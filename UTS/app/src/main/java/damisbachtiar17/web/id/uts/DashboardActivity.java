package damisbachtiar17.web.id.uts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends  AppCompatActivity {
    private List<BarangViewItem> barangViewItemList = null;
    public TextView totalHarga;
    SharedPreferences preferences;
    public static final String KEYPREF = "HARGA";
    public static final String KEYVALUE = "KEYVALUE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Dashboard");
        initializebarangViewItemList();

        totalHarga = (TextView) findViewById(R.id.total_harga);
        preferences = getSharedPreferences(KEYPREF, Context.MODE_PRIVATE);
        totalHarga.setText(preferences.getString(KEYVALUE, "999"));

        // Create the recyclerview.
        RecyclerView recyclerBarang = (RecyclerView)findViewById(R.id.list_barang);
        // Create the grid layout manager with 2 columns.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // Set layout manager.
        recyclerBarang.setLayoutManager(gridLayoutManager);


        // Create car recycler view data adapter with car item list.
        BarangViewData barangDataAdapter = new BarangViewData(barangViewItemList);
        // Set data adapter.
        recyclerBarang.setAdapter(barangDataAdapter);


    }


    public void pembayaran(View v){
        Intent intent = new Intent(this, FormPembayaranActivity.class);
        intent.putExtra("total",totalHarga.getText().toString());
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

    private void displayToast(String pesan){
        Toast.makeText(this, ""+pesan, Toast.LENGTH_SHORT).show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_call_center){
            displayToast("Menghubungi CALL CENTER");
            Uri number = Uri.parse("tel:085876247118");
            Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
            startActivity(callIntent);
        } else if (item.getItemId() == R.id.action_sms_center) {
            String message="Hallo Admin,Saya ";
            String phone_number="085876247118";
            Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phone_number));
            sendIntent.putExtra("sms_body", message);
            startActivity(sendIntent);
        } else if (item.getItemId() == R.id.action_lokasi) {
            String alamat = "UDINUS";
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
    private void initializebarangViewItemList()
    {
        if(barangViewItemList == null)
        {
            barangViewItemList = new ArrayList<BarangViewItem>();
            barangViewItemList.add(new BarangViewItem("Audi", R.drawable.ayam1, 1000,"Deskripsi 1"));
            barangViewItemList.add(new BarangViewItem("BMW", R.mipmap.ic_launcher_round, 100,"Deskripsi 2"));
            barangViewItemList.add(new BarangViewItem("Benz", R.mipmap.ic_launcher_round, 200,"Deskripsi 3"));
            barangViewItemList.add(new BarangViewItem("Jeep", R.mipmap.ic_launcher_round, 300,"Deskripsi 4"));
            barangViewItemList.add(new BarangViewItem("Land Rover", R.mipmap.ic_launcher_round, 400,"Deskripsi 5"));
            barangViewItemList.add(new BarangViewItem("Future", R.mipmap.ic_launcher_round, 500,"Deskripsi 6"));
        }
    }
    public void sample(){
        displayToast("Berhasil");
    }
}