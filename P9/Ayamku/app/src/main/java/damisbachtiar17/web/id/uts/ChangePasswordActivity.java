package damisbachtiar17.web.id.uts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText pass1,pass2;
    public static final String KEYPREF = "USER";
    public static final String KEYNAMA = "NAMA";
    public static final String KEYUSERNAME = "USERNAME";
    public static final String KEYPASSWORD = "PASSWORD";
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        pass1 = findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);
        preferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
    }

    private void changePassword(String password){
        Log.d("TEST", "BEFORE changePassword: "+preferences.getString(KEYNAMA, ""));
        //clearSharedPreference(this);
        //preferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Log.d("TEST", "AFTER changePassword: "+preferences.getString(KEYNAMA, ""));
//        editor.putString(KEYNAMA,preferences.getString(KEYNAMA, ""));
//        editor.putString(KEYUSERNAME,preferences.getString(KEYUSERNAME, ""));
        editor.putString(KEYPASSWORD, password);
        editor.apply();
        displayToast("PASSWORD BERHASIL DI RUBAH!");
        Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private boolean checkIsPasswordSame(String pass1,String pass2){
        if(!pass1.equals(pass2)){
            displayToast("PASSWORD TIDAK SAMA !");
            return false;
        }
        return  true;
    }

    private void displayToast(String pesan){
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
    }

    public void save(View view){
        String password1=pass1.getText().toString();
        String password2=pass2.getText().toString();
        if(checkIsPasswordSame(password1,password2)){
            changePassword(password1);
        }
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
}
