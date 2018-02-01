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

import apatis.com.apatispolinema.adapter.RekapAdapter;
import apatis.com.apatispolinema.adapter.RekapPasienAdapter;
import apatis.com.apatispolinema.models.Pasien_Tetap;

public class RekapPasienActivity extends AppCompatActivity {
    private RekapPasienAdapter mAdapter;
    private List<Pasien_Tetap> mDaftarPasien= new ArrayList<>();
    private String tanggal = "null";
    DatabaseReference databasePasien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_pasien);

        final RecyclerView recyclerMenu = (RecyclerView) findViewById(R.id.rvRekapPasien);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMenu.setLayoutManager(mLayoutManager);

        databasePasien = FirebaseDatabase.getInstance().getReference();
        databasePasien.child("pasien_tetap").orderByChild("tanggal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDaftarPasien= new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Pasien_Tetap rekap= noteDataSnapshot.getValue(Pasien_Tetap.class);
                    rekap.setIdPasienTetap(noteDataSnapshot.getKey());
                    if(!rekap.getTanggal().equals(tanggal)){
                        mDaftarPasien.add(rekap);
                        tanggal = rekap.getTanggal();
                    }
                }

                mAdapter = new RekapPasienAdapter(RekapPasienActivity.this,mDaftarPasien);
                recyclerMenu.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
}
