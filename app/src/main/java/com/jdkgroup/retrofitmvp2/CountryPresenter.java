package com.jdkgroup.retrofitmvp2;

import android.content.Context;
import com.jdkgroup.retrofitmvp2.model.MainCity;
import com.jdkgroup.retrofitmvp2.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kamlesh on 6/27/2017.
 */

public class CountryPresenter {
    private final Context context;
    private final CountryPresenterListener mListener;
    private final RetrofitClient retrofitClient;

    public CountryPresenter(CountryPresenterListener listener, Context context){
        this.mListener = listener;
        this.context = context;
        this.retrofitClient = new RetrofitClient();
    }

    public void getCountries(){
        retrofitClient
                .getApiService()
                .getResults()
                .enqueue(new Callback<MainCity>() {
                    @Override
                    public void onResponse(Call<MainCity> call, Response<MainCity> response) {
                        MainCity mainCity = response.body();

                        if(mainCity != null)
                            mListener.countriesReady(mainCity);
                    }

                    @Override
                    public void onFailure(Call<MainCity> call, Throwable t) {
                        try {
                            throw  new InterruptedException(t.getMessage());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    public void appName(String appname)
    {
        mListener.appName("Tag" + appname);
    }
}