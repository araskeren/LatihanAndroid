package damisbachtiar17.web.id.pasargayeng.api;

import damisbachtiar17.web.id.pasargayeng.model.ResponsModelData;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelLogin;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelToko;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiRequestToko {

    @GET("toko")
    Call<ResponsModelToko> getAllToko(
            @Header("Authorization") String auth
    );

    @FormUrlEncoded
    @POST("toko")
    Call<ResponsModelData> userToko(
            @Header("Authorization") String auth,
            @Field("nama") String nama,
            @Field("alamat") String alamat,
            @Field("pemilik_id") Integer pemilik_id
    );

}