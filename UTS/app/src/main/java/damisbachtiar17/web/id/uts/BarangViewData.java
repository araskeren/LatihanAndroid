package damisbachtiar17.web.id.uts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class BarangViewData extends RecyclerView.Adapter<ViewHolderBarang> {

    private List<BarangViewItem> barangViewItemList;
    SharedPreferences preferences;
    public static final String KEYPREF = "HARGA";
    public static final String KEYVALUE = "KEYVALUE";

    public BarangViewData(List<BarangViewItem> barangViewItems){
        this.barangViewItemList = barangViewItems;
    }

    @Override
    public ViewHolderBarang onCreateViewHolder(final ViewGroup parent, int viewType){
        // Get LayoutInflater object.
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the RecyclerView item layout xml.
        final View viewItemBarang = layoutInflater.inflate(R.layout.adapter_barang, parent, false);

        // Get car title text view object.
        final TextView judulBarang = (TextView)viewItemBarang.findViewById(R.id.nama_gambar);
        // Get car image view object.
        final ImageView gambarBarang = (ImageView)viewItemBarang.findViewById(R.id.gambar);

        final TextView hargaBarang = (TextView)viewItemBarang.findViewById(R.id.harga_gambar);

        final TextView deskripsiBarang = (TextView)viewItemBarang.findViewById(R.id.deskripsi);

        final LinearLayout background = (LinearLayout) viewItemBarang.findViewById(R.id.linearBackground);

        //When judul barang on click
        judulBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(),DetailBarang.class);
                intent.putExtra("judul",judulBarang.getText().toString());
                intent.putExtra("harga",hargaBarang.getText().toString());
                intent.putExtra("deskripsi",deskripsiBarang.getText().toString());

                parent.getContext().startActivity(intent);
                Toast.makeText(parent.getContext(), ""+judulBarang.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // When click the image.
        gambarBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.e("CekHarga ",""+carHargaView.getText().toString());
                //MainActivity harga= new MainActivity();
                //harga.updateData(carHargaView.getText().toString());
                
                Toast.makeText(parent.getContext(), ""+hargaBarang.getText().toString(), Toast.LENGTH_SHORT).show();
                // Get car title text.
                // Create a snackbar and show it.
                //Snackbar snackbar = Snackbar.make(carImageView, "You click " + carTitle +" image", Snackbar.LENGTH_LONG);
                //snackbar.show();
            }
        });

        // Create and return our custom Car Recycler View Item Holder object.
        ViewHolderBarang ret = new ViewHolderBarang(viewItemBarang);
        return ret;
    }

    @Override
    public void onBindViewHolder(ViewHolderBarang holder, int position) {
        if(barangViewItemList!=null) {
            // Get car item dto in list.
            BarangViewItem itemBarang = barangViewItemList.get(position);

            if(itemBarang != null) {
                // Set car item title.
                holder.getJudulBarang().setText(itemBarang.getNamaBarang());
                holder.getDeskripsiBarang().setText(itemBarang.getDeskripsiBarang());
                holder.getHarga().setText(String.valueOf(itemBarang.getIdHarga()));

                // Set car image resource id.
                holder.getGambarBarang().setImageResource(itemBarang.getIdGambar());
            }
        }
    }

    @Override
    public int getItemCount(){
        int ret = 0;
        if(barangViewItemList!=null)
        {
            ret = barangViewItemList.size();
        }
        return ret;
    }
}