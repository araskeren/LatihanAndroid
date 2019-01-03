package damisbachtiar17.web.id.uts.model;

import java.util.List;

public class ResponsModelTransaksi {
    String  kode, pesan;
    List<DataModelTransaksi> result;


    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<DataModelTransaksi> getResult() {
        return result;
    }

    public void setResult(List<DataModelTransaksi> result) {
        this.result = result;
    }
}
