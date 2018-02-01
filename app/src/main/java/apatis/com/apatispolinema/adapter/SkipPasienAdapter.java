package apatis.com.apatispolinema.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import apatis.com.apatispolinema.R;
import apatis.com.apatispolinema.models.Skip;

/**
 * Created by ASUS on 29/01/2018.
 */

public class SkipPasienAdapter extends RecyclerView.Adapter<SkipPasienAdapter.ViewHolder> {
    private Context context;
    private List<Skip> listSkip;

    public SkipPasienAdapter(Context context, List<Skip> listSkip) {
        this.context = context;
        this.listSkip = listSkip;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pasien_skip,parent,false);
        return new SkipPasienAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Skip skip = listSkip.get(position);
        holder.TxtNomor.setText(skip.getNomorAntrian());
        holder.TxtNama.setText(skip.getNama_pasien());
        holder.TxtPoli.setText(skip.getPoli());
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
