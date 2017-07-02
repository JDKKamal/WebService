package com.jdkgroup.loginmvp.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jdkgroup.webservice.BuildConfig;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
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

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Customize the request
                    Request request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .removeHeader("Pragma")
                            .build();

                    okhttp3.Response response = chain.proceed(request);
                    response.cacheResponse();
                    return response;
                }
            })
            .build();


    private Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL) .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
