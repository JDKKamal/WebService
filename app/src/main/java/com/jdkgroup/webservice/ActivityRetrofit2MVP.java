package com.jdkgroup.webservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;

import com.jdkgroup.model.ModelError;
import com.jdkgroup.retrofit2.ApiService;
import com.jdkgroup.retrofit2.RetrofitClient;
import com.jdkgroup.retrofit2mvp.CountryPresenter;
import com.jdkgroup.retrofit2mvp.CountryPresenterListener;
import com.jdkgroup.retrofit2mvp.model.MainCity;
import com.jdkgroup.retrofit2mvp.model.Result;

import java.net.HttpRetryException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRetrofit2MVP extends AppCompatActivity implements CountryPresenterListener {

    private CountryPresenter countryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryPresenter = new CountryPresenter(this, this);
        countryPresenter.getCountries();
        countryPresenter.appName("Simple MVP");
    }

    @Override
    public void countriesReady(MainCity mainCity) {
        List<Result> alResult = mainCity.getRestResponse().getResult();

        for (Result result : alResult) {
            System.out.println("Tag" + result.getName());
        }
    }

    @Override
    public void appName(String appname) {
        System.out.println("Tag" + appname);
    }
}