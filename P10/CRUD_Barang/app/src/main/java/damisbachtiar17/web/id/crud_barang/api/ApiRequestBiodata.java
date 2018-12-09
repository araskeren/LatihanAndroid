package damisbachtiar17.web.id.crud_barang.api;

import damisbachtiar17.web.id.crud_barang.model.ResponsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequestBiodata {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponsModel> sendBiodata(@Field("nama") String nama,
                                   @Field("harga_jual") String harga_jual,
                                   @Field("harga_beli") String harga_beli,
                                   @Field("stock") String stock);

    @GET("read.php")
    Call<ResponsModel> getBiodata();

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponsModel> updateData( @Field("id") String id,
                                   @Field("nama") String nama,
                                   @Field("harga_jual") String harga_jual,
                                   @Field("harga_beli") String harga_beli,
                                   @Field("stock") String stock);

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponsModel> deleteData(@Field("id") String id);
}
