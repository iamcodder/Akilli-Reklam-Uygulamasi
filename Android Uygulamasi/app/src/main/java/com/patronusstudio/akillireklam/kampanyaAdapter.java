package com.patronusstudio.akillireklam;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class kampanyaAdapter extends RecyclerView.Adapter<kampanyaAdapter.viewHolder> {

    private Context mContext;
    private List<magazaModel> kampanyaliMagazaListesi;

    public kampanyaAdapter(Context mContext, List<magazaModel> kampanyaliMagazaListesi) {
        this.mContext = mContext;
        this.kampanyaliMagazaListesi = kampanyaliMagazaListesi;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView= LayoutInflater.from(mContext).inflate(R.layout.kampanya_item,viewGroup,false);

        return new viewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        viewHolder.firma_adi.setText(kampanyaliMagazaListesi.get(i).getFirmaAdi());
        viewHolder.kampanya_icerigi.setText(kampanyaliMagazaListesi.get(i).getKampanyaIcerik());
        viewHolder.kampanya_suresi.setText(kampanyaliMagazaListesi.get(i).getKampanyaSuresi());
    }

    @Override
    public int getItemCount() {
        return kampanyaliMagazaListesi.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{

        TextView firma_adi,kampanya_icerigi,kampanya_suresi;

        viewHolder(@NonNull View itemView) {
            super(itemView);
            firma_adi=itemView.findViewById(R.id.txt_firma_adi);
            kampanya_icerigi=itemView.findViewById(R.id.txt_firma_kampanya_icerigi);
            kampanya_suresi=itemView.findViewById(R.id.txt_firma_kampanya_suresi);
        }
    }
}
