package arumfaisal.id.paysargayengumum.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsModelToko {
    @SerializedName("data")
    @Expose
    List<DataModelToko> resultToko;

    public List<DataModelToko> getResultBarang() {
        return resultToko;
    }

    public void setResultBarang(List<DataModelToko> resultToko) {
        this.resultToko = resultToko;
    }
}
