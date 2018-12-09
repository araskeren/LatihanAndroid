package damisbachtiar17.web.id.kota;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retroserver {
    //private  static  final String base_url = "http://192.168.1.2/tugas_android/kota/";

    private  static  final String base_url = "http://damisbachtiar17.web.id/tugas_android/kota/";


    private static Retrofit retrofit;


    public static Retrofit getClient()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return  retrofit;
    }
}

