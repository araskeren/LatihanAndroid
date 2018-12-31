package com.example.d2a.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button btCreateDB;
    private Button btViewDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btCreateDB = (Button) findViewById(R.id.bt_createdata);
        btViewDB = (Button) findViewById(R.id.bt_viewdata);
        btCreateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // kelas yang akan dijalankan ketika tombol Create/Insert Data diklik
                startActivity(FirebaseDBCreate.getActIntent(MainActivity.this));
            }
        });
        btViewDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // kode untuk tutorial selanjutnya
                startActivity(FirebaseDBReadActivity.getActIntent(MainActivity.this));
            }
        });
    }
}
