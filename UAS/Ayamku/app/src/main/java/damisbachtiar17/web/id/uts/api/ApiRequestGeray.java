package damisbachtiar17.web.id.uts.api;


import damisbachtiar17.web.id.uts.model.ResponsModelGeray;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequestGeray {

    @FormUrlEncoded
    @POST("geray/insert.php")
    Call<ResponsModelGeray> insertGeray(@Field("nama") String nama,
                                       @Field("no_telp") String no_telp,
                                       @Field("alamat") String alamat);

    @GET("geray/read.php")
    Call<ResponsModelGeray> getGeray();

    @FormUrlEncoded
    @POST("geray/update.php")
    Call<ResponsModelGeray> updateGeray(@Field("id") String id,
                                        @Field("nama") String nama,
                                        @Field("no_telp") String no_telp,
                                        @Field("alamat") String alama);

    @FormUrlEncoded
    @POST("geray/delete.php")
    Call<ResponsModelGeray> deleteGeray(@Field("id") String id);
}
