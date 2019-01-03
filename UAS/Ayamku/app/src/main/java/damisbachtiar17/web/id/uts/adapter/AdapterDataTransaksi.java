package damisbachtiar17.web.id.uts.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import damisbachtiar17.web.id.uts.R;
import damisbachtiar17.web.id.uts.model.DataModelTransaksi;
import damisbachtiar17.web.id.uts.model.ResponsModelTransaksi;

public class AdapterDataTransaksi extends RecyclerView.Adapter<AdapterDataTransaksi.HolderData> {

    private List<DataModelTransaksi> mList ;
    private Context ctx;
    private String geray_id;
    private ResponsModelTransaksi rest_transaksi;

    public  AdapterDataTransaksi (Context ctx, List<DataModelTransaksi> mList,String geray_id)
    {
        this.ctx = ctx;
        this.mList = mList;
        this.geray_id=geray_id;
        Log.e("dataHargaId ",""+new Gson().toJson(mList));
        Log.e("dataThis ",""+new Gson().toJson(this.mList));
    }

    @Override
    public AdapterDataTransaksi.HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_transaksi,parent, false);
        //AdapterDataTransaksi.HolderData holder = new AdapterDataTransaksi.HolderData(layout);
        return new AdapterDataTransaksi.HolderData(layout);
    }

    @Override
    public void onBindViewHolder(AdapterDataTransaksi.HolderData holder, int position) {
        DataModelTransaksi dm = mList.get(position);
        Log.e("cekAdapeter ",""+dm);
        holder.nama.setText(dm.getNamaBarang());
        holder.harga.setText(dm.getHarga());
        //holder.dm = dm;
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder{
        TextView nama, harga;
        DataModelTransaksi dm;
        public HolderData (View v)
        {
            super(v);
            nama  = (TextView) v.findViewById(R.id.nama_barang);
            harga = (TextView) v.findViewById(R.id.harga_barang);
        }
    }
}