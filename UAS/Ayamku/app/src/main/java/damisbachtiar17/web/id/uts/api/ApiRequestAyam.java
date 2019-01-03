package damisbachtiar17.web.id.uts.api;

import damisbachtiar17.web.id.uts.model.ResponsModelAyam;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequestAyam {
    @FormUrlEncoded
    @POST("barang/insert.php")
    Call<ResponsModelAyam> sendBiodata(@Field("nama") String nama,
                                       @Field("harga") String harga,
                                       @Field("deskripsi") String deskripsi,
                                       @Field("gambar") String gambar);

    @GET("barang/read.php")
    Call<ResponsModelAyam> getBiodata();

    @FormUrlEncoded
    @POST("barang/update.php")
    Call<ResponsModelAyam> updateData( @Field("id") String id,
                                   @Field("nama") String nama,
                                   @Field("harga") String harga,
                                   @Field("deskripsi") String deskripsi,
                                   @Field("gambar") String gambar);

    @FormUrlEncoded
    @POST("barang/delete.php")
    Call<ResponsModelAyam> deleteData(@Field("id") String id);
}
