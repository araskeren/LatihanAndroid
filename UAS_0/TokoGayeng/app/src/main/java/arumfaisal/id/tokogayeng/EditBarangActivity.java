package arumfaisal.id.tokogayeng;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import arumfaisal.id.tokogayeng.api.ApiRequestBarang;
import arumfaisal.id.tokogayeng.api.Retroserver;
import arumfaisal.id.tokogayeng.model.ResponsModelAddBarang;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditBarangActivity extends AppCompatActivity {
    Button btnHapus,btnSave;
    EditText edNama,edHarga,edDeskripsi,edGambar;
    String token,id_barang,toko_id,nama,harga,deskripsi,gambar;
    Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        bind();
        bindListener();
    }

    private void bind(){

        edNama = findViewById(R.id.edNama);
        edHarga= findViewById(R.id.edHarga);
        edDeskripsi= findViewById(R.id.edDeskripsi);
        edGambar= findViewById(R.id.edGambar);
        btnSave=findViewById(R.id.btnSave);
        btnHapus=findViewById(R.id.btnHapus);

        intent = getIntent();

        token = intent.getStringExtra("token").toString();
        id_barang = intent.getStringExtra("id").toString();
        toko_id=intent.getStringExtra("toko_id").toString();
        nama = intent.getStringExtra("nama").toString();
        harga=intent.getStringExtra("harga").toString();
        deskripsi=intent.getStringExtra("deskripsi").toString();
        if(intent.hasExtra("gambar") && intent.getStringExtra("gambar")!=null){
            gambar=intent.getStringExtra("gambar").toString();
        }else{
            gambar="";
        }

        Log.e("bind", "bind: "+toko_id);
        edNama.setText(nama);
        edHarga.setText(harga);
        edGambar.setText(gambar);
        edDeskripsi.setText(deskripsi);
    }

    private void bindListener(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nama=edNama.getText().toString();
                harga=edHarga.getText().toString();
                gambar=edGambar.getText().toString();
                deskripsi=edDeskripsi.getText().toString();

                ApiRequestBarang api = Retroserver.getClient().create(ApiRequestBarang.class);
                Call<ResponsModelAddBarang> getData=api.editBarang(token,id_barang,nama,harga,deskripsi,gambar);

                getData.enqueue(new Callback<ResponsModelAddBarang>() {
                    @Override
                    public void onResponse(Call<ResponsModelAddBarang> call, Response<ResponsModelAddBarang> response) {
                        Log.e("onResponse", "onResponse: "+response.body());
                        Toast.makeText(getBaseContext(), "BERHASIL DI UPDATE !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),DaftarBarangActivity.class);
                        intent.putExtra("token",token);
                        intent.putExtra("toko_id",toko_id);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponsModelAddBarang> call, Throwable t) {
                        Log.d("RETRO", "FAILED : respon gagal");
                        Toast.makeText(getBaseContext(), "BERHASIL DI UPDATE !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),DaftarBarangActivity.class);
                        intent.putExtra("token",token);
                        intent.putExtra("toko_id",toko_id);
                        startActivity(intent);
                    }
                });
            }
        });
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiRequestBarang api = Retroserver.getClient().create(ApiRequestBarang.class);
                Call<ResponsModelAddBarang> getData=api.deleteBarang(token,id_barang);

                getData.enqueue(new Callback<ResponsModelAddBarang>() {
                    @Override
                    public void onResponse(Call<ResponsModelAddBarang> call, Response<ResponsModelAddBarang> response) {
                        Log.e("onResponse", "onResponse: "+response.body());
                        Toast.makeText(getBaseContext(), "BERHASIL DI UPDATE !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),DaftarBarangActivity.class);
                        intent.putExtra("token",token);
                        intent.putExtra("toko_id",toko_id);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ResponsModelAddBarang> call, Throwable t) {
                        Log.d("RETRO", "FAILED : respon gagal");
                        Toast.makeText(getBaseContext(), "BERHASIL DI HAPUS !", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),DaftarBarangActivity.class);
                        intent.putExtra("token",token);
                        intent.putExtra("toko_id",toko_id);
                        startActivity(intent);

                    }
                });
            }
        });
    }


}
