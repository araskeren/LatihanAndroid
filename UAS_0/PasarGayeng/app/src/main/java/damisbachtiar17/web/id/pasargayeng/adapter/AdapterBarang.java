package damisbachtiar17.web.id.pasargayeng.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import com.squareup.picasso.Picasso;

import damisbachtiar17.web.id.pasargayeng.model.DataModelBarang;
//import damisbachtiar17.web.id.pasargayeng.model.ResultBarang;
import damisbachtiar17.web.id.pasargayeng.R;
import damisbachtiar17.web.id.pasargayeng.DetailBarang;
public class AdapterBarang extends RecyclerView.Adapter<AdapterBarang.HolderData>{
    private List<DataModelBarang> mList ;
    private Context ctx;
    private String token;
    public  AdapterBarang (Context ctx, List<DataModelBarang> mList,String token)
    {
        this.ctx = ctx;
        this.mList = mList;
        this.token = token;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_barang,parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        DataModelBarang dm = mList.get(position);
        Log.e("", "onBindViewHolder: "+dm.getNama());
        holder.nama.setText(dm.getNama());
        holder.harga.setText(dm.getHarga());
//        new DownloadImageTask(holder.gambar)
//                .execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");
        Picasso.get().load(dm.getGambar()).into(holder.gambar);

        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends  RecyclerView.ViewHolder{
        TextView nama, harga, deskripsi;
        ImageView gambar;
        DataModelBarang dm;
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
                    goInput.putExtra("id", dm.getId());
                    goInput.putExtra("judul", dm.getNama());
                    goInput.putExtra("harga", dm.getHarga());
                    goInput.putExtra("gambar", dm.getGambar());
                    goInput.putExtra("deskripsi", dm.getDeskripsi());
                    goInput.putExtra("token", token);

                    ctx.startActivity(goInput);
                }
            });
        }
    }
}
