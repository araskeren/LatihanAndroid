package id.web.damisbachtiar.ayamku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<BarangViewItem> barangViewItemList = null;

    public TextView totalHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializebarangViewItemList();

        // Create the recyclerview.
        RecyclerView carRecyclerView = (RecyclerView)findViewById(R.id.list_barang);
        // Create the grid layout manager with 2 columns.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // Set layout manager.
        carRecyclerView.setLayoutManager(gridLayoutManager);

        totalHarga = (TextView) findViewById(R.id.total_harga);
        // Create car recycler view data adapter with car item list.
        BarangViewData barangDataAdapter = new BarangViewData(barangViewItemList);
        // Set data adapter.
        carRecyclerView.setAdapter(barangDataAdapter);

        //this.updateData();

    }

    /* Initialise car items in list. */
    private void initializebarangViewItemList()
    {
        if(barangViewItemList == null)
        {
            barangViewItemList = new ArrayList<BarangViewItem>();
            barangViewItemList.add(new BarangViewItem("Ayam Bakar", R.drawable.ayam_bakar, 12500));
            barangViewItemList.add(new BarangViewItem("Ayam Balado", R.drawable.ayam_balado, 13000));
            barangViewItemList.add(new BarangViewItem("Ayam Geprek", R.drawable.ayam_geprek, 7000));
            barangViewItemList.add(new BarangViewItem("Ayam Kecap", R.drawable.ayam_kecap, 8500));
            barangViewItemList.add(new BarangViewItem("Ayam Krispi", R.drawable.ayam_krispi, 5000));
            barangViewItemList.add(new BarangViewItem("Ayam Rendang", R.drawable.ayam_rendang, 18000));
        }
    }

    public void updateData(String data){
//        Log.d(, "updateData: "+data);
//        totalHarga.setText(data);
    }
}