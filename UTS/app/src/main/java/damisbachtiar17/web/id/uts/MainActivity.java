package damisbachtiar17.web.id.uts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /**
     * By Fathurrahman (okedroid.com)
     */

    EditText edUsername, edPassword;
    SharedPreferences preferences;
    public static final String KEYPREF = "Key Preferences";
    public static final String KEYNAMA = "Key Nama";
    public static final String KEYUSERNAME = "Key Username";
    public static final String KEYPASSWORD = "Key Password";
    //deklarasi editext ,sharedpreferences ,dan Key


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);


        getSupportActionBar().setTitle("Login UTS");

        edUsername = (EditText) findViewById(R.id.username);
        edPassword = (EditText) findViewById(R.id.password);
        //instansiasi editext


        preferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);

        //instansiasi preferences

        if (preferences.contains(KEYUSERNAME) && (preferences.contains(KEYPASSWORD))) {
            edUsername.setText(preferences.getString(KEYUSERNAME, ""));
            edPassword.setText(preferences.getString(KEYPASSWORD, ""));

        }
        //kondisi jika aplikasi di buka kembali ,maka akan menyimpan data dari sharedpreferences
        //berdasarkan Key
    }


    public String getUsername(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        text = settings.getString(KEYUSERNAME, null);
        return text;
    }

    public String getNama(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        text = settings.getString(KEYNAMA, null);
        return text;
    }

    public String getPassword(Context context) {
        SharedPreferences settings;
        String text;

        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings = context.getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        text = settings.getString(KEYPASSWORD, null);
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
        String idUser, passWord, username, password;
        username = edUsername.getText().toString();
        password = edPassword.getText().toString();

        idUser = getUsername(this);
        passWord = getPassword(this);
        if (username.equals(idUser) && password.equals(passWord)) {
            String nama = getNama(this);
            Toast.makeText(this, "Selamat Datang : " + nama + ", Kamu Berhasil LOGIN !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            startActivity(intent);
        } else if (username.equals(idUser) && !passWord.equals(passWord)) {
            Toast.makeText(this, "Password Salah !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Tidak Ada Data ! Silahkan Register", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
