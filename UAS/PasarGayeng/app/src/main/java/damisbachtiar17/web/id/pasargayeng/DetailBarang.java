package damisbachtiar17.web.id.pasargayeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import damisbachtiar17.web.id.pasargayeng.api.ApiRequestTransaksi;
import damisbachtiar17.web.id.pasargayeng.api.Retroserver;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelTransaksi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBarang extends AppCompatActivity {
    TextView namaBarang,hargaBarang,deskripsiBarang;
    ImageView gambarBarang;
    String totalHarga;
    private String token,barang_id;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);
        namaBarang = findViewById(R.id.judul);
        hargaBarang = findViewById(R.id.harga);
        deskripsiBarang = findViewById(R.id.deskripsi);
        gambarBarang = findViewById(R.id.gambar);
        Intent intent = getIntent();


        namaBarang.setText(intent.getStringExtra("judul"));
        hargaBarang.setText(intent.getStringExtra("harga"));
        deskripsiBarang.setText(intent.getStringExtra("deskripsi"));
        totalHarga=intent.getStringExtra("total");
        token = intent.getStringExtra("token");
        barang_id = intent.getStringExtra("id");
        Picasso.get().load(intent.getStringExtra("gambar")).into(gambarBarang);

        intent = new Intent(getApplicationContext(), DaftarBarangActivity.class);
//        if(getIntent().hasExtra("gambar")) {
//            byte[] byteArray = intent.getByteArrayExtra("gambar");
//            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//
//            gambarBarang.setImageBitmap(bmp);
//        }
    }

    public void tambah(View view){
        ApiRequestTransaksi api = Retroserver.getClient().create(ApiRequestTransaksi.class);
        Call<ResponsModelTransaksi> getData=api.addTransaksi(token,barang_id);
        getData.enqueue(new Callback<ResponsModelTransaksi>() {
            @Override
            public void onResponse(Call<ResponsModelTransaksi> call, Response<ResponsModelTransaksi> response) {
                ResponsModelTransaksi resp = response.body();
                if(resp!=null){
                    Toast.makeText(getBaseContext(), "BARANG BERHASIL DI TAMBAHKAN KE TROLI !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), DaftarTokoActivity.class);
                    intent.putExtra("token",token);
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(), "TRANSAKSI GAGAL,SILAHKAN ULANGI KEMBALI !", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsModelTransaksi> call, Throwable t) {
                Log.d("RETRO", "FAILED : respon gagal");
                Toast.makeText(getBaseContext(), "TRANSAKSI GAGAL,SILAHKAN ULANGI KEMBALI !", Toast.LENGTH_LONG).show();
            }
        });
    }
}
