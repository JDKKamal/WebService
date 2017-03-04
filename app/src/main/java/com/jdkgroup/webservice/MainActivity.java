package com.jdkgroup.webservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.jdkgroup.model.ModelError;
import com.jdkgroup.retrofit2.ApiService;
import com.jdkgroup.retrofit2.RetrofitClient;

import java.net.HttpRetryException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppCompatTextView tvCuurentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCuurentDate = (AppCompatTextView) findViewById(R.id.tvCuurentDate);

        //TODO API Call
        apiCall();
    }

    public void apiCall() {
        RetrofitClient retroClient = new RetrofitClient();
        ApiService apiService = retroClient.getApiService();

        Call<ModelError> call = apiService.getError("201");

        call.enqueue(new Callback<ModelError>() {
            @Override
            public void onResponse(Call<ModelError> call, Response<ModelError> response) {
                //TODO API RESPONSE
                if (response.isSuccessful()) {

                } else {

                }
            }

            @Override
            public void onFailure(Call<ModelError> call, Throwable t) {
                //TODO Fail
                if (t instanceof HttpRetryException) {

                }

                System.out.println(t.getMessage());
            }
        });
    }
}
