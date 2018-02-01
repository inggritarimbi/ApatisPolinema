package apatis.com.apatispolinema;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText TxtEmail,TxtPassword;
    Button BtnLogin;
    Button BtnRegister, BtnKembali;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);



        TxtEmail = (EditText) findViewById(R.id.txtEmail);
        TxtPassword = (EditText) findViewById(R.id.txtPassword);

        BtnLogin = (Button) findViewById(R.id.btnLogin);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        BtnRegister = (Button) findViewById(R.id.btnRegisterL);
        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });

        BtnKembali = (Button) findViewById(R.id.btnKembaliL);
        BtnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void loginUser(){
        if (TextUtils.isEmpty(TxtEmail.getText().toString())){
            Toast.makeText(this,"Masukkan Email!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(TxtPassword.getText().toString())){
            Toast.makeText(this,"Masukkan Password!",Toast.LENGTH_SHORT).show();
            return;
        }

        final String email = TxtEmail.getText().toString();

        progressDialog.setMessage("Login Process...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(TxtEmail.getText().toString(),TxtPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            finish();
                            if (TxtEmail.getText().toString().equals("admin@gmail.com")){
                                startActivity(new Intent(getApplicationContext(),HomeAdmin.class));
                            }
                            else{
                                Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                                i.putExtra("email",email);
                                startActivity(i);
                            }
                            Toast.makeText(LoginActivity.this,"Login Berhasil!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this,"Login Gagal!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
