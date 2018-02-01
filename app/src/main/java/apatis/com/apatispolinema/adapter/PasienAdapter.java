package apatis.com.apatispolinema.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import apatis.com.apatispolinema.R;
import apatis.com.apatispolinema.models.Pasien;

/**
 * Created by ASUS on 17/01/2018.
 */

public class PasienAdapter extends RecyclerView.Adapter<PasienAdapter.ViewHolder> {
    private Context context;
    private List<Pasien> listPasien;

    public PasienAdapter(Context context, List<Pasien> listPasien) {
        this.context = context;
        this.listPasien = listPasien;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_antrian,parent,false);
        return new PasienAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pasien pasien = listPasien.get(position);
        holder.TxtNomor.setText(pasien.getNomorAntrian());
        holder.TxtNama.setText(pasien.getNama_pasien());
        holder.TxtPoli.setText(pasien.getPoli());
//        holder.TxtMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(v.getContext(), DetailMenuActivity.class);
//                i.putExtra("idMenu",menu.getId_menu());
//                i.putExtra("namaMenu",menu.getNama_menu());
//                i.putExtra("harga",menu.getHarga());
//                i.putExtra("stok",menu.getStok());
//                v.getContext().startActivity(i);
//            }
//        });
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
