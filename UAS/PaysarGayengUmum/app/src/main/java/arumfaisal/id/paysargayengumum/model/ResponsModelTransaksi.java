package arumfaisal.id.paysargayengumum.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsModelTransaksi {
    @SerializedName("data")
    @Expose
    private DataModelTransaksi resultTransaksi;

    public DataModelTransaksi getResultBarang() {
        return resultTransaksi;
    }

    public void setResultBarang(DataModelTransaksi resultTransaksi) {
        this.resultTransaksi = resultTransaksi;
    }


}
