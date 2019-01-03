package arumfaisal.id.tokogayeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import arumfaisal.id.tokogayeng.api.ApiRequestBarang;
import arumfaisal.id.tokogayeng.api.ApiRequestTransaksi;
import arumfaisal.id.tokogayeng.api.Retroserver;
import arumfaisal.id.tokogayeng.model.ResponsModelAddBarang;
import arumfaisal.id.tokogayeng.model.ResponsModelTransaksiBarangUpdate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransaksiActivity extends AppCompatActivity {
    Button btnKonfirmasiPengiriman;
    TextView tvNamaBarang,tvHargaBarang,tvNamaPembeli,tvNoTelp,tvAlamat;
    String token,transaksi_id,barang_id,toko_id,nama_barang,harga_barang,nama_pembeli,no_telp,alamat;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);

        bind();
        bindListener();
    }

    private void bind(){

        tvNamaBarang = findViewById(R.id.tv_namabarang);
        tvHargaBarang= findViewById(R.id.tv_harga);
        tvNamaPembeli= findViewById(R.id.tv_nama_pembeli);
        tvNoTelp= findViewById(R.id.tv_no_telp);
        tvAlamat=findViewById(R.id.tv_alamat);
        btnKonfirmasiPengiriman=findViewById(R.id.btnKonfirmasiPengiriman);

        intent = getIntent();

        token = intent.getStringExtra("token").toString();
        transaksi_id = intent.getStringExtra("transaksi_id");
        toko_id=intent.getStringExtra("toko_id").toString();
        nama_barang = intent.getStringExtra("nama_barang").toString();
        harga_barang=intent.getStringExtra("harga_barang").toString();
        nama_pembeli=intent.getStringExtra("nama_pembeli").toString();
        no_telp=intent.getStringExtra("no_telp").toString();
        alamat=intent.getStringExtra("alamat").toString();

        Log.e("bind", "bind: "+toko_id);
        tvNamaBarang.setText(nama_barang);
        tvHargaBarang.setText(harga_barang);
        tvNamaPembeli.setText(nama_pembeli);
        tvNoTelp.setText(no_telp);
        tvAlamat.setText(alamat);
    }

    private void bindListener(){
        btnKonfirmasiPengiriman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiRequestTransaksi api = Retroserver.getClient().create(ApiRequestTransaksi.class);
                Log.e("onClick", "onClick: "+transaksi_id );
                Call<ResponsModelTransaksiBarangUpdate> getData=api.updateTransaksiByTransaksiId(token,transaksi_id,"terkirim");
                getData.enqueue(new Callback<ResponsModelTransaksiBarangUpdate>() {
                    @Override
                    public void onResponse(Call<ResponsModelTransaksiBarangUpdate> call, Response<ResponsModelTransaksiBarangUpdate> response) {
                        Toast.makeText(getBaseContext(), "BARANG BERHASIL TERKIRIM !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),TransaksiActivity.class);
                        intent.putExtra("token",token);
                        intent.putExtra("toko_id",toko_id);
                        startActivity(intent);
                        Log.d("RETRO", "BERHASIL :"+response.body().getKode());
                    }

                    @Override
                    public void onFailure(Call<ResponsModelTransaksiBarangUpdate> call, Throwable t) {
                        Log.d("RETRO", "FAILED : respon gagal");

                    }
                });
            }
        });
    }


}
