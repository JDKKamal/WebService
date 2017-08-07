package com.jdkgroup.retrofitmvp2.retrofit;

import com.jdkgroup.retrofitmvp2.model.MainCity;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kamlesh on 12/17/2016.
 */

public interface ApiService {
    @GET("country/get/all")
    Call<MainCity> getResults();
}
