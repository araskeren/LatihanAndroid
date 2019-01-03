package damisbachtiar17.web.id.pasargayeng.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsModelBarang {

    @SerializedName("data")
    @Expose
    List<DataModelBarang> resultBarang;

    public List<DataModelBarang> getResultBarang() {
        return resultBarang;
    }

    public void setResultBarang(List<DataModelBarang> resultBarang) {
        this.resultBarang = resultBarang;
    }
}
