package com.jdkgroup.retrofitmvp3.connection;

import java.util.HashMap;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

public interface RestService {

    @FormUrlEncoded
    @POST
    Observable<String> apiPost(@Url String url, @FieldMap HashMap<String, String> params);
}


