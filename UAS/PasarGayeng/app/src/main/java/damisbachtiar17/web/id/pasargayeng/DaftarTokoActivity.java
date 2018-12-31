package damisbachtiar17.web.id.pasargayeng;

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

import damisbachtiar17.web.id.pasargayeng.adapter.AdapterToko;
import damisbachtiar17.web.id.pasargayeng.api.ApiRequestToko;
import damisbachtiar17.web.id.pasargayeng.api.ApiRequestTransaksi;
import damisbachtiar17.web.id.pasargayeng.api.Retroserver;
import damisbachtiar17.web.id.pasargayeng.model.DataModelToko;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelToko;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelTransaksiUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarTokoActivity extends AppCompatActivity {
    private String token;
    private RecyclerView.Adapter adapterToko;
    private RecyclerView recyclerToko;
    private List<DataModelToko> itemToko = new ArrayList<>();
    private ResponsModelTransaksiUser itemTransaksi;

    private TextView tvTotalHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_toko);

        Intent intent = getIntent();
        if(intent.hasExtra("harga")){
            String harga = intent.getStringExtra("harga");
        }
        getSupportActionBar().setTitle(intent.getStringExtra("nama_user"));
        token = intent.getStringExtra("token").toString();


        recyclerToko = (RecyclerView) findViewById(R.id.list_toko);
        setView();
        getListToko();
        getDataTransaksi();
    }

    private void setView(){
        tvTotalHarga = (TextView) findViewById(R.id.total_harga);
    }

    private void getListToko(){
        ApiRequestToko api = Retroserver.getClient().create(ApiRequestToko.class);
        Call<ResponsModelToko> getData=api.getAllToko(token);
        getData.enqueue(new Callback<ResponsModelToko>() {
            @Override
            public void onResponse(Call<ResponsModelToko> call, Response<ResponsModelToko> response) {
                itemToko = response.body().getResultBarang();
                if(itemToko!=null){
                    adapterToko = new AdapterToko(itemToko,DaftarTokoActivity.this,token);
                    recyclerToko.setAdapter(adapterToko);
                    adapterToko.notifyDataSetChanged();
                }else{
                    Toast.makeText(getBaseContext(), "GAGAL MENGAMBIL DATA !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelToko> call, Throwable t) {
                Log.d("RETRO", "FAILED : respon gagal");

            }
        });

        // Create the recyclerview.
        RecyclerView recyclerToko = (RecyclerView)findViewById(R.id.list_toko);
        // Create the grid layout manager with 2 columns.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // Set layout manager.
        recyclerToko.setLayoutManager(gridLayoutManager);
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
    public void pembayaran(View view){
        Intent intent = new Intent(getApplicationContext(), FormPembayaranActivity.class);
        intent.putExtra("token",token);
        startActivity(intent);
    }
}
