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

public class DetailBarang extends AppCompatActivity {
    TextView namaBarang,hargaBarang,deskripsiBarang;
    ImageView gambarBarang;
    String totalHarga;

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


//        if(getIntent().hasExtra("gambar")) {
//            byte[] byteArray = intent.getByteArrayExtra("gambar");
//            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//
//            gambarBarang.setImageBitmap(bmp);
//        }
    }

    public void tambah(View view){
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        Integer total=Integer.parseInt(totalHarga)+Integer.parseInt(hargaBarang.getText().toString());
        Log.d("TEST", "tambah: "+total);
        intent.putExtra("harga",total.toString());
        startActivity(intent);
    }
}
