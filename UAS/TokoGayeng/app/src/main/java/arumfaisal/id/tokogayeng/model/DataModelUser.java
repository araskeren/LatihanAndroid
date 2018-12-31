package arumfaisal.id.tokogayeng.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataModelUser {
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("user")
    @Expose
    private DataDetailModelUser detailUser;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DataDetailModelUser getDetailUser() {
        return detailUser;
    }

    public void setDetailUser(DataDetailModelUser detailUser) {
        this.detailUser = detailUser;
    }
}
