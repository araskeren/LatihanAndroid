package damisbachtiar17.web.id.pasargayeng.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import damisbachtiar17.web.id.pasargayeng.R;
import damisbachtiar17.web.id.pasargayeng.model.DataModelTransaksi;
import damisbachtiar17.web.id.pasargayeng.model.DataModelTransaksiUser;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelTransaksi;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelTransaksiUser;

public class AdapterListBarangTransaksi extends RecyclerView.Adapter<AdapterListBarangTransaksi.HolderData> {
    private List<DataModelTransaksiUser> mList ;
    private Context ctx;
    private String token;
    private ResponsModelTransaksiUser dataModelTransaksiUser;
    ArrayList<String> alist=new ArrayList<String>();


    public AdapterListBarangTransaksi(List<DataModelTransaksiUser> mList, Context ctx, String token) {
        Log.e("dataMasuk : ",""+ new Gson().toJson(mList));
        this.mList = mList;
        this.ctx = ctx;
        this.token = token;

    }
    @Override
    public AdapterListBarangTransaksi.HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_transaksi_barang,parent, false);
        //AdapterListBarangTransaksi.HolderData holder = new AdapterListBarangTransaksi.HolderData(layout);
        //return holder;
        return new AdapterListBarangTransaksi.HolderData(layout);
    }

    @Override
    public void onBindViewHolder(AdapterListBarangTransaksi.HolderData holder, int position) {

        DataModelTransaksiUser dataModelTransaksiUser = mList.get(position);
        Log.e("", "CEK LIST: "+dataModelTransaksiUser.getBarang().getNama());
        holder.nama.setText(dataModelTransaksiUser.getBarang().getNama());
        holder.harga.setText(String.valueOf(dataModelTransaksiUser.getBarang().getHarga()));
//        new DownloadImageTask(holder.gambar)
//                .execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");
        //Picasso.get().load(dm.getGambar()).into(holder.gambar);

        //holder.dm = dataModelTransaksiUser;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HolderData extends  RecyclerView.ViewHolder{
        TextView nama, harga;
        //ImageView gambar;
        DataModelTransaksiUser dm;
        public HolderData (View v)
        {
            super(v);
            nama  = (TextView) v.findViewById(R.id.tv_namabarang);
            harga = (TextView) v.findViewById(R.id.tv_harga);
        }
    }
}
