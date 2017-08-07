package com.jdkgroup.retrofitmvp2.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kamlesh on 12/17/2016.
 */

public class RetrofitClient {
    private final String ROOT_URL = "http://services.groupkt.com/";

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();

    private Retrofit getRetrofitInstance() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("User-Agent", "Your-App-Name")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClient.build();
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }

    public ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
