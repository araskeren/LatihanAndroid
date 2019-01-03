package damisbachtiar17.web.id.uts.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

import damisbachtiar17.web.id.uts.DetailBarang;
import damisbachtiar17.web.id.uts.MainActivity;
import damisbachtiar17.web.id.uts.model.DataModelAyam;
import damisbachtiar17.web.id.uts.R;
public class AdapterDataAyam extends RecyclerView.Adapter<AdapterDataAyam.HolderData> {

    private List<DataModelAyam> mList ;
    private Context ctx;
    private String geray_id;


    public  AdapterDataAyam (Context ctx, List<DataModelAyam> mList,String geray_id)
    {
        this.ctx = ctx;
        this.mList = mList;
        this.geray_id=geray_id;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_barang,parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        DataModelAyam dm = mList.get(position);
        Log.d("TAG", "onBindViewHolder: "+dm.getNama().toString());
        holder.nama.setText(dm.getNama());
        holder.harga.setText(dm.getHarga());
//        new DownloadImageTask(holder.gambar)
//                .execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");
        Picasso.get().load(dm.getGambar()).into(holder.gambar);

        holder.dm = dm;
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder{
        TextView nama, harga, deskripsi;
        ImageView gambar;
        DataModelAyam dm;
        public HolderData (View v)
        {
            super(v);

            nama  = (TextView) v.findViewById(R.id.nama_gambar);
            harga = (TextView) v.findViewById(R.id.harga_gambar);
            gambar = (ImageView) v.findViewById(R.id.gambar);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx,DetailBarang.class);
                    Log.d("onClick", "onClick: "+dm.getId());
                    Log.d("onClick", "onClick: "+dm.getNama());
                    goInput.putExtra("barang_id", dm.getId());
                    goInput.putExtra("geray_id", geray_id);
                    goInput.putExtra("judul", dm.getNama());
                    goInput.putExtra("harga", dm.getHarga());
                    goInput.putExtra("gambar", dm.getGambar());
                    goInput.putExtra("deskripsi", dm.getDeskripsi());

                    ctx.startActivity(goInput);
                }
            });
        }
    }
}