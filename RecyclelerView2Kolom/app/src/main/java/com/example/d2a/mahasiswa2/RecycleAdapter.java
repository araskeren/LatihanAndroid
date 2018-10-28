package com.example.d2a.mahasiswa2;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private ArrayList<String> listGambar;
    private Activity activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private CardView cv;
        private TextView mTextView;
        private ImageView mImage;

        public ViewHolder(View v) {
            super(v);
            cv=(CardView)v.findViewById(R.id.card_view);
            mTextView=(TextView)v.findViewById(R.id.txt_card);
            mImage=(ImageView)v.findViewById(R.id.img_card);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecycleAdapter(Activity activity,ArrayList<String> listGambar) {
        this.listGambar = listGambar;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return listGambar.size();
    }



    // Create new views (invoked by the layout manager)
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(listGambar.get(position));
        // menampilkan gambar dari folder assets
        AssetManager assetManager = activity.getAssets();
        InputStream is;
        try {
            is = assetManager.open(listGambar.get(position)+".png");
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            System.out.print(listGambar.get(position)+".png");
            holder.mImage.setImageBitmap(bitmap);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

