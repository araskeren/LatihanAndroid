package arumfaisal.id.paysargayengumum.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import arumfaisal.id.paysargayengumum.DaftarBarangActivity;
import arumfaisal.id.paysargayengumum.R;
import arumfaisal.id.paysargayengumum.model.DataModelToko;

public class AdapterToko extends RecyclerView.Adapter<AdapterToko.HolderData>{
    private List<DataModelToko> mList ;
    private Context ctx;
    private String token;

    public AdapterToko(List<DataModelToko> mList, Context ctx, String token) {
        this.mList = mList;
        this.ctx = ctx;
        this.token = token;
    }

    @Override
    public AdapterToko.HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_toko,parent, false);
        AdapterToko.HolderData holder = new AdapterToko.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterToko.HolderData holder, int position) {
        DataModelToko dm = mList.get(position);
        Log.e("", "onBindViewHolder: "+dm.getNama());
        holder.nama.setText(dm.getNama());
//        new DownloadImageTask(holder.gambar)
//                .execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");
        Picasso.get().load(dm.getCover()).into(holder.gambar);

        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends  RecyclerView.ViewHolder{
        TextView nama, harga, deskripsi;
        ImageView gambar;
        DataModelToko dm;
        public HolderData (View v)
        {
            super(v);

            nama  = (TextView) v.findViewById(R.id.nama_gambar);
            gambar = (ImageView) v.findViewById(R.id.gambar);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx,DaftarBarangActivity.class);
                    Log.d("onClick", "onClick: "+dm.getId());
                    Log.d("onClick", "onClick: "+dm.getNama());
                    goInput.putExtra("id", dm.getId());
                    goInput.putExtra("nama_toko", dm.getNama());
                    //goInput.putExtra("gambar", dm.getGambar());
                    goInput.putExtra("token", token);

                    ctx.startActivity(goInput);
                }
            });
        }
    }
}
