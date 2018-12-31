package arumfaisal.id.tokogayeng.api;

import arumfaisal.id.tokogayeng.model.ResponsModelTransaksiBarang;
import arumfaisal.id.tokogayeng.model.ResponsModelTransaksiBarangUpdate;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiRequestTransaksi {
    @FormUrlEncoded
    @POST("transaksi/toko/get-all")
    Call<ResponsModelTransaksiBarang> getAllTransaksiToko(
            @Header("Authorization") String auth,
            @Field("toko_id") String toko_id,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("transaksi/update/by-id")
    Call<ResponsModelTransaksiBarangUpdate> updateTransaksiByTransaksiId(
            @Header("Authorization") String auth,
            @Field("transaksi_id") String transaksi_id,
            @Field("status") String status
    );
}
