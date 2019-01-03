package arumfaisal.id.paysargayengumum;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import arumfaisal.id.paysargayengumum.api.ApiRequestLogin;
import arumfaisal.id.paysargayengumum.api.Retroserver;
import arumfaisal.id.paysargayengumum.model.ResponsModelLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    /**
     * By Fathurrahman (okedroid.com)
     */

    EditText edUsername, edNama,edPassword,edEmail,edNoTelp,edAlamat;
    SharedPreferences preferences;
    public static final String KEYPREF = "USER";
    public static final String KEYNAMA = "NAMA";
    public static final String KEYUSERNAME = "USERNAME";
    public static final String KEYPASSWORD = "PASSWORD";
    //deklarasi editext ,sharedpreferences ,dan Key


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        getSupportActionBar().setTitle("Register User");
        edNama     = (EditText)  findViewById(R.id.nama);
        edUsername = (EditText) findViewById(R.id.username);
        edPassword = (EditText) findViewById(R.id.password);
        edAlamat   = (EditText) findViewById(R.id.alamat);
        edEmail    = (EditText) findViewById(R.id.email);
        edNoTelp   = (EditText) findViewById(R.id.no_telp);
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
        String username = edUsername.getText().toString();
        String password = edPassword.getText().toString();
        String nama = edNama.getText().toString();
        String email= edEmail.getText().toString();
        String alamat= edAlamat.getText().toString();
        String no_telp= edNoTelp.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEYNAMA, nama);
        editor.putString(KEYUSERNAME, email);
        editor.putString(KEYPASSWORD, password);
        editor.apply();

        ApiRequestLogin api= Retroserver.getClient().create(ApiRequestLogin.class);
        Call<ResponsModelLogin> getData= api.userRegister(username,nama,email,password,password,no_telp,alamat);
        getData.enqueue(new Callback<ResponsModelLogin>() {
            @Override
            public void onResponse(Call<ResponsModelLogin> call, Response<ResponsModelLogin> response) {
                ResponsModelLogin resp = response.body();
                if(resp!=null){
                    Toast.makeText(getApplicationContext(), "UserName dan Password disimpan,REGISTRASI BERHASIL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Pendaftaran gagal!Periksa kembali semua inputan !", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponsModelLogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal terkoneksi ke server,silahkan cek kembali jaringan dan coba kembali", Toast.LENGTH_LONG).show();
            }
        });
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