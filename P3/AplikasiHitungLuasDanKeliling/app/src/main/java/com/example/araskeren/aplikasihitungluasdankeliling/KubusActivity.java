package com.example.araskeren.aplikasihitungluasdankeliling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class KubusActivity extends AppCompatActivity implements View.OnClickListener {

    EditText t1;
    double sisi,luas,kel;
    ImageButton b1,b2;
    TextView txtLuas,txtKel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kubus);
        t1=(EditText)findViewById(R.id.edSisi);
        txtLuas=(TextView) findViewById(R.id.txtHasil);
        txtKel=(TextView) findViewById(R.id.txtKeliling);
        b1 = (ImageButton)findViewById(R.id.btnOK);
        b2 = (ImageButton)findViewById(R.id.btnExit);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOK:
                sisi = Double.parseDouble(t1.getText().toString());
                luas = Math.pow(sisi,2);
                kel=6*Math.pow(sisi,2);
                txtLuas.setText(Double.toString(luas));
                txtKel.setText(Double.toString(kel));
                break;

        }
    }
}

