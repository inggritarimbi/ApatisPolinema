package apatis.com.apatispolinema;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import apatis.com.apatispolinema.adapter.PasienAdapter;
import apatis.com.apatispolinema.models.Pasien;

public class AntrianActivity extends AppCompatActivity {
    private PasienAdapter mAdapter;
    private List<Pasien> mDaftarPasien= new ArrayList<>();
    DatabaseReference databasePasien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrian);

        final RecyclerView recyclerMenu = (RecyclerView) findViewById(R.id.rvAntrian);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMenu.setLayoutManager(mLayoutManager);

        databasePasien = FirebaseDatabase.getInstance().getReference();//Inisialisasi dan mengambil Firebase Database Reference
        databasePasien.child("pasien").addValueEventListener(new ValueEventListener() {//Mengambil data pasien dari Firebase Realtime DB
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {//dipanggil saat sebuah tugas baru ditambahkan atau dihapus dari database Firebase.
                mDaftarPasien= new ArrayList<>();//Saat ada data baru, masukkan datanya ke ArrayList
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {//pada DataSnapshot ke dalam object pasien
                    Pasien pasien= noteDataSnapshot.getValue(Pasien.class);//Dan juga menyimpan primary key pada object pasien untuk keperluan Edit dan Delete data
                    pasien.setIdPasien(noteDataSnapshot.getKey());
                    mDaftarPasien.add(pasien);//Menambahkan object pasien yang sudah dimapping  ke dalam ArrayList

                }

                mAdapter = new PasienAdapter(AntrianActivity.this,mDaftarPasien);//Inisialisasi adapter dan data pasien dalam bentuk ArrayList dan mengeset Adapter ke dalam RecyclerView
                recyclerMenu.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {//Kode ini akan dipanggil ketika ada error dan pengambilan data gagal
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
}
