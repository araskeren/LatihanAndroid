package com.example.d2a.mahasiswa2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecycleAdapter rAdapter;

    private ArrayList<String> listGambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rView = (RecyclerView) findViewById(R.id.my_recycler_view);
        rView.setHasFixedSize(true);

        //LINEAR
        //  LinearLayoutManager llm = new LinearLayoutManager(this);
        // llm.setOrientation(LinearLayoutManager.VERTICAL);

        //GRID 2 kolom
        GridLayoutManager llm=new GridLayoutManager(this,2);

        //STAGGER 4 KOLOM
        // StaggeredGridLayoutManager llm=new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        rView.setLayoutManager(llm);

        ambidata();
        rAdapter=new RecycleAdapter(this,listGambar);
        rView.setAdapter(rAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void ambidata(){
        listGambar= new ArrayList<String>();

        listGambar.add("bird_icon");
        listGambar.add("black_cat_icon");
        listGambar.add("bulldog_icon");
        listGambar.add("bunny_icon");
       /* listGambar.add("cardinal-icon");
        listGambar.add("cat-icon");
        listGambar.add("chicken-icon");
        listGambar.add("chihuahua-icon");
        listGambar.add("cow-icon");
        listGambar.add("crab-icon");
        listGambar.add("dachshund-icon");
        listGambar.add("dog-icon");
        listGambar.add("dolphin-icon");
        listGambar.add("donkey-icon");
        listGambar.add("duck-icon");
        listGambar.add("fish-icon");
        listGambar.add("frog-icon");
        listGambar.add("gold-fish-icon");
        listGambar.add("hamster-icon");
        listGambar.add("horse-icon");
        listGambar.add("jellyfish-icon");
        listGambar.add("kitten-icon");
        listGambar.add("lobster-icon");
        listGambar.add("mouse-icon");
        listGambar.add("octopus-icon");
        listGambar.add("parrot-icon");
        listGambar.add("pig-icon");
        listGambar.add("puppy-icon");
        listGambar.add("rabbit-icon");
        listGambar.add("seal-icon");
        listGambar.add("shark-icon");
        listGambar.add("sheep-icon");
        listGambar.add("squid-icon");
        listGambar.add("squirrel-icon");
        listGambar.add("tropical-fish-icon");
        listGambar.add("tuna-icon");
        listGambar.add("turkey-icon");
        listGambar.add("turtle-icon");
        listGambar.add("whale-icon");*/
    }
}