package arumfaisal.id.paysargayengumum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import arumfaisal.id.paysargayengumum.adapter.AdapterBarang;
import arumfaisal.id.paysargayengumum.api.ApiRequestBarang;
import arumfaisal.id.paysargayengumum.api.ApiRequestTransaksi;
import arumfaisal.id.paysargayengumum.api.Retroserver;
import arumfaisal.id.paysargayengumum.model.DataModelBarang;
import arumfaisal.id.paysargayengumum.model.ResponsModelBarang;
import arumfaisal.id.paysargayengumum.model.ResponsModelTransaksiUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarBarangActivity extends AppCompatActivity {
    private String token,nama_toko,toko_id;
    private RecyclerView.Adapter adapterBarang;
    private RecyclerView recyclerBarang;
    private List<DataModelBarang> itemBarang = new ArrayList<>();
    private ResponsModelTransaksiUser itemTransaksi;

    private TextView tvTotalHarga,tvJudul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_barang);

        Intent intent = getIntent();
        if(intent.hasExtra("harga")){
            String harga = intent.getStringExtra("harga");
        }
        getSupportActionBar().setTitle(intent.getStringExtra("nama"));
        token = intent.getStringExtra("token").toString();
        nama_toko = intent.getStringExtra("nama_toko");
        toko_id = intent.getStringExtra("id");

        recyclerBarang = (RecyclerView) findViewById(R.id.list_barang);
        setView();
        getListBarang();
        getDataTransaksi();
    }

    private void getDataTransaksi(){
        ApiRequestTransaksi api = Retroserver.getClient().create(ApiRequestTransaksi.class);
        Call<ResponsModelTransaksiUser> getData=api.getAllTransaksi(token);
        getData.enqueue(new Callback<ResponsModelTransaksiUser>() {
            @Override
            public void onResponse(Call<ResponsModelTransaksiUser> call, Response<ResponsModelTransaksiUser> response) {
                itemTransaksi = response.body();
                if(itemTransaksi!=null){
                    Log.e("TAG", "onResponse: "+itemTransaksi.getTotal());
                    tvTotalHarga.setText(itemTransaksi.getTotal().toString());
                }else{
                    Toast.makeText(getBaseContext(), "GAGAL MENGAMBIL DATA TRANSAKSI!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelTransaksiUser> call, Throwable t) {
                Log.d("RETRO", "FAILED : respon gagal");

            }
        });
    }

    private void getListBarang(){
        ApiRequestBarang api = Retroserver.getClient().create(ApiRequestBarang.class);
        Call<ResponsModelBarang> getData=api.getByToko(token,toko_id);
        getData.enqueue(new Callback<ResponsModelBarang>() {
            @Override
            public void onResponse(Call<ResponsModelBarang> call, Response<ResponsModelBarang> response) {
                itemBarang = response.body().getResultBarang();
                if(itemBarang!=null){
                    adapterBarang = new AdapterBarang(DaftarBarangActivity.this,itemBarang,token);
                    recyclerBarang.setAdapter(adapterBarang);
                    adapterBarang.notifyDataSetChanged();
                }else{
                    Toast.makeText(getBaseContext(), "GAGAL MENGAMBIL DATA !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelBarang> call, Throwable t) {
                Log.d("RETRO", "FAILED : respon gagal");

            }
        });

        // Create the recyclerview.
        RecyclerView recyclerBarang = (RecyclerView)findViewById(R.id.list_barang);
        // Create the grid layout manager with 2 columns.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // Set layout manager.
        recyclerBarang.setLayoutManager(gridLayoutManager);
    }

    private void setView(){
        tvTotalHarga = (TextView) findViewById(R.id.total_harga);
        tvJudul = (TextView) findViewById(R.id.judulToko);

        tvJudul.setText(nama_toko);
    }

    public void pembayaran(View view){
        Intent intent = new Intent(getApplicationContext(), FormPembayaranActivity.class);
        intent.putExtra("token",token);
        startActivity(intent);
    }
}
