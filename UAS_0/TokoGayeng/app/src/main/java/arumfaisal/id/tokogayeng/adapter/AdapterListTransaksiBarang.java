package arumfaisal.id.tokogayeng.adapter;

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

import arumfaisal.id.tokogayeng.DetailTransaksiActivity;
import arumfaisal.id.tokogayeng.EditBarangActivity;
import arumfaisal.id.tokogayeng.R;
import arumfaisal.id.tokogayeng.model.DataModelTransaksiBarang;
import arumfaisal.id.tokogayeng.model.ResponsModelTransaksiBarang;

public class AdapterListTransaksiBarang extends RecyclerView.Adapter<AdapterListTransaksiBarang.HolderData> {
    private List<DataModelTransaksiBarang> mList ;
    private Context ctx;
    private String token;
    private ResponsModelTransaksiBarang dataModelTransaksiBarang;
    ArrayList<String> alist=new ArrayList<String>();


    public AdapterListTransaksiBarang(List<DataModelTransaksiBarang> mList, Context ctx, String token) {
        Log.e("dataMasuk : ",""+ new Gson().toJson(mList));
        this.mList = mList;
        this.ctx = ctx;
        this.token = token;

    }
    @Override
    public AdapterListTransaksiBarang.HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_transaksi,parent, false);
        //AdapterListBarangTransaksi.HolderData holder = new AdapterListBarangTransaksi.HolderData(layout);
        //return holder;
        return new AdapterListTransaksiBarang.HolderData(layout);
    }

    @Override
    public void onBindViewHolder(AdapterListTransaksiBarang.HolderData holder, int position) {

        DataModelTransaksiBarang dataModelTransaksiBarang = mList.get(position);
        holder.nama.setText(dataModelTransaksiBarang.getNamaBarang());
        holder.harga.setText(String.valueOf(dataModelTransaksiBarang.getHarga()));
        holder.dm = dataModelTransaksiBarang;

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
        DataModelTransaksiBarang dm;
        public HolderData (View v)
        {
            super(v);
            nama  = (TextView) v.findViewById(R.id.tv_namabarang);
            harga = (TextView) v.findViewById(R.id.tv_harga);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.e("onClick", "onClick: "+dm.getId());
                    Intent goInput = new Intent(ctx,DetailTransaksiActivity.class);
                    goInput.putExtra("transaksi_id", String.valueOf(dm.getId()));
                    goInput.putExtra("nama_barang", dm.getNamaBarang());
                    goInput.putExtra("harga_barang", String.valueOf(dm.getHarga()));
                    goInput.putExtra("nama_pembeli", dm.getNamaPembeli());
                    goInput.putExtra("no_telp", dm.getNoTelp());
                    goInput.putExtra("alamat", dm.getAlamat());

                    goInput.putExtra("toko_id",String.valueOf(dm.getTokoId()));
                    goInput.putExtra("token", token);

                    ctx.startActivity(goInput);
                }
            });
        }
    }
}
