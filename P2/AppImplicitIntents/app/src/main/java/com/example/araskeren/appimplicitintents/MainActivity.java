package com.example.araskeren.appimplicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etLokasi,etLink,etShare;
    Button btnLokasi,btnLink,btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etLokasi=(EditText)findViewById(R.id.textLokasi);
        etLink=(EditText)findViewById(R.id.textLink);
        etShare=(EditText)findViewById(R.id.textCopy);
        btnLokasi=(Button)findViewById(R.id.btnLokasi);
        btnLink=(Button)findViewById(R.id.btnLink);
        btnShare=(Button)findViewById(R.id.btnCopy);

        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String alamat = etLokasi.getText().toString();
                Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194?q="+alamat);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        btnLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String link = etLink.getText().toString();
//                Uri uriUrl = Uri.parse(link);
//                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
//                startActivity(launchBrowser);

                String url = etLink.getText().toString();
                Log.d("TEST", "onClick: "+url);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=etShare.getText().toString();
                String phone_number="085876247118";
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phone_number));
                sendIntent.putExtra("sms_body", message);
                startActivity(sendIntent);
            }
        });
    }
}
