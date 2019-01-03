package damisbachtiar17.web.id.uts.model;

public class DataModelTransaksi {
    String transaksi_id,nama_barang,harga,nama_geray,status,created_at;

    public String getTransaksiId() {
        return transaksi_id;
    }

    public void setTransaksiId(String transaksi_id) {
        this.transaksi_id = transaksi_id;
    }

    public String getNamaBarang() {
        return nama_barang;
    }

    public void setNamaBarang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNamaGeray() {
        return nama_geray;
    }

    public void setNamaGeray(String nama_geray) {
        this.nama_geray = nama_geray;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String created_at) {
        this.created_at = created_at;
    }
}
