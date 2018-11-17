package com.example.d2a.datasiswa;

import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class APP_SQLITEActivity extends ListActivity {
    /** Called when the activity is first created. */
    String dataSiswa[] = null;
    String dS[] = null;
    @Override
    public void
    onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);
        // Tambah Siswa
        Log.d("Tambah Siswa: ", "Menambah Data Siswa..");
        db.addSiswa(new Siswa("001","Ghiyatsi  Miftahur Rahmat"));
        db.addSiswa(new Siswa("002","Annisa Fitriana"));
        db.addSiswa(new Siswa("003","Andina NurAmalia"));
        // Membaca Semua Siswa
        Log.d("Baca Siswa: ",   "Membaca Semua Data Siswa..");
        List<Siswa> siswa = db.getSemuaSiswa();
        dataSiswa= new String[siswa.size()];
        dS= new String[siswa.size()];
        int i=0;
        for(Siswa s : siswa) {
            String  log ="NIS:  "+  s.getNis()  +  ",Nama:  "+ s.getNama();
            Log.d("Name: ", log);
            dataSiswa[i] = s.getNis() + " - "+ s.getNama();
            dS[i] = s.getNis();
            i++;
        }
        setListAdapter(new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, dataSiswa));
        registerForContextMenu(getListView());
    }
    @Override
    public  void  onCreateContextMenu(ContextMenu  menu,
                                      View v,
                                      ContextMenuInfo menuInfo) {
        // TODOAutogenerated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Action");
        menu.add(0,0,0,"Tambah");
        menu.add(0,1,1,"Hapus");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODOAuto-generated method stub
        try{
            switch(item.getItemId()){
                case 0:{
                    Class c =Class.forName("com.example.d2a.datasiswa.TambahActivity");
                    Intent  i  =  new  Intent(APP_SQLITEActivity.this, c);
                    startActivity(i); break;
                }
                case 1:{
                    DatabaseHandler db = new DatabaseHandler(this);
                    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    String[] args = {
                        String.valueOf(info.id)
                    };
                    db.deleteRow(dS[0]);
                    Class c =Class.forName("com.example.d2a.datasiswa.APP_SQLITEActivity");
                    Intent  i = new Intent(APP_SQLITEActivity.this, c);
                    startActivity(i);
                    break;
                }
            }
        } catch(
                ClassNotFoundException e) {
                // TODOAuto-generated catch block
                e.printStackTrace();
        }
        return true;
    }
}