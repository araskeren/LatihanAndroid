package damisbachtiar17.web.id.uts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import damisbachtiar17.web.id.uts.adapter.AdapterDataPilihGeray;
import damisbachtiar17.web.id.uts.api.ApiRequestGeray;
import damisbachtiar17.web.id.uts.api.Retroserver;
import damisbachtiar17.web.id.uts.model.DataModelGeray;
import damisbachtiar17.web.id.uts.model.ResponsModelGeray;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihGerayActivity extends AppCompatActivity {
    //private List<BarangViewItem> barangViewItemList = null;
    private List<DataModelGeray> itemGeray = new ArrayList<>();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecycler;

    public TextView totalHarga;
    SharedPreferences preferences;
    public static final String KEYPREF = "HARGA";
    public static final String KEYVALUE = "KEYVALUE";
    public static final String USERPREF = "USER";
    public static final String KEYNAMA = "NAMA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_geray);
        preferences = getSharedPreferences(USERPREF, Context.MODE_PRIVATE);
        getSupportActionBar().setTitle(preferences.getString(KEYNAMA, ""));

        //initializebarangViewItemList();
        mRecycler = (RecyclerView) findViewById(R.id.list_geray);
        ApiRequestGeray api = Retroserver.getClient().create(ApiRequestGeray.class);
        Call<ResponsModelGeray> getdata = api.getGeray();
        getdata.enqueue(new Callback<ResponsModelGeray>() {
            @Override
            public void onResponse(Call<ResponsModelGeray> call, Response<ResponsModelGeray> response) {
                itemGeray = response.body().getResult();
                Log.d("TAG", "onResponse: "+itemGeray);
                mAdapter = new AdapterDataPilihGeray(PilihGerayActivity.this,itemGeray);
                mRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponsModelGeray> call, Throwable t) {
                Log.d("RETRO", "FAILED : respon gagal");

            }
        });
        // Create the recyclerview.
        RecyclerView recyclerGeray = (RecyclerView)findViewById(R.id.list_geray);
        // Create the grid layout manager with 2 columns.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // Set layout manager.
        recyclerGeray.setLayoutManager(gridLayoutManager);
    }

    public void displayToast(String pesan){
        Toast.makeText(this, ""+pesan, Toast.LENGTH_SHORT).show();
    }
}