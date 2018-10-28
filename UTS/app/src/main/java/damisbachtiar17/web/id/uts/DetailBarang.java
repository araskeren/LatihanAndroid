package damisbachtiar17.web.id.uts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailBarang extends AppCompatActivity {
    TextView namaBarang,hargaBarang,deskripsiBarang;
    ImageView gambarBarang;
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
//        if(getIntent().hasExtra("gambar")) {
//            byte[] byteArray = intent.getByteArrayExtra("gambar");
//            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//
//            gambarBarang.setImageBitmap(bmp);
//        }
    }
}
