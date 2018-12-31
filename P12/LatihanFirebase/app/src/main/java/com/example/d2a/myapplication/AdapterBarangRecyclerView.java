package com.example.d2a.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.d2a.myapplication.R;
import com.example.d2a.myapplication.Barang;


public class AdapterBarangRecyclerView extends RecyclerView.Adapter<AdapterBarangRecyclerView.ViewHolder> {

    private ArrayList<Barang> daftarBarang;
    private Context context;
    FirebaseDataListener listener;

    public AdapterBarangRecyclerView(ArrayList<Barang> barangs, Context ctx){
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */
        daftarBarang = barangs;
        context = ctx;
        listener = (FirebaseDataListener)ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView tvNama,tvSatuan,tvHargaBeli,tvHargaJual,tvStock,tvStockMin;
        LinearLayout list_view_barang;

        ViewHolder(View v) {
            super(v);
            tvNama = (TextView) v.findViewById(R.id.tv_namabarang);
            tvSatuan = (TextView) v.findViewById(R.id.tv_satuan);
            tvHargaBeli = (TextView) v.findViewById(R.id.tv_harga_beli);
            tvHargaJual = (TextView) v.findViewById(R.id.tv_harga_jual);
            tvStock = (TextView) v.findViewById(R.id.tv_stock);
            tvStockMin = (TextView) v.findViewById(R.id.tv_stock_min);
            list_view_barang = (LinearLayout) v.findViewById(R.id.barang);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Inisiasi ViewHolder
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        /**
         *  Menampilkan data pada view
         */
        final String nama = daftarBarang.get(position).getNama();
        final String satuan = daftarBarang.get(position).getSatuan();
        final String harga_beli = daftarBarang.get(position).getHarga_beli();
        final String harga_jual = daftarBarang.get(position).getHarga_jual();
        final String stock = daftarBarang.get(position).getStock();
        final String stock_min = daftarBarang.get(position).getStock_min();


        holder.list_view_barang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Read detail data
                 */
            }
        });

        holder.list_view_barang.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Delete dan update data
                 */
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_view);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = (Button) dialog.findViewById(R.id.bt_edit_data);
                Button delButton = (Button) dialog.findViewById(R.id.bt_delete_data);

                //apabila tombol edit diklik
                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                context.startActivity(FirebaseDBCreate.getActIntent((Activity) context)
                                        .putExtra("data", daftarBarang.get(position))
                                        .putExtra("nama",nama)
                                        .putExtra("satuan",satuan)
                                        .putExtra("harga_beli",harga_beli)
                                        .putExtra("harga_jual",harga_jual)
                                        .putExtra("stock",stock)
                                        .putExtra("stock_min",stock_min));
                            }
                        }
                );

                //apabila tombol delete diklik
                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                /**
                                 *  Kodingan untuk Delete data (memanggil interface delete data)
                                 */
                                dialog.dismiss();
                                listener.onDeleteData(daftarBarang.get(position), position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvNama.setText(nama);
        holder.tvSatuan.setText(satuan);
        holder.tvHargaBeli.setText(harga_beli);
        holder.tvHargaJual.setText(harga_jual);
        holder.tvStock.setText(stock);
        holder.tvStockMin.setText(stock_min);

    }

    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item pada barang
         */
        return daftarBarang.size();
    }
    public interface FirebaseDataListener{
        void onDeleteData(Barang barang, int position);
    }
}
