package com.example.d2a.myapplication;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;
@IgnoreExtraProperties
public class Barang implements Serializable{
    private String nama,satuan,harga_beli,harga_jual,stock,stock_min;
    private String key;

    public Barang() {
    }

    public Barang(String nama, String satuan, String harga_beli, String harga_jual, String stock, String stock_min) {
        this.nama = nama;
        this.satuan = satuan;
        this.harga_beli = harga_beli;
        this.harga_jual = harga_jual;
        this.stock = stock;
        this.stock_min = stock_min;
    }

    @Override
    public String toString() {
        return "Barang{" +
                "nama='" + nama + '\'' +
                ", satuan='" + satuan + '\'' +
                ", harga_beli='" + harga_beli + '\'' +
                ", harga_jual='" + harga_jual + '\'' +
                ", stock='" + stock + '\'' +
                ", stock_min='" + stock_min + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getHarga_beli() {
        return harga_beli;
    }

    public void setHarga_beli(String harga_beli) {
        this.harga_beli = harga_beli;
    }

    public String getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(String harga_jual) {
        this.harga_jual = harga_jual;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStock_min() {
        return stock_min;
    }

    public void setStock_min(String stock_min) {
        this.stock_min = stock_min;
    }
}
