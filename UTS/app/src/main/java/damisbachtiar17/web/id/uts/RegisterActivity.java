package damisbachtiar17.web.id.uts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    /**
     * By Fathurrahman (okedroid.com)
     */

    EditText userName, passWord,edNama;
    SharedPreferences preferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNAMA = "Key Nama";
    public static final String KEYUSERNAME = "Key Username";
    public static final String KEYPASSWORD = "Key Password";
    //deklarasi editext ,sharedpreferences ,dan Key


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);


        getSupportActionBar().setTitle("Register User");
        edNama     = (EditText)  findViewById(R.id.nama);
        userName = (EditText) findViewById(R.id.username);
        passWord = (EditText) findViewById(R.id.password);
        //instansiasi editext

        preferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
//
//        //instansiasi preferences
//
//        if (preferences.contains(KEYUSERNAME) && (preferences.contains(KEYPASSWORD))) {
//            userName.setText(preferences.getString(KEYUSERNAME, ""));
//            passWord.setText(preferences.getString(KEYPASSWORD, ""));
//
//        }
        //kondisi jika aplikasi di buka kembali ,maka akan menyimpan data dari sharedpreferences
        //berdasarkan Key
    }

    public void registerAkun(View view) {
        String user = userName.getText().toString();
        String pass = passWord.getText().toString();
        String nama = edNama.getText().toString();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEYNAMA, nama);
        editor.putString(KEYUSERNAME, user);
        editor.putString(KEYPASSWORD, pass);
        editor.apply();
        Toast.makeText(this, "UserName dan Password disimpan", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        //menyimpan data user dan pass
        //dengan method apply


    }

    public String getValue(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        text = settings.getString(KEYNAMA, null);
        return text;
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }

    public void cekUser(View view) {
        String namaUser;
        namaUser = getValue(this);
        if (TextUtils.isEmpty(namaUser)) {
            Toast.makeText(this, "UserName : Masih Kosong!!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "UserName : " + namaUser + " Sudah ada!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public void login(View view){
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }
}