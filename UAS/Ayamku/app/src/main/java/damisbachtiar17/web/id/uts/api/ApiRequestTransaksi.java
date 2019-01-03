package damisbachtiar17.web.id.uts.api;

import java.util.ArrayList;

import damisbachtiar17.web.id.uts.model.ResponsModelTransaksi;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRequestTransaksi {
    @FormUrlEncoded
    @POST("transaksi/insert.php")
    Call<ResponsModelTransaksi> insertTransaksi(@Field("barang_id") String barang_id,
                                        @Field("geray_id") String geray_id,
                                        @Field("status") String status);

    @FormUrlEncoded
    @POST("transaksi/read.php")
    Call<ResponsModelTransaksi> getTransaksi(@Field("status") String status,
                                             @Field("geray_id")String geray_id);

    @FormUrlEncoded
    @POST("transaksi/update.php")
    Call<ResponsModelTransaksi> updateTransaksi(@Field("id[]") String[] id,
                                            @Field("status") String status);

    @FormUrlEncoded
    @POST("transaksi/delete.php")
    Call<ResponsModelTransaksi> deleteTransaksi(@Field("id") String id);
}
