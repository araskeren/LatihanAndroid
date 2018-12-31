package damisbachtiar17.web.id.pasargayeng.api;

import damisbachtiar17.web.id.pasargayeng.model.ResponsModelData;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelLogin;
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
    @FormUrlEncoded
    Call<ResponsModelLogin> userRegister(
            @Field("username") String username,
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("c_password") String c_password,
            @Field("no_telp") String no_telp,
            @Field("alamat") String alamat
    );

    @POST("details")
    @FormUrlEncoded
    Call<ResponsModelLogin> userDetails();

}
