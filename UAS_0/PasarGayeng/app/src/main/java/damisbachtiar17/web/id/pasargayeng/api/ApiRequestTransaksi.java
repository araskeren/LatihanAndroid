package damisbachtiar17.web.id.pasargayeng.api;

import damisbachtiar17.web.id.pasargayeng.model.ResponsModelTransaksi;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelTransaksiUpdate;
import damisbachtiar17.web.id.pasargayeng.model.ResponsModelTransaksiUser;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiRequestTransaksi {
    @GET("transaksi/user")
    Call<ResponsModelTransaksiUser> getAllTransaksi(
            @Header("Authorization") String auth
    );

    @FormUrlEncoded
    @POST("transaksi")
    Call<ResponsModelTransaksi> addTransaksi(
                    @Header("Authorization") String auth,
                    @Field("barang_id") String barang_id
            );

    @FormUrlEncoded
    @POST("transaksi/update")
    Call<ResponsModelTransaksiUpdate> updateTransaksi(
            @Header("Authorization") String auth,
            @Field("id[]") String[] id,
            @Field("status") String status
    );
}
