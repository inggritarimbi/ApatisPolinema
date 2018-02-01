package apatis.com.apatispolinema.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import apatis.com.apatispolinema.R;
import apatis.com.apatispolinema.RekapActivity;
import apatis.com.apatispolinema.models.Pasien_Tetap;

/**
 * Created by ASUS on 31/01/2018.
 */

public class RekapPasienAdapter extends RecyclerView.Adapter<RekapPasienAdapter.ViewHolder> {
    private Context context;
    private List<Pasien_Tetap> listRekap;

    public RekapPasienAdapter(Context context, List<Pasien_Tetap> listRekap) {
        this.context = context;
        this.listRekap = listRekap;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rekap_pasien,parent,false);
        return new RekapPasienAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Pasien_Tetap rekappasien = listRekap.get(position);
        holder.TxtDate.setText(rekappasien.getTanggal());
        holder.TxtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),RekapActivity.class);
                i.putExtra("tanggal",rekappasien.getTanggal());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRekap.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TxtDate;
        public ViewHolder(View itemView) {
            super(itemView);
            TxtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
