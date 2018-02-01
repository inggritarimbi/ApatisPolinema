package apatis.com.apatispolinema.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import apatis.com.apatispolinema.DetailPasienActivity;
import apatis.com.apatispolinema.R;
import apatis.com.apatispolinema.models.Pasien;

/**
 * Created by ASUS on 17/01/2018.
 */

public class DaftarPasienAdapter extends RecyclerView.Adapter<DaftarPasienAdapter.ViewHolder> {
    private Context context;
    private List<Pasien> listPasien;

    public DaftarPasienAdapter(Context context, List<Pasien> listPasien) {
        this.context = context;
        this.listPasien = listPasien;
    }

    @Override
    public DaftarPasienAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pasien,parent,false);
        return new DaftarPasienAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DaftarPasienAdapter.ViewHolder holder, int position) {
        final Pasien pasien = listPasien.get(position);
        holder.TxtNomor.setText(pasien.getNomorAntrian());
        holder.TxtNama.setText(pasien.getNama_pasien());
        holder.TxtPoli.setText(pasien.getPoli());
        holder.TxtNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DetailPasienActivity.class);
                i.putExtra("alamat",pasien.getAlamat());
                i.putExtra("idPasien",pasien.getIdPasien());
                i.putExtra("nama",pasien.getNama_pasien());
                i.putExtra("pekerjaan",pasien.getPekerjaan());
                i.putExtra("poli",pasien.getPoli());
                i.putExtra("umur",pasien.getUmur());
                i.putExtra("nomor",pasien.getNomorAntrian());
                i.putExtra("email",pasien.getEmail());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPasien.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView TxtNomor,TxtNama, TxtPoli;
        public ViewHolder(View itemView) {
            super(itemView);
            TxtNomor = itemView.findViewById(R.id.txtNomorAntrian);
            TxtNama = itemView.findViewById(R.id.txtNama);
            TxtPoli = itemView.findViewById(R.id.txtPoli);
        }
    }
}
