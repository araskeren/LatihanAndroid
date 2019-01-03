package arumfaisal.id.tokogayeng.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsModelLogin {
    @SerializedName("success")
    @Expose
    private DataModelUser success;

    public DataModelUser getSuccess() {
        return success;
    }

    public void setSuccess(DataModelUser success) {
        this.success = success;
    }
}
