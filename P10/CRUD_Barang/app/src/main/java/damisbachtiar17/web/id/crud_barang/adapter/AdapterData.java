package damisbachtiar17.web.id.crud_barang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import damisbachtiar17.web.id.crud_barang.MainActivity;
import damisbachtiar17.web.id.crud_barang.R;
import damisbachtiar17.web.id.crud_barang.model.DataModel;

import java.util.List;
public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {

    private List<DataModel> mList ;
    private Context ctx;


    public  AdapterData (Context ctx, List<DataModel> mList)
    {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list,parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        DataModel dm = mList.get(position);
        Log.d("TAG", "onBindViewHolder: "+dm.getNama().toString());
        holder.nama.setText(dm.getNama());
        holder.harga_beli.setText(dm.getHargaBeli().toString());
        holder.harga_jual.setText(dm.getHargaJual().toString());
        holder.stock.setText(dm.getStock().toString());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder{
        TextView nama, harga_jual, harga_beli,stock;
        DataModel dm;
        public HolderData (View v)
        {
            super(v);

            nama  = (TextView) v.findViewById(R.id.txtNama);
            harga_jual = (TextView) v.findViewById(R.id.txtHargaJual);
            harga_beli = (TextView) v.findViewById(R.id.txtHargaBeli);
            stock = (TextView) v.findViewById(R.id.txtStock);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx,MainActivity.class);
                    Log.d("onClick", "onClick: "+dm.getId());
                    Log.d("onClick", "onClick: "+dm.getNama());
                    goInput.putExtra("id", dm.getId());
                    goInput.putExtra("nama", dm.getNama());
                    goInput.putExtra("harga_beli", dm.getHargaBeli());
                    goInput.putExtra("harga_jual", dm.getHargaJual());
                    goInput.putExtra("stock", dm.getStock());

                    ctx.startActivity(goInput);
                }
            });
        }
    }
}