package damisbachtiar17.web.id.uts.model;

import java.util.List;

public class ResponsModelAyam {
    String  kode, pesan;
    List<DataModelAyam> result;


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

    public List<DataModelAyam> getResult() {
        return result;
    }

    public void setResult(List<DataModelAyam> result) {
        this.result = result;
    }
}
