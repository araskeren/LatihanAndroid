package damisbachtiar17.web.id.uts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderBarang extends RecyclerView.ViewHolder {

    private TextView judulBarang = null;

    private ImageView gambarBarang = null;

    private TextView hargaBarang = null;

    private TextView deskripsiBarang = null;

    private TextView totalBarang=null;


    public ViewHolderBarang(View itemView) {
        super(itemView);

        if(itemView != null)
        {
            judulBarang = (TextView)itemView.findViewById(R.id.nama_gambar);

            gambarBarang = (ImageView)itemView.findViewById(R.id.gambar);

            hargaBarang = (TextView)itemView.findViewById(R.id.harga_gambar);

            deskripsiBarang = (TextView)itemView.findViewById(R.id.deskripsi);

        }
    }

    public TextView getJudulBarang() {
        return judulBarang;
    }

    public ImageView getGambarBarang() {
        return gambarBarang;
    }

    public TextView getHarga() {
        return hargaBarang;
    }

    public TextView getDeskripsiBarang() {
        return deskripsiBarang;
    }
}
