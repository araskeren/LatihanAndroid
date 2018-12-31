package arumfaisal.id.tokogayeng.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsModelTransaksiBarang {
    @SerializedName("data")
    @Expose
    private List<DataModelTransaksiBarang> resultTransaksi;

    public List<DataModelTransaksiBarang> getResultBarang() {
        return resultTransaksi;
    }

    public void setResultBarang(List<DataModelTransaksiBarang> resultTransaksi) {
        this.resultTransaksi = resultTransaksi;
    }
}
