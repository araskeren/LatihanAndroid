package arumfaisal.id.tokogayeng.api;

import arumfaisal.id.tokogayeng.model.ResponsModelLogin;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiRequestLogin {
    @FormUrlEncoded
    @POST("login")
    Call<ResponsModelLogin> userLogin(@Field("email") String email,
                                      @Field("password") String password);

    @POST("register")
    Call<ResponsModelLogin> userRegister(
            @Field("username") String username,
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("c_password") String c_password
    );

    @POST("details")
    Call<ResponsModelLogin> userDetails();
}
