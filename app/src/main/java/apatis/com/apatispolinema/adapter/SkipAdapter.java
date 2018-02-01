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
import apatis.com.apatispolinema.DetailSkipActivity;
import apatis.com.apatispolinema.R;
import apatis.com.apatispolinema.models.Pasien;
import apatis.com.apatispolinema.models.Skip;

/**
 * Created by ASUS on 27/01/2018.
 */

public class SkipAdapter extends RecyclerView.Adapter<SkipAdapter.ViewHolder> {

    private Context context;
    private List<Skip> listSkip;

    public SkipAdapter(Context context, List<Skip> listSkip) {
        this.context = context;
        this.listSkip = listSkip;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skip,parent,false);
        return new SkipAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Skip skip = listSkip.get(position);
        holder.TxtNomor.setText(skip.getNomorAntrian());
        holder.TxtNama.setText(skip.getNama_pasien());
        holder.TxtPoli.setText(skip.getPoli());
        holder.TxtNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DetailSkipActivity.class);
                i.putExtra("alamat",skip.getAlamat());
                i.putExtra("idPasien",skip.getIdPasien());
                i.putExtra("nama",skip.getNama_pasien());
                i.putExtra("pekerjaan",skip.getPekerjaan());
                i.putExtra("poli",skip.getPoli());
                i.putExtra("umur",skip.getUmur());
                i.putExtra("nomor",skip.getNomorAntrian());
                i.putExtra("email",skip.getEmail());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSkip.size();
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
