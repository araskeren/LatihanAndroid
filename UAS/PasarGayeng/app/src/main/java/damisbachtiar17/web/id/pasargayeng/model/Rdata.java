package damisbachtiar17.web.id.pasargayeng.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rdata {
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("pemilik_id")
    @Expose
    private String pemilik_id;
    @SerializedName("create_id")
    @Expose
    private Integer creatId;
    @SerializedName("update_at")
    @Expose
    private String updateAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPemilik_id() {
        return pemilik_id;
    }

    public void setPemilik_id(String pemilik_id) {
        this.pemilik_id = pemilik_id;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
