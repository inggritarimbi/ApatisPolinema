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
import apatis.com.apatispolinema.adapter.SkipAdapter;
import apatis.com.apatispolinema.models.Pasien;
import apatis.com.apatispolinema.models.Skip;

public class SkipActivity extends AppCompatActivity {
    private SkipAdapter mAdapter;
    private List<Skip> mDaftarPasien= new ArrayList<>();
    DatabaseReference databasePasien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip);

        final RecyclerView recyclerMenu = (RecyclerView) findViewById(R.id.rvSkip);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerMenu.setLayoutManager(mLayoutManager);

        databasePasien = FirebaseDatabase.getInstance().getReference();
        databasePasien.child("skip").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mDaftarPasien= new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Skip skip= noteDataSnapshot.getValue(Skip.class);
                    skip.setIdPasien(noteDataSnapshot.getKey());
                    mDaftarPasien.add(skip);
                }

                mAdapter = new SkipAdapter(SkipActivity.this,mDaftarPasien);
                recyclerMenu.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
}
