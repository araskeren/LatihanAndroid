package damisbachtiar17.web.id.uts.model;

import java.util.List;

public class ResponsModelGeray {
    String  kode, pesan;
    List<DataModelGeray> result;


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

    public List<DataModelGeray> getResult() {
        return result;
    }

    public void setResult(List<DataModelGeray> result) {
        this.result = result;
    }
}
