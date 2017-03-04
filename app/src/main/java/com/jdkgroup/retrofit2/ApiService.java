package com.jdkgroup.retrofit2;

import com.jdkgroup.model.ModelError;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by kamlesh on 12/17/2016.
 */

public interface ApiService {
    @FormUrlEncoded
    @POST("webservice_goodmorning")
    Call<ModelError> getError(@Field("param") String param);
}
