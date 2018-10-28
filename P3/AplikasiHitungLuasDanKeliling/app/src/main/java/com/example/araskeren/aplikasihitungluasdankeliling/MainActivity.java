package com.example.araskeren.aplikasihitungluasdankeliling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewPersegi(View view) {
        Intent j = new Intent(this, PersegiActivity.class);
        startActivity(j);
    }
    public void viewSegitiga(View view) {
        Intent j = new Intent(this, SegitigaActivity.class);
        startActivity(j);
    }
    public void viewLingkaran(View view) {
        Intent j = new Intent(this, LingkaranActivity.class);
        startActivity(j);
    }
    public void viewKubus(View view) {
        Intent j = new Intent(this, KubusActivity.class);
        startActivity(j);
    }
}
