package damisbachtiar17.web.id.pasargayeng.model;

import android.provider.ContactsContract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
