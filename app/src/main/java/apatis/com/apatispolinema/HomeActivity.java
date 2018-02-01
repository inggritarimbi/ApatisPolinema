package apatis.com.apatispolinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import apatis.com.apatispolinema.models.Pasien;

public class HomeActivity extends AppCompatActivity {
    Button BtnDaftar,BtnLogout,BtnLihat,BtnMap;
    FirebaseAuth firebaseAuth;
    DatabaseReference databasePasien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        Intent i = getIntent();
        final String email = i.getStringExtra("email");

        databasePasien= FirebaseDatabase.getInstance().getReference();
        databasePasien.child("pasien").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<String> antrian = new ArrayList<String>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Pasien pasien= noteDataSnapshot.getValue(Pasien.class);
                    pasien.setIdPasien(noteDataSnapshot.getKey());
                    antrian.add(pasien.getNomorAntrian());
                }

                BtnDaftar = (Button) findViewById(R.id.btnDaftar);
                BtnDaftar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(),PendaftaranActivity.class);
                        i.putExtra("email",email);
                        i.putExtra("antrian",antrian);
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });

        BtnLihat = (Button) findViewById(R.id.btnLihat);
        BtnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LihatAntrianActivity.class);
                i.putExtra("email",email);
                startActivity(i);
            }
        });


        BtnLogout = (Button) findViewById(R.id.btnLogout);
        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        BtnMap = (Button) findViewById(R.id.btnMap);
        BtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(j);
            }
        });
    }
}
