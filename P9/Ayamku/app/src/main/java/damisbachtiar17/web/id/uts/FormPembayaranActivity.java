package damisbachtiar17.web.id.uts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class FormPembayaranActivity extends AppCompatActivity {

    EditText totalTransaksi,totalPembayaran,kembalian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pembayaran);
        totalTransaksi = findViewById(R.id.total_transaksi);
        totalPembayaran= findViewById(R.id.total_pembayaran);
        kembalian      = findViewById(R.id.kembalian);

        Intent intent = getIntent();
        totalTransaksi.setText(intent.getStringExtra("total"));
        bind();
    }

    private void bind(){
        totalPembayaran.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setKembalian();
            }
        });
    }

    private void setKembalian(){
        Log.d("TEST", "setKembalian: "+totalPembayaran.getText().toString().length());
        if(totalPembayaran.getText().toString().length()>3){
        Integer transaksi=Integer.parseInt(totalTransaksi.getText().toString());
        Integer pembayaran=Integer.parseInt(totalPembayaran.getText().toString());


            Integer kembali = pembayaran-transaksi;

            kembalian.setText(kembali.toString());
        }else{
            kembalian.setText("0");
        }
    }
}
