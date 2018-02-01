package apatis.com.apatispolinema;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import apatis.com.apatispolinema.models.Pasien;
import apatis.com.apatispolinema.models.Pasien_Tetap;
import apatis.com.apatispolinema.models.Skip;

public class DetailPasienActivity extends AppCompatActivity {

    TextView TxtNamaPasien, TxtAlamat, TxtPekerjaan, TxtUmur, TxtPoli;
    Button BtnMasuk,BtnSkip;
    DatabaseReference databasePasien;
    DatabaseReference databaseApatis;
    DatabaseReference databaseSkip;
//    Calendar calendar = Calendar.getInstance();
    Date dates = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pasien);

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
//        final String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        TxtNamaPasien.setText(nama);
        TxtAlamat.setText(alamat);
        TxtPekerjaan.setText(pekerjaan);
        TxtUmur.setText(umur);
        TxtPoli.setText(poli);

        BtnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                TextView textViewDate = (TextView) findViewById(R.id.txtDate);
//                textViewDate.setText(currentDate);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                String date = sdf.format(dates);

                String id = databasePasien.push().getKey();
                Pasien_Tetap pasien = new Pasien_Tetap(id,nama,alamat,pekerjaan,umur,poli,date);
                databasePasien.child(id).setValue(pasien);
                databaseApatis.child(idPasien).removeValue();
                Toast.makeText(getApplicationContext(), "Pasien Telah Terdaftar",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplication(),DaftarPasienActivity.class);
                startActivity(i);
            }
        });

        BtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = databaseSkip.push().getKey();
                Skip skip= new Skip(id,nama,alamat,pekerjaan,umur,poli,nomor,email);
                databaseSkip.child(id).setValue(skip);
                databaseApatis.child(idPasien).removeValue();
                Toast.makeText(getApplicationContext(), "Pasien Telah di Skip",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplication(),DaftarPasienActivity.class);
                startActivity(i);
            }
        });
    }
}
