package arumfaisal.id.paysargayengumum.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModelTransaksiUpdate {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("kode_pembayaran")
    @Expose
    private String kode_pembayaran;

    public String getKode_pembayaran() {
        return kode_pembayaran;
    }

    public void setKode_pembayaran(String kode_pembayaran) {
        this.kode_pembayaran = kode_pembayaran;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
