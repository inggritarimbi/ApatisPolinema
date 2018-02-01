package apatis.com.apatispolinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import apatis.com.apatispolinema.models.Pasien;

public class LihatAntrianActivity extends AppCompatActivity {
    TextView nomorAntrian;
    Button BtnLihatAntrian,BtnSkip;
    DatabaseReference databasePasien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_antrian);

        nomorAntrian = (TextView) findViewById(R.id.txtNomorAntrian);
        BtnLihatAntrian = (Button) findViewById(R.id.btnAntrian);
        BtnSkip = (Button) findViewById(R.id.btnSkip);

        BtnLihatAntrian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AntrianActivity.class);
                startActivity(i);
            }
        });

        BtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SkipPasienActivity.class);
                startActivity(i);
            }
        });


        final Intent i = getIntent();
        final String email = i.getStringExtra("email");

        databasePasien = FirebaseDatabase.getInstance().getReference("pasien");
        databasePasien.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                ArrayList<String> imel = new ArrayList<String>();
                String antrian = null;
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Pasien pasien= noteDataSnapshot.getValue(Pasien.class);
                    pasien.setIdPasien(noteDataSnapshot.getKey());
                    antrian = pasien.getNomorAntrian();
                }
                nomorAntrian.setText(antrian);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
}
