package apatis.com.apatispolinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import apatis.com.apatispolinema.models.Pasien;

public class PendaftaranActivity extends AppCompatActivity {
    EditText Nama_Pasien, Alamat, Pekerjaan, Umur;
    Button BtnPendaftaran;
    Spinner Poli;
//    ArrayList<String> antrian = new ArrayList<>();
    int nomorAntrian = 0;
    DatabaseReference databaseApatis;
//    DatabaseReference databasePasien;
    String nomorBaru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendaftaran);

        Intent i = getIntent();
        final ArrayList<String> antrian = (ArrayList<String>) i.getSerializableExtra("antrian");
        final String email = i.getStringExtra("email");

        Nama_Pasien= (EditText) findViewById(R.id.nama_pasien);
        Alamat= (EditText) findViewById(R.id.alamat);
        Pekerjaan= (EditText) findViewById(R.id.pekerjaan);
        Umur = (EditText) findViewById(R.id.umur);
        BtnPendaftaran = (Button) findViewById(R.id.btnPendaftaran);
        Poli = (Spinner) findViewById(R.id.poli);



//        databasePasien= FirebaseDatabase.getInstance().getReference();
//        databasePasien.child("pasien").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
//                    Pasien pasien= noteDataSnapshot.getValue(Pasien.class);
//                    antrian.add(pasien.getNomorAntrian());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
//            }
//        });
        if (antrian.size() == 0){
            nomorBaru = "1";
        }
        else{
            String ant = antrian.get(antrian.size()-1);
            int antrianint = Integer.parseInt(ant);
            nomorAntrian = antrianint + 1;
            nomorBaru = String.valueOf(nomorAntrian);
        }

        databaseApatis = FirebaseDatabase.getInstance().getReference("pasien");
        BtnPendaftaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(Nama_Pasien.toString()) && !TextUtils.isEmpty(Alamat.toString()) && !TextUtils.isEmpty(Pekerjaan.toString()) && !TextUtils.isEmpty(Umur.toString())){
                    String id = databaseApatis.push().getKey();
                    Pasien pasien = new Pasien(id,Nama_Pasien.getText().toString(),Alamat.getText().toString(),Pekerjaan.getText().toString(),Umur.getText().toString(),Poli.getSelectedItem().toString(),nomorBaru,email);
                    databaseApatis.child(id).setValue(pasien);
                    Toast.makeText(getApplicationContext(), "Pendaftaran Berhasil",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplication(),HomeActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "Semua data harus diisi",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
