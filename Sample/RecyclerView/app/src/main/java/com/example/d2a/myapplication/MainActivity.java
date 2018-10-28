package com.example.d2a.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new MahasiswaAdapter(mahasiswaArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa("Ghiyatsi Miftahur Rahmat", "A11.0001", "0857777779"));
        mahasiswaArrayList.add(new Mahasiswa("Najwa Aulia Dhofiroh", "A11.0002", "0857777778"));
        mahasiswaArrayList.add(new Mahasiswa("Annisa Fitriana", "A11.0003", "0857777777"));
        mahasiswaArrayList.add(new Mahasiswa("Avanca Dewi", "A11.0004", "0857777776"));
    }

}