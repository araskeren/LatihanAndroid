package damisbachtiar17.web.id.pasargayeng;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import damisbachtiar17.web.id.pasargayeng.api.ApiRequestLogin;
import damisbachtiar17.web.id.pasargayeng.api.ApiRequestToko;
import damisbachtiar17.web.id.pasargayeng.api.Retroserver;
import damisbachtiar17.web.id.pasargayeng.model.DataModelLogin;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelData;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnRegister;
    TextView txtUsername,txtPassword;
    String username,password;
    Intent intent;
    EditText userName, passWord;
    SharedPreferences preferences;
    public static final String KEYPREF = "USER";
    public static final String KEYUSERNAME = "USERNAME";
    public static final String KEYPASSWORD = "PASSWORD";
    private List<DataModelLogin> result = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnClear);
        txtUsername = findViewById(R.id.username);
        txtPassword = findViewById(R.id.password);
        intent = new Intent(this,DaftarTokoActivity.class);

        preferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        userName = (EditText) findViewById(R.id.username);
        passWord = (EditText) findViewById(R.id.password);
        getSavePreferences();
    }

    public void login(View view){
        username = txtUsername.getText().toString();
        password = txtPassword.getText().toString();
        ApiRequestLogin api= Retroserver.getClient().create(ApiRequestLogin.class);
        Call<ResponsModelLogin> getData= api.userLogin(username,password);
        getData.enqueue(new Callback<ResponsModelLogin>() {
            @Override
            public void onResponse(Call<ResponsModelLogin> call, Response<ResponsModelLogin> response) {
                ResponsModelLogin resp = response.body();
                if(resp!=null){
//                    Log.e("cekResponse : ",""+ new Gson().toJson(resp));
//                    Log.e("cekToken",""+resp.getSuccess().getDetailUser().getNama());
                    savePreferences();
                    Toast.makeText(getBaseContext(), "Login Berhasil, "+resp.getSuccess().getDetailUser().getNama(), Toast.LENGTH_LONG).show();
                    addDataToIntent(resp);
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(), "Login gagal!Periksa Email dan password", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponsModelLogin> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Gagal terkoneksi ke server,silahkan cek kembali jaringan dan coba kembali", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void savePreferences(){
        String user = userName.getText().toString();
        String pass = passWord.getText().toString();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEYUSERNAME, user);
        editor.putString(KEYPASSWORD, pass);
        editor.apply();
    }
    private void getSavePreferences(){
        if (preferences.contains(KEYUSERNAME) && (preferences.contains(KEYPASSWORD))) {
            userName.setText(preferences.getString(KEYUSERNAME, ""));
            passWord.setText(preferences.getString(KEYPASSWORD, ""));
        }
    }
    private  void addDataToIntent(ResponsModelLogin resp){
        //intent.putExtra("harga",0);
        intent.putExtra("token","Bearer "+resp.getSuccess().getToken());
        intent.putExtra("nama_user",resp.getSuccess().getDetailUser().getNama());
        intent.putExtra("level",resp.getSuccess().getDetailUser().getLevel());
    }

    public void register(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
