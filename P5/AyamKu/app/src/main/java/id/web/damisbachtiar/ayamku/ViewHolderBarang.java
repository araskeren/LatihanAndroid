package id.web.damisbachtiar.ayamku;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderBarang extends RecyclerView.ViewHolder {

    private TextView carTitleText = null;

    private ImageView carImageView = null;

    private TextView carHargaView = null;

    public ViewHolderBarang(View itemView) {
        super(itemView);

        if(itemView != null)
        {
            carTitleText = (TextView)itemView.findViewById(R.id.nama_gambar);

            carImageView = (ImageView)itemView.findViewById(R.id.gambar);

            carHargaView = (TextView)itemView.findViewById(R.id.harga_gambar);
        }
    }

    public TextView getCarTitleText() {
        return carTitleText;
    }

    public ImageView getCarImageView() {
        return carImageView;
    }

    public TextView getHarga() {
        return carHargaView;
    }
}
