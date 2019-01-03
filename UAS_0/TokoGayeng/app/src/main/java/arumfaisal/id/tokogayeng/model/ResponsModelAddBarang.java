package arumfaisal.id.tokogayeng.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsModelAddBarang {

    @SerializedName("data")
    @Expose
    DataModelBarang resultBarang;

    public DataModelBarang getResultBarang() {
        return resultBarang;
    }

    public void setResultBarang(DataModelBarang resultBarang) {
        this.resultBarang = resultBarang;
    }
}
