package arumfaisal.id.tokogayeng.api;

import arumfaisal.id.tokogayeng.model.ResponsModelAddBarang;
import arumfaisal.id.tokogayeng.model.ResponsModelBarang;
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

    @GET("barang/user")
    Call<ResponsModelBarang> getBarangByUser(
            @Header("Authorization") String auth
    );


    @POST("barang/toko")
    @FormUrlEncoded
    Call<ResponsModelBarang> getByToko(
            @Header("Authorization") String auth,
            @Field("toko_id") String toko_id
    );

    @POST("barang/create")
    @FormUrlEncoded
    Call<ResponsModelAddBarang> tambahBarang(
            @Header("Authorization") String auth,
            @Field("toko_id") String toko_id,
            @Field("nama") String nama,
            @Field("harga") String harga,
            @Field("deskripsi") String deskripsi,
            @Field("gambar") String gambar
    );

    @POST("barang/edit")
    @FormUrlEncoded
    Call<ResponsModelAddBarang> editBarang(
            @Header("Authorization") String auth,
            @Field("id") String id_barang,
            @Field("nama") String nama,
            @Field("harga") String harga,
            @Field("deskripsi") String deskripsi,
            @Field("gambar") String gambar
    );

    @POST("barang/delete")
    @FormUrlEncoded
    Call<ResponsModelAddBarang> deleteBarang(
            @Header("Authorization") String auth,
            @Field("id") String id_barang
    );
}
