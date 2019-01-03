package damisbachtiar17.web.id.uts.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import damisbachtiar17.web.id.uts.DashboardActivity;
import damisbachtiar17.web.id.uts.R;
import damisbachtiar17.web.id.uts.model.DataModelGeray;

public class AdapterDataPilihGeray extends RecyclerView.Adapter<AdapterDataPilihGeray.HolderData> {

    private List<DataModelGeray> mList ;
    private Context ctx;


    public  AdapterDataPilihGeray (Context ctx, List<DataModelGeray> mList)
    {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public AdapterDataPilihGeray.HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_geray,parent, false);
        AdapterDataPilihGeray.HolderData holder = new AdapterDataPilihGeray.HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(AdapterDataPilihGeray.HolderData holder, int position) {
        DataModelGeray dm = mList.get(position);
        Log.d("TAG", "onBindViewHolder: "+dm.getNama().toString());
        holder.nama.setText(dm.getNama());
        holder.no_telp.setText(dm.getNoTelp());
        holder.alamat.setText(dm.getAlamat());
        holder.dm = dm;
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder{
        TextView nama, no_telp, alamat;
        DataModelGeray dm;
        public HolderData (View v)
        {
            super(v);

            nama  = (TextView) v.findViewById(R.id.nama);
            no_telp = (TextView) v.findViewById(R.id.no_telp);
            alamat = (TextView) v.findViewById(R.id.alamat);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx,DashboardActivity.class);
                    goInput.putExtra("geray_id", dm.getId());
                    goInput.putExtra("geray_nama", dm.getNama());
                    goInput.putExtra("geray_no_telp", dm.getNoTelp());
                    goInput.putExtra("geray_alamat", dm.getAlamat());

                    ctx.startActivity(goInput);
                }
            });
        }
    }
}