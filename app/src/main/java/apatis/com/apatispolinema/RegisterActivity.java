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

public class RegisterActivity extends AppCompatActivity {
    EditText TxtEmail,TxtPassword;
    Button BtnRegister;
    Button BtnLogin, BtnKembali;
    FirebaseAuth firebaseAuth;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        TxtEmail = (EditText) findViewById(R.id.txtEmail);
        TxtPassword = (EditText) findViewById(R.id.txtPassword);

        BtnLogin = (Button) findViewById(R.id.btnLoginR);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });

        BtnRegister = (Button) findViewById(R.id.btnRegister);
        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        BtnKembali = (Button) findViewById(R.id.btnKembaliR);
        BtnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void registerUser(){
        if (TextUtils.isEmpty(TxtEmail.getText().toString())){
            Toast.makeText(this,"Masukkan Email!",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(TxtPassword.getText().toString())){
            Toast.makeText(this,"Masukkan Password!",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(TxtEmail.getText().toString(),TxtPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            TxtEmail.setText("");
                            TxtPassword.setText("");
                            Toast.makeText(RegisterActivity.this,"Register Sukses", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this,"Tidak bisa di register, silahkan coba lagi.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
