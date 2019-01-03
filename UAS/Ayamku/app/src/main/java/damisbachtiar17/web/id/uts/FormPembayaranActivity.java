package damisbachtiar17.web.id.uts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import damisbachtiar17.web.id.uts.adapter.AdapterDataAyam;
import damisbachtiar17.web.id.uts.adapter.AdapterDataTransaksi;
import damisbachtiar17.web.id.uts.api.ApiRequestTransaksi;
import damisbachtiar17.web.id.uts.api.Retroserver;
import damisbachtiar17.web.id.uts.model.DataModelTransaksi;
import damisbachtiar17.web.id.uts.model.ResponsModelTransaksi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPembayaranActivity extends AppCompatActivity {

    EditText edTotalTransaksi,edTotalPembayaran;
    private AdapterDataTransaksi adapterDataTransaksi;
    RecyclerView recyclerTransaksi;
    String geray_id,kembalian;
    Button btnBayar;
    ResponsModelTransaksi restTransaksi;
    private String[] transaksi_id = new String[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pembayaran);
        edTotalTransaksi = findViewById(R.id.total_transaksi);
        edTotalPembayaran= findViewById(R.id.total_pembayaran);
        btnBayar = findViewById(R.id.btnBayar);
        Intent intent = getIntent();
        recyclerTransaksi = (RecyclerView)findViewById(R.id.list_daftar_transaksi);
        edTotalTransaksi.setText(intent.getStringExtra("total"));
        geray_id=intent.getStringExtra("geray_id");
        getTransaksi();
        bind();
    }

    private void bind(){
        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer totalTransaksi=Integer.parseInt(edTotalTransaksi.getText().toString());
                String total=edTotalPembayaran.getText().toString();
                if(total.length()<1){
                    Toast.makeText(getBaseContext(), "TOTAL PEMBAYARAN TIDAK BOLEH KOSONG !", Toast.LENGTH_LONG).show();
                }else{
                    Integer totalBayar=Integer.parseInt(total);
                    Integer kembalian = totalBayar-totalTransaksi;
                    if(kembalian<0){
                        Toast.makeText(getBaseContext(), "UANG KURANG !Rp."+kembalian, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getBaseContext(), "KEMBALIAN = Rp."+kembalian, Toast.LENGTH_LONG).show();
                        ApiRequestTransaksi api = Retroserver.getClient().create(ApiRequestTransaksi.class);
                        Call<ResponsModelTransaksi> getData=api.updateTransaksi(transaksi_id,"sukses");
                        Log.e("onClick", "onClick: "+transaksi_id[0]);
                        getData.enqueue(new Callback<ResponsModelTransaksi>() {
                            @Override
                            public void onResponse(Call<ResponsModelTransaksi> call, Response<ResponsModelTransaksi> response) {
                                Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
                                intent.putExtra("geray_id",geray_id);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<ResponsModelTransaksi> call, Throwable t) {
                                Log.d("RETRO", "FAILED : respon gagal");
                                Toast.makeText(getBaseContext(), "CEK KONEKSI ANDA !", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }
        });
    }
    private void getTransaksi(){
        ApiRequestTransaksi api = Retroserver.getClient().create(ApiRequestTransaksi.class);
        Call<ResponsModelTransaksi> getData=api.getTransaksi("check",geray_id);

        getData.enqueue(new Callback<ResponsModelTransaksi>() {
            @Override
            public void onResponse(Call<ResponsModelTransaksi> call, Response<ResponsModelTransaksi> response) {
                //List<DataModelTransaksi> resp = response.body().getResult();
                restTransaksi = response.body();

                for (int i=0;i<restTransaksi.getResult().size();i++){
                    transaksi_id[i]=restTransaksi.getResult().get(i).getTransaksiId();
                }
                Log.e("onResponse", "onResponse: "+transaksi_id[0]);
                Log.e("onResponse", "onResponse: "+new Gson().toJson(restTransaksi.getResult()));

                //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FormPembayaranActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FormPembayaranActivity.this);
                adapterDataTransaksi = new AdapterDataTransaksi(FormPembayaranActivity.this,restTransaksi.getResult(),geray_id);
                //recyclerTransaksi.setLayoutManager(recyclerTransaksi);
                recyclerTransaksi.setLayoutManager(layoutManager);
                adapterDataTransaksi.notifyDataSetChanged();
                recyclerTransaksi.setAdapter(adapterDataTransaksi);
            }

            @Override
            public void onFailure(Call<ResponsModelTransaksi> call, Throwable t) {
                Log.d("RETRO", "FAILED : respon gagal");
                Toast.makeText(getBaseContext(), "CEK KONEKSI ANDA !", Toast.LENGTH_LONG).show();
            }
        });
    }
}
