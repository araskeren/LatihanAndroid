package damisbachtiar17.web.id.belajarsharedprefs;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText edNama,edAlamat;
    Button btnSave,btnCancel;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        bindListener();
    }

    private void bind(){
        edNama = (EditText) findViewById(R.id.nama);
        edAlamat=(EditText) findViewById(R.id.alamat);
        btnSave=(Button) findViewById(R.id.btnSave);
        btnCancel=(Button) findViewById(R.id.btnCancel);
    }
    private  void bindListener(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveClick();
            }
        });
    }
    private  void sharedPreferences(){
        sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("NAMA",edNama.getText().toString());
        editor.putString("ALAMAT",edAlamat.getText().toString());
        editor.commit();
    }
    public void saveClick(){
        sharedPreferences();
//        SharedPreferences pref = getSharedPreferences(sharedPreferences,Context.MODE_PRIVATE);
//        String nama = pref.getString("NAMA", "");
//        String alamat = pref.getString("ALAMAT", "");
        Toast.makeText(MainActivity.this,"Data tersimpan "+sharedPreferences,Toast.LENGTH_LONG).show();

    }
}
