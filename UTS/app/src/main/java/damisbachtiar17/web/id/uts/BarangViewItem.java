package damisbachtiar17.web.id.uts;

public class BarangViewItem{

    // Save car name.
    private String namaBarang,deskripsiBarang,total;

    // Save car image resource id.
    private int idGambar;

    private int idHarga;




    public BarangViewItem(String namaBarang, int idGambar, int hargaBarang,String deskripsi) {
        this.namaBarang = namaBarang;
        this.deskripsiBarang=deskripsi;
        this.idGambar = idGambar;
        this.idHarga = hargaBarang;
        this.total=total;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getTotalBarang() {
        return total;
    }

    public void setTotalBarang(String total) {
        this.total = total;
    }

    public String getDeskripsiBarang() {
        return deskripsiBarang;
    }

    public void setDeskripsiBarang(String deskripsi) {
        this.deskripsiBarang = deskripsi;
    }

    public int getIdGambar() {
        return idGambar;
    }

    public void setIdGambar(int carImageId) {
        this.idGambar = carImageId;
    }

    public int getIdHarga() {
        return idHarga;
    }

    public void setIdHarga(int hargaId) {
        this.idHarga = hargaId;
    }

}