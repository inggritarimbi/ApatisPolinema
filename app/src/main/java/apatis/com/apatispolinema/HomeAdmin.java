package apatis.com.apatispolinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class HomeAdmin extends AppCompatActivity {

    Button BtnPasien,BtnLogout,BtnSkip,BtnRekap;
    DatabaseReference databasePasien;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        BtnPasien = (Button) findViewById(R.id.btnPasien);
        BtnPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DaftarPasienActivity.class);
                startActivity(i);
            }
        });

        BtnSkip = (Button) findViewById(R.id.btnSkip);
        BtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SkipActivity.class);
                startActivity(i);
            }
        });

        BtnRekap = (Button) findViewById(R.id.btnRekap);
        BtnRekap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RekapPasienActivity.class);
                startActivity(i);
            }
        });

        BtnLogout= (Button) findViewById(R.id.btnLogout);
        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}
