package damisbachtiar17.web.id.pasargayeng.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsModelTransaksiUser {
    @SerializedName("data")
    @Expose
    private List<DataModelTransaksiUser> resultTransaksi;

    public List<DataModelTransaksiUser> getResultBarang() {
        return resultTransaksi;
    }

    public void setResultBarang(List<DataModelTransaksiUser> resultTransaksi) {
        this.resultTransaksi = resultTransaksi;
    }

    @SerializedName("total")
    @Expose
    private Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
