package damisbachtiar17.web.id.uts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

import damisbachtiar17.web.id.uts.adapter.AdapterDataAyam;
import damisbachtiar17.web.id.uts.api.ApiRequestAyam;
import damisbachtiar17.web.id.uts.api.ApiRequestTransaksi;
import damisbachtiar17.web.id.uts.api.Retroserver;
import damisbachtiar17.web.id.uts.model.DataModelAyam;
import damisbachtiar17.web.id.uts.model.DataModelTransaksi;
import damisbachtiar17.web.id.uts.model.ResponsModelAyam;
import damisbachtiar17.web.id.uts.model.ResponsModelTransaksi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends  AppCompatActivity {
    //private List<BarangViewItem> barangViewItemList = null;
    private List<DataModelAyam> itemAyam = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecycler;

    public TextView totalHarga;
    SharedPreferences preferences;
    public static final String KEYPREF = "HARGA";
    public static final String KEYVALUE = "KEYVALUE";
    public static final String USERPREF = "USER";
    public static final String KEYNAMA = "NAMA";
    private String geray_id,geray_alamat,geray_telp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(USERPREF, Context.MODE_PRIVATE);
        getSupportActionBar().setTitle(preferences.getString(KEYNAMA, ""));
        Intent intent = getIntent();
        totalHarga = (TextView) findViewById(R.id.total_harga);
        if(intent.hasExtra("harga")){
            String harga = intent.getStringExtra("harga");
            Log.d("TEST", "onCreate: "+harga);
            totalHarga.setText(harga);
        }
        geray_id=intent.getStringExtra("geray_id");
        geray_telp=intent.getStringExtra("geray_no_telp");
        geray_alamat=intent.getStringExtra("geray_alamat");
        //initializebarangViewItemList();
        mRecycler = (RecyclerView) findViewById(R.id.list_barang);
        ApiRequestAyam api = Retroserver.getClient().create(ApiRequestAyam.class);
        Call<ResponsModelAyam> getdata = api.getBiodata();
        getdata.enqueue(new Callback<ResponsModelAyam>() {
            @Override
            public void onResponse(Call<ResponsModelAyam> call, Response<ResponsModelAyam> response) {
                itemAyam = response.body().getResult();
                Log.d("TAG", "onResponse: "+itemAyam);
                mAdapter = new AdapterDataAyam(DashboardActivity.this,itemAyam,geray_id);
                mRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponsModelAyam> call, Throwable t) {
                Log.d("RETRO", "FAILED : respon gagal");

            }
        });


//        preferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
//        totalHarga.setText(preferences.getString(KEYVALUE, "999"));

        // Create the recyclerview.
        RecyclerView recyclerBarang = (RecyclerView)findViewById(R.id.list_barang);
        // Create the grid layout manager with 2 columns.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // Set layout manager.
        recyclerBarang.setLayoutManager(gridLayoutManager);

        getTransaksi();
    }


    public void pembayaran(View v){
        Intent intent = new Intent(this, FormPembayaranActivity.class);
        intent.putExtra("total",totalHarga.getText().toString());
        intent.putExtra("geray_id",geray_id);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    public void displayToast(String pesan){
        Toast.makeText(this, ""+pesan, Toast.LENGTH_SHORT).show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_call_center){
            displayToast("Menghubungi CALL CENTER");
            Uri number = Uri.parse("tel:"+geray_telp);
            Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
            startActivity(callIntent);
        } else if (item.getItemId() == R.id.action_sms_center) {
            displayToast("Menghubungi GERAY via SMS");
            String message="Hallo Admin,Saya "+preferences.getString(KEYNAMA, "");
            String phone_number=geray_telp;
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phone_number));
            sendIntent.putExtra("sms_body", message);
            startActivity(sendIntent);
        } else if (item.getItemId() == R.id.action_lokasi) {
            displayToast("Membuka Google Maps !");
            String alamat = geray_alamat;
            Uri gmmIntentUri = Uri.parse("geo:37.7749,-122.4194?q="+alamat);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }else if (item.getItemId() == R.id.action_change_password) {
            startActivity(new Intent(this, ChangePasswordActivity.class));
        }

        return true;
    }
    /* Initialise car items in list. */
//    private void initializebarangViewItemList()
//    {
//        if(barangViewItemList == null)
//        {
//            barangViewItemList = new ArrayList<BarangViewItem>();
//            barangViewItemList.add(new BarangViewItem("Ayam Krispi", R.drawable.ayam_krispi, 400,"Deskripsi Ayam Krispi"));
//            barangViewItemList.add(new BarangViewItem("Ayam Geprek", R.drawable.ayam_geprek, 200,"Deskripsi Ayam Geprek"));
//            barangViewItemList.add(new BarangViewItem("Ayam Kecap", R.drawable.ayam_kecap, 300,"Deskripsi Ayam Kecap"));
//            barangViewItemList.add(new BarangViewItem("Ayam Rendang", R.drawable.ayam_rendang, 500,"Deskripsi Ayam Rendang"));
//            barangViewItemList.add(new BarangViewItem("Ayam Bakar", R.drawable.ayam_bakar, 1000,"Deskripsi Ayam Bakar"));
//            barangViewItemList.add(new BarangViewItem("Ayam Balado", R.drawable.ayam_balado, 100,"Deskripsi Ayam Balado"));
//        }
//    }
    public void sample(){
        displayToast("Berhasil");
    }

    private void getTransaksi(){
        ApiRequestTransaksi api = Retroserver.getClient().create(ApiRequestTransaksi.class);
        Call<ResponsModelTransaksi> getData=api.getTransaksi("check",geray_id);
        Log.e("getTransaksi", "getTransaksi: "+geray_id);
        getData.enqueue(new Callback<ResponsModelTransaksi>() {
            @Override
            public void onResponse(Call<ResponsModelTransaksi> call, Response<ResponsModelTransaksi> response) {
                List<DataModelTransaksi> resp = response.body().getResult();
                Integer total_bayar=0;

                int i=0;
                for (DataModelTransaksi result : resp){
                    total_bayar+= Integer.parseInt(resp.get(i).getHarga());
                    i++;
                }

                Log.e("onResponse", "onResponse: "+resp.size());

                totalHarga.setText(total_bayar.toString());
            }

            @Override
            public void onFailure(Call<ResponsModelTransaksi> call, Throwable t) {
                Log.d("RETRO", "FAILED : respon gagal");
                Toast.makeText(getBaseContext(), "CEK KONEKSI ANDA !", Toast.LENGTH_LONG).show();
            }
        });
    }
}