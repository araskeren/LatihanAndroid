package id.web.damisbachtiar.ayamku;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BarangViewData extends RecyclerView.Adapter<ViewHolderBarang> {

    private List<BarangViewItem> barangViewItemList;

    public BarangViewData(List<BarangViewItem> barangViewItems) {
        this.barangViewItemList = barangViewItems;
    }

    @Override
    public ViewHolderBarang onCreateViewHolder(final ViewGroup parent, int viewType) {
        // Get LayoutInflater object.
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the RecyclerView item layout xml.
        final View carItemView = layoutInflater.inflate(R.layout.adapter_ayam, parent, false);

        // Get car title text view object.
        final TextView carTitleView = (TextView)carItemView.findViewById(R.id.nama_gambar);
        // Get car image view object.
        final ImageView carImageView = (ImageView)carItemView.findViewById(R.id.gambar);

        final TextView carHargaView = (TextView)carItemView.findViewById(R.id.harga_gambar);

        final LinearLayout background = (LinearLayout) carItemView.findViewById(R.id.linearBackground);
        // When click the image.
        carImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.e("CekHarga ",""+carHargaView.getText().toString());
                //MainActivity harga= new MainActivity();
                //harga.updateData(carHargaView.getText().toString());
                
                Toast.makeText(parent.getContext(), ""+carHargaView.getText().toString(), Toast.LENGTH_SHORT).show();
                // Get car title text.
                // Create a snackbar and show it.
//                Snackbar snackbar = Snackbar.make(carImageView, "You click " + carTitle +" image", Snackbar.LENGTH_LONG);
//                snackbar.show();
            }
        });

        // Create and return our custom Car Recycler View Item Holder object.
        ViewHolderBarang ret = new ViewHolderBarang(carItemView);
        return ret;
    }

    @Override
    public void onBindViewHolder(ViewHolderBarang holder, int position) {
        if(barangViewItemList!=null) {
            // Get car item dto in list.
            BarangViewItem carItem = barangViewItemList.get(position);

            if(carItem != null) {
                // Set car item title.
                holder.getCarTitleText().setText(carItem.getCarName());
                holder.getHarga().setText(String.valueOf(carItem.getHargaId()));
                // Set car image resource id.
                holder.getCarImageView().setImageResource(carItem.getCarImageId());
            }
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if(barangViewItemList!=null)
        {
            ret = barangViewItemList.size();
        }
        return ret;
    }
}