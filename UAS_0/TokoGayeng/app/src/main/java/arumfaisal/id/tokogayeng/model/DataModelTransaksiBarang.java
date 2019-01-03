package arumfaisal.id.tokogayeng.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModelTransaksiBarang {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("toko_id")
    @Expose
    private Integer tokoId ;

    @SerializedName("barang_id")
    @Expose
    private Integer barangId ;

    @SerializedName("nama_barang")
    @Expose
    private String namaBarang;

    @SerializedName("harga")
    @Expose
    private Integer harga;

    @SerializedName("gambar")
    @Expose
    private String gambar;

    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;

    @SerializedName("user_id")
    @Expose
    private Integer userId ;

    @SerializedName("nama_pembeli")
    @Expose
    private String namaPembeli;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("no_telp")
    @Expose
    private String noTelp;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("kode_pembayaran")
    @Expose
    private String kodePembayaran;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTokoId() {
        return tokoId;
    }

    public void setTokoId(Integer tokoId) {
        this.tokoId = tokoId;
    }

    public Integer getBarangId() {
        return barangId;
    }

    public void setBarangId(Integer barangId) {
        this.barangId = barangId;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public void setNamaPembeli(String namaPembeli) {
        this.namaPembeli = namaPembeli;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKodePembayaran() {
        return kodePembayaran;
    }

    public void setKodePembayaran(String kodePembayaran) {
        this.kodePembayaran = kodePembayaran;
    }
}
