package damisbachtiar17.web.id.pasargayeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class HalamanPembayaranActivity extends AppCompatActivity {
    private String token,kode,total_bayar;
    EditText totalTransaksi,kodePembayaran;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        totalTransaksi = findViewById(R.id.total_transaksi);
        kodePembayaran = findViewById(R.id.kode_pembayaran);

        Intent intent = getIntent();
        token = intent.getStringExtra("token").toString();
        kode = intent.getStringExtra("kode").toString();
        total_bayar = intent.getStringExtra("total_bayar").toString();

        Log.e("TAG", "onCreate: "+kode );
        totalTransaksi.setText(total_bayar);
        kodePembayaran.setText(kode);
    }

    public void beranda(View view){
        Intent intent = new Intent(getApplicationContext(),DaftarTokoActivity.class);
        intent.putExtra("token",token);
        startActivity(intent);
    }
}
