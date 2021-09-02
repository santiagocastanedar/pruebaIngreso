package co.com.ceiba.mobile.pruebadeingreso.apiController;

import java.util.concurrent.TimeUnit;

import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.URL_BASE;

public class Retrofit2Client {

    private static Retrofit2Client instance;


    private ApiController apiController;

    public static Retrofit2Client getInstance() {
        if (instance == null) {
            instance = new Retrofit2Client();
        }

        return instance;
    }

    private Retrofit2Client() {
        buildRetrofit(URL_BASE);
    }

    private void buildRetrofit(String url) {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiController = retrofit.create(ApiController.class);
    }

    public ApiController getApiController() {
        return apiController;
    }
}