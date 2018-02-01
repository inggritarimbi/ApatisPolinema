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
import apatis.com.apatispolinema.DetailRekapActivity;
import apatis.com.apatispolinema.R;
import apatis.com.apatispolinema.models.Pasien_Tetap;
import apatis.com.apatispolinema.models.Skip;

/**
 * Created by ASUS on 28/01/2018.
 */

public class RekapAdapter extends RecyclerView.Adapter<RekapAdapter.ViewHolder> {
    private Context context;
    private List<Pasien_Tetap> listRekap;

    public RekapAdapter(Context context, List<Pasien_Tetap> listRekap) {
        this.context = context;
        this.listRekap = listRekap;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rekap,parent,false);
        return new RekapAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pasien_Tetap rekap = listRekap.get(position);
        holder.TxtNama.setText(rekap.getNama_pasien());
        holder.TxtPoli.setText(rekap.getPoli());
        holder.TxtTanggal.setText(rekap.getTanggal());
        holder.TxtNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DetailRekapActivity.class);
                i.putExtra("alamat",rekap.getAlamat());
//                i.putExtra("idPasien",rekap.getIdPasien());
                i.putExtra("nama",rekap.getNama_pasien());
                i.putExtra("pekerjaan",rekap.getPekerjaan());
                i.putExtra("poli",rekap.getPoli());
                i.putExtra("umur",rekap.getUmur());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRekap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TxtNama, TxtPoli,TxtTanggal;
        public ViewHolder(View itemView) {
            super(itemView);
            TxtNama = itemView.findViewById(R.id.txtNama);
            TxtPoli  =itemView.findViewById(R.id.txtPoli);
            TxtTanggal  =itemView.findViewById(R.id.txtTanggal);
        }
    }
}
