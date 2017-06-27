package com.jdkgroup.retrofit2mvp.retrofit;

import com.jdkgroup.retrofit2mvp.model.MainCity;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

/**
 * Created by kamlesh on 12/17/2016.
 */

public interface ApiService {
    @GET("country/get/all")
    Call<MainCity> getResults();
}
