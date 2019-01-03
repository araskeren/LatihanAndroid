package arumfaisal.id.paysargayengumum.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sukses {
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("user")
    @Expose
    private DetailUser detailUser;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DetailUser getDetailUser() {
        return detailUser;
    }

    public void setDetailUser(DetailUser detailUser) {
        this.detailUser = detailUser;
    }

}
