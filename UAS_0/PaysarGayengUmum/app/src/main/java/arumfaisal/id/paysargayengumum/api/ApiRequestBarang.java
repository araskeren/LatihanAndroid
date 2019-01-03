package arumfaisal.id.paysargayengumum.api;

import arumfaisal.id.paysargayengumum.model.ResponsModelBarang;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiRequestBarang {

    @GET("barang")
    Call<ResponsModelBarang> getAllBarang(
            @Header("Authorization") String auth
    );


    @POST("barang/toko")
    @FormUrlEncoded
    Call<ResponsModelBarang> getByToko(
            @Header("Authorization") String auth,
            @Field("toko_id") String toko_id
    );
}
