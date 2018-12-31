package arumfaisal.id.paysargayengumum.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsModelData {

    @SerializedName("data")
    @Expose
    private Rdata rdata;

    public Rdata getRdata() {
        return rdata;
    }

    public void setRdata(Rdata rdata) {
        this.rdata = rdata;
    }
}
