package com.jdkgroup.loginmvp.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class CallBackRetrofit<T> implements Callback<T> {

    public abstract void onSuccess(Response<T> response);

    public abstract void onFailure(Response<T> response);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (response.code() >= 400 && response.code() < 599) {
            onFailure(response);
        } else {
            onSuccess(response);
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
    }
}