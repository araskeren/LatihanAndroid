package damisbachtiar17.web.id.uts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import damisbachtiar17.web.id.uts.api.ApiRequestTransaksi;
import damisbachtiar17.web.id.uts.api.Retroserver;
import damisbachtiar17.web.id.uts.model.ResponsModelTransaksi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBarang extends AppCompatActivity {
    TextView namaBarang,hargaBarang,deskripsiBarang;
    ImageView gambarBarang;
    String totalHarga,barang_id,geray_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);
        namaBarang = findViewById(R.id.judul);
        hargaBarang = findViewById(R.id.harga);
        deskripsiBarang = findViewById(R.id.deskripsi);
        gambarBarang = findViewById(R.id.gambar);
        Intent intent = getIntent();

        barang_id=intent.getStringExtra("barang_id");
        geray_id=intent.getStringExtra("geray_id");
        namaBarang.setText(intent.getStringExtra("judul"));
        hargaBarang.setText(intent.getStringExtra("harga"));
        deskripsiBarang.setText(intent.getStringExtra("deskripsi"));
        totalHarga=intent.getStringExtra("total");
        Picasso.get().load(intent.getStringExtra("gambar")).into(gambarBarang);


//        if(getIntent().hasExtra("gambar")) {
//            byte[] byteArray = intent.getByteArrayExtra("gambar");
//            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//
//            gambarBarang.setImageBitmap(bmp);
//        }
    }

    public void tambah(View view){
        insertToDatabase();
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        //Integer total=Integer.parseInt(totalHarga)+Integer.parseInt(hargaBarang.getText().toString());
        //Log.d("TEST", "tambah: "+total);
        //intent.putExtra("harga",total.toString());
        startActivity(intent);
    }

    private void insertToDatabase(){
        ApiRequestTransaksi api = Retroserver.getClient().create(ApiRequestTransaksi.class);
        Call<ResponsModelTransaksi> getData=api.insertTransaksi(barang_id,geray_id,"check");
        getData.enqueue(new Callback<ResponsModelTransaksi>() {
            @Override
            public void onResponse(Call<ResponsModelTransaksi> call, Response<ResponsModelTransaksi> response) {
                ResponsModelTransaksi resp = response.body();
                if(resp!=null){
                    Toast.makeText(getBaseContext(), "BARANG BERHASIL DI TAMBAHKAN KE TROLI !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    intent.putExtra("geray_id",geray_id);
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
