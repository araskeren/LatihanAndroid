package damisbachtiar17.web.id.pasargayeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import damisbachtiar17.web.id.pasargayeng.adapter.AdapterBarang;
import damisbachtiar17.web.id.pasargayeng.adapter.AdapterListBarangTransaksi;
import damisbachtiar17.web.id.pasargayeng.api.ApiRequestTransaksi;
import damisbachtiar17.web.id.pasargayeng.api.Retroserver;
import damisbachtiar17.web.id.pasargayeng.model.DataModelTransaksi;
import damisbachtiar17.web.id.pasargayeng.model.DataModelTransaksiUser;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelTransaksi;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelTransaksiUpdate;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelTransaksiUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPembayaranActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterBarang;
    EditText totalTransaksi;
    private String token;
    private RecyclerView recyclerBarang;
    private ResponsModelTransaksiUser itemTransaksi;
    private AdapterListBarangTransaksi adapterListBarangTransaksi;
    private String[] id=new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pembayaran);
        totalTransaksi = findViewById(R.id.total_transaksi);
        recyclerBarang = (RecyclerView) findViewById(R.id.list_barang);
        recyclerBarang.setHasFixedSize(true);

        Intent intent = getIntent();
        token = intent.getStringExtra("token").toString();
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
                    totalTransaksi.setText(itemTransaksi.getTotal().toString());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FormPembayaranActivity.this);
                    adapterListBarangTransaksi = new AdapterListBarangTransaksi(itemTransaksi.getResultBarang(),FormPembayaranActivity.this, token);
                    recyclerBarang.setLayoutManager(layoutManager);
                    adapterListBarangTransaksi.notifyDataSetChanged();
                    recyclerBarang.setAdapter(adapterListBarangTransaksi);

                    int i=0;
                    for (DataModelTransaksiUser dataModelTransaksiUser : itemTransaksi.getResultBarang()){
                        id[i]=dataModelTransaksiUser.getId().toString();
                        i+=1;
                    }
                    Log.e("idBarang", ""+id[0]);

                    // Create the recyclerview.
                    //RecyclerView recyclerBarang = (RecyclerView)findViewById(R.id.list_barang);
                    // Create the grid layout manager with 2 columns.
                    //GridLayoutManager gridLayoutManager = new GridLayoutManager(FormPembayaranActivity.this, 1);
                    // Set layout manager.
                    //recyclerBarang.setLayoutManager(gridLayoutManager);
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
        Log.e("", "pembayaran: "+itemTransaksi.getResultBarang());
        ApiRequestTransaksi api = Retroserver.getClient().create(ApiRequestTransaksi.class);
        Call<ResponsModelTransaksiUpdate> getData=api.updateTransaksi(token,id,"pembayaran");
        getData.enqueue(new Callback<ResponsModelTransaksiUpdate>() {
            @Override
            public void onResponse(Call<ResponsModelTransaksiUpdate> call, Response<ResponsModelTransaksiUpdate> response) {
                Toast.makeText(getBaseContext(), "BERHASIL CHECKOUT !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),HalamanPembayaranActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("kode",response.body().getKode().toString());
                intent.putExtra("total_bayar",totalTransaksi.getText().toString());
                startActivity(intent);
                Log.d("RETRO", "BERHASIL :"+response.body().getKode());
            }

            @Override
            public void onFailure(Call<ResponsModelTransaksiUpdate> call, Throwable t) {
                Log.d("RETRO", "FAILED : respon gagal");

            }
        });

    }

}
