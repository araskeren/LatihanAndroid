package damisbachtiar17.web.id.crud_barang.model;

public class DataModel {
    String id,nama, harga_jual, harga_beli , stock;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHargaJual() {
        return harga_jual;
    }

    public void setHargaJual(String harga_jual) {
        this.harga_jual = harga_jual;
    }

    public String getHargaBeli() {
        return harga_beli;
    }

    public void setHargaBeli(String harga_beli) {
        this.harga_beli = harga_beli;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}