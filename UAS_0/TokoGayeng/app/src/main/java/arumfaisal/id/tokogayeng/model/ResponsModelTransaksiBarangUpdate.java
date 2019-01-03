package arumfaisal.id.tokogayeng.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsModelTransaksiBarangUpdate {
    @SerializedName("data")
    @Expose
    private String kode;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }
}
