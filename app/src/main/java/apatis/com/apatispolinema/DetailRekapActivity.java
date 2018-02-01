package apatis.com.apatispolinema;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailRekapActivity extends AppCompatActivity {
    TextView TxtNamaPasien, TxtAlamat, TxtPekerjaan, TxtUmur, TxtPoli;
    Button BtnMasuk,BtnSkip;
    DatabaseReference databasePasien;
    DatabaseReference databaseApatis;
    DatabaseReference databaseSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_rekap);

        databasePasien = FirebaseDatabase.getInstance().getReference("pasien_tetap");
        databaseSkip= FirebaseDatabase.getInstance().getReference("skip");
        databaseApatis = FirebaseDatabase.getInstance().getReference("pasien");

        Intent i = getIntent();
        final String idPasien = i.getStringExtra("idPasien");
        final String nama = i.getStringExtra("nama");
        final String pekerjaan = i.getStringExtra("pekerjaan");
        final String poli = i.getStringExtra("poli");
        final String umur = i.getStringExtra("umur");
        final String alamat = i.getStringExtra("alamat");
        final String nomor = i.getStringExtra("nomor");
        final String email = i.getStringExtra("email");

        TxtNamaPasien = (TextView) findViewById(R.id.txtNamaPasien);
        TxtAlamat= (TextView) findViewById(R.id.txtAlamat);
        TxtPekerjaan= (TextView) findViewById(R.id.txtPekerjaan);
        TxtUmur= (TextView) findViewById(R.id.txtUmur);
        TxtPoli= (TextView) findViewById(R.id.txtPoli);
        BtnMasuk = (Button) findViewById(R.id.btnMasuk);
        BtnSkip = (Button) findViewById(R.id.btnSkip);

        TxtNamaPasien.setText(nama);
        TxtAlamat.setText(alamat);
        TxtPekerjaan.setText(pekerjaan);
        TxtUmur.setText(umur);
        TxtPoli.setText(poli);
    }
}
