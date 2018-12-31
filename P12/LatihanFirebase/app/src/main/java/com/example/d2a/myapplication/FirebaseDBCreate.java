package com.example.d2a.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class FirebaseDBCreate extends AppCompatActivity {
// variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;
    // variable fields EditText dan Button
    private Button btSubmit;
    private EditText edNama,edSatuan,edHargaBeli,edHargaJual,edStock,edStockMin;
    private String nama,satuan,harga_beli,harga_jual,stock,stock_min;
    private TextView tvJudul;
    private  Boolean editBarang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_dbcreate);
        Intent intent = getIntent();

        // inisialisasi fields EditText dan Button
        edNama = (EditText) findViewById(R.id.et_namabarang);
        edSatuan = (EditText) findViewById(R.id.et_satuan);
        edHargaBeli = (EditText) findViewById(R.id.et_harga_beli);
        edHargaJual = (EditText) findViewById(R.id.et_harga_jual);
        edStock = (EditText )findViewById(R.id.et_stock);
        edStockMin = (EditText) findViewById(R.id.et_stock_min);
        btSubmit = (Button) findViewById(R.id.bt_submit);
        tvJudul = (TextView) findViewById(R.id.judul);
        //Cek apakah ada data
        if(intent.hasExtra("data")){
            tvJudul.setText("Edit Data");
            Log.e("TAG", "onCreate: "+intent.getStringExtra("data"));
            getDataFromIntent(intent);
            setDataToView();
        }else{
            tvJudul.setText("Tambah Data");
            editBarang=false;
        }

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();
        // kode yang dipanggil ketika tombol Submit diklik
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(
                    View view) {
                if(!isEmpty(edNama.getText().toString()) &&
                        !isEmpty(edSatuan.getText().toString()) &&
                        !isEmpty(edHargaBeli.getText().toString())&&
                        !isEmpty(edHargaJual.getText().toString()) &&
                        !isEmpty(edStock.getText().toString()) &&
                        !isEmpty(edStockMin.getText().toString()))

                    submitBarang(new Barang(edNama.getText().toString(),
                            edSatuan.getText().toString(),
                            edHargaBeli.getText().toString(),
                            edHargaJual.getText().toString(),
                            edStock.getText().toString(),
                            edStockMin.getText().toString()));
                else
                    Snackbar.make(findViewById(R.id.bt_submit), "Data barang tidak boleh kosong", Snackbar.LENGTH_LONG).show();
                            InputMethodManager imm = (InputMethodManager)
                                    getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        edNama.getWindowToken(), 0);
            }
        });
    }
    private boolean isEmpty(String s) {
    // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }
    private void updateBarang(Barang barang) {
    // kodingan untuk next tutorial ya :p
    }

    private void getDataFromIntent(Intent intent){
        nama = intent.getStringExtra("nama");
        satuan = intent.getStringExtra("satuan");
        harga_beli = intent.getStringExtra("harga_beli");
        harga_jual = intent.getStringExtra("harga_jual");
        stock = intent.getStringExtra("stock");
        stock_min = intent.getStringExtra("stock_min");
    }

    private void setDataToView(){
        edNama.setText(nama);
        edSatuan.setText(satuan);
        edHargaJual.setText(harga_jual);
        edHargaBeli.setText(harga_beli);
        edStock.setText(stock);
        edStockMin.setText(stock_min);
        editBarang = true;
    }
    private void submitBarang(Barang barang) {
    /**
     * Ini adalah kode yang digunakan untuk mengirimkan data ke
     Firebase Realtime Database
     * dan juga kita set onSuccessListener yang berisi kode yang
     akan dijalankan
     * ketika data berhasil ditambahkan
     */
        database.child
                ("barang").push().setValue(barang).addOnSuccessListener(
                this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        edNama.setText("");
                        edSatuan.setText("");
                        edHargaBeli.setText("");
                        edHargaJual.setText("");
                        edStock.setText("");
                        edStockMin.setText("");
                        Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
                    }
                });
    }
    public static Intent getActIntent(Activity activity) {
// kode untuk pengambilan Intent
        return new Intent(activity, FirebaseDBCreate.class);
    }
}