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

import apatis.com.apatispolinema.adapter.DaftarPasienAdapter;
import apatis.com.apatispolinema.adapter.PasienAdapter;
import apatis.com.apatispolinema.models.Pasien;

public class DaftarPasienActivity extends AppCompatActivity {
    private DaftarPasienAdapter mAdapter;
    private List<Pasien> mDaftarPasien= new ArrayList<>();
    DatabaseReference databasePasien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pasien);

        final RecyclerView recyclerMenu = (RecyclerView) findViewById(R.id.rvDaftarPasien);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMenu.setLayoutManager(mLayoutManager);

        databasePasien = FirebaseDatabase.getInstance().getReference();
        databasePasien.child("pasien").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDaftarPasien= new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Pasien pasien= noteDataSnapshot.getValue(Pasien.class);
                    pasien.setIdPasien(noteDataSnapshot.getKey());
                    mDaftarPasien.add(pasien);
                }

                mAdapter = new DaftarPasienAdapter(DaftarPasienActivity.this,mDaftarPasien);
                recyclerMenu.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
}
