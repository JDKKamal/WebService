package com.jdkgroup.webservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jdkgroup.retrofitmvp2.CountryPresenter;
import com.jdkgroup.retrofitmvp2.CountryPresenterListener;
import com.jdkgroup.retrofitmvp2.model.MainCity;
import com.jdkgroup.retrofitmvp2.model.Result;

import java.util.List;

public class ActivityRetrofitMVP2 extends AppCompatActivity implements CountryPresenterListener {

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