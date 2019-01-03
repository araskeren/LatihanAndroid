package arumfaisal.id.tokogayeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import arumfaisal.id.tokogayeng.adapter.AdapterListTransaksiBarang;
import arumfaisal.id.tokogayeng.api.ApiRequestTransaksi;
import arumfaisal.id.tokogayeng.api.Retroserver;
import arumfaisal.id.tokogayeng.model.DataModelTransaksiBarang;
import arumfaisal.id.tokogayeng.model.ResponsModelTransaksiBarang;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterBarang;
    private String token,toko_id;
    private RecyclerView recyclerBarang;
    private ResponsModelTransaksiBarang itemTransaksi;
    private AdapterListTransaksiBarang adapterListTransaksiBarang;
    private String[] id=new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_transaksi);
        recyclerBarang = (RecyclerView) findViewById(R.id.list_barang);
        recyclerBarang.setHasFixedSize(true);

        Intent intent = getIntent();
        token = intent.getStringExtra("token").toString();
        toko_id=intent.getStringExtra("toko_id").toString();
        getDataTransaksi();
    }


    private void getDataTransaksi(){
        ApiRequestTransaksi api = Retroserver.getClient().create(ApiRequestTransaksi.class);

        Call<ResponsModelTransaksiBarang> getData=api.getAllTransaksiToko(token,toko_id,"menunggu pengiriman");
        getData.enqueue(new Callback<ResponsModelTransaksiBarang>() {
            @Override
            public void onResponse(Call<ResponsModelTransaksiBarang> call, Response<ResponsModelTransaksiBarang> response) {
                itemTransaksi = response.body();
                Log.e("onResponse", "onResponse: "+itemTransaksi);
                if(itemTransaksi!=null){
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TransaksiActivity.this);
                    adapterListTransaksiBarang = new AdapterListTransaksiBarang(itemTransaksi.getResultBarang(),TransaksiActivity.this, token);
                    recyclerBarang.setLayoutManager(layoutManager);
                    adapterListTransaksiBarang.notifyDataSetChanged();
                    recyclerBarang.setAdapter(adapterListTransaksiBarang);

                    int i=0;
                    for (DataModelTransaksiBarang dataModelTransaksiBarang : itemTransaksi.getResultBarang()){
                        id[i]=dataModelTransaksiBarang.getId().toString();
                        i+=1;
                    }
                    Log.e("idBarang", ""+id[0]);
                }else{
                    Toast.makeText(getBaseContext(), "GAGAL MENGAMBIL DATA TRANSAKSI!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelTransaksiBarang> call, Throwable t) {
                Log.d("RETRO", "FAILED : respon gagal");

            }
        });
    }
}
