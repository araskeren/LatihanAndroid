package id.web.damisbachtiar.ayamku;

import android.widget.TextView;

public class BarangViewItem {

    // Save car name.
    private String carName;

    // Save car image resource id.
    private int carImageId;

    private int hargaId;


    public BarangViewItem(String carName, int carImageId, int hargaId) {
        this.carName = carName;
        this.carImageId = carImageId;
        this.hargaId = hargaId;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getCarImageId() {
        return carImageId;
    }

    public void setCarImageId(int carImageId) {
        this.carImageId = carImageId;
    }

    public int getHargaId() {
        return hargaId;
    }

    public void setHargaId(int hargaId) {
        this.hargaId = hargaId;
    }

}