package arumfaisal.id.tokogayeng;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import arumfaisal.id.tokogayeng.adapter.AdapterBarang;
import arumfaisal.id.tokogayeng.api.ApiRequestBarang;
import arumfaisal.id.tokogayeng.api.Retroserver;
import arumfaisal.id.tokogayeng.model.DataModelBarang;
import arumfaisal.id.tokogayeng.model.ResponsModelBarang;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarBarangActivity extends AppCompatActivity {
    private String token,nama_toko,toko_id;
    private RecyclerView.Adapter adapterBarang;
    private RecyclerView recyclerBarang;
    private List<DataModelBarang> itemBarang = new ArrayList<>();

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
        //nama_toko = intent.getStringExtra("nama_toko");
        toko_id = intent.getStringExtra("toko_id");

        recyclerBarang = (RecyclerView) findViewById(R.id.list_barang);
        setView();
        getListBarang();
    }


    private void getListBarang(){
        ApiRequestBarang api = Retroserver.getClient().create(ApiRequestBarang.class);
        Call<ResponsModelBarang> getData=api.getBarangByUser(token);
        getData.enqueue(new Callback<ResponsModelBarang>() {
            @Override
            public void onResponse(Call<ResponsModelBarang> call, Response<ResponsModelBarang> response) {
                Log.e("", "onResponse: "+response.body());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.daftar_transaksi){
            Intent intent = new Intent(getApplicationContext(),TransaksiActivity.class);
            intent.putExtra("token",token);
            intent.putExtra("toko_id",toko_id);
            startActivity(intent);
        }
        return true;
    }

    private void setView(){
        tvTotalHarga = (TextView) findViewById(R.id.total_harga);
        tvJudul = (TextView) findViewById(R.id.judulToko);

        tvJudul.setText(nama_toko);
    }

    public void tambahBarang(View view){
        Intent intent = new Intent(getApplicationContext(),AddBarangActivity.class);
        intent.putExtra("token",token);
        intent.putExtra("toko_id",toko_id);
        startActivity(intent);
    }
}
