package arumfaisal.id.paysargayengumum.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModelTransaksi {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer user_id;
    @SerializedName("barang_id")
    @Expose
    private Integer barang_id;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("update_at")
    @Expose
    private String update_at;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("kode_pembayaran")
    @Expose
    private String kode_pembayaran;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBarang_id() {
        return barang_id;
    }

    public void setBarang_id(Integer barang_id) {
        this.barang_id = barang_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKode_pembayaran() {
        return kode_pembayaran;
    }

    public void setKode_pembayaran(String kode_pembayaran) {
        this.kode_pembayaran = kode_pembayaran;
    }
}