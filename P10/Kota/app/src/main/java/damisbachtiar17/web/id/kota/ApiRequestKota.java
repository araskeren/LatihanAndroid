package damisbachtiar17.web.id.kota;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequestKota {
    @GET("read.php")
    Call<ResponsModel> getKota();
}
