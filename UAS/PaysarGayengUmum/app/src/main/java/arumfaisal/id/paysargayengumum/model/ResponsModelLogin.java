package arumfaisal.id.paysargayengumum.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsModelLogin {
    @SerializedName("success")
    @Expose
    private Sukses success;

    public Sukses getSuccess() {
        return success;
    }

    public void setSuccess(Sukses success) {
        this.success = success;
    }


}
