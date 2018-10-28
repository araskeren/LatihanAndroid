package com.example.araskeren.menggunakanrelativelayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button opencv=(Button) findViewById(R.id.opencv);
        opencv.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                openCV();
            }
        });
    }

    public void openCV(){
        Context context = getApplicationContext();
        CharSequence text = "CV Open!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        String url = context.getString(R.string.link);
        Log.d("TEST", "openCV: "+url);
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
