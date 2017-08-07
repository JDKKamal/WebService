package com.jdkgroup.loginmvp;

import android.app.Activity;
import android.text.TextUtils;

import com.jdkgroup.loginmvp.base.BaseAppCompatActivity;
import com.jdkgroup.loginmvp.retrofit.RetrofitClient;
import com.jdkgroup.retrofitmvp2.model.MainCity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl extends BaseAppCompatActivity implements LoginInteractor {
    private RetrofitClient retrofitClient;

    @Override
    public void doLogin(final Activity activity, final String username, final String password, final OnLoginFinishedListener listener) {

        boolean error = false;
        if (TextUtils.isEmpty(username)) {
            listener.onFailure("Username empty");
            error = true;
            return;
        }
        if (TextUtils.isEmpty(password)) {
            listener.onFailure("Password empty");
            error = true;
            return;
        }
        if (!error) {
            showProgressDialog(activity);
            retrofitClient = new RetrofitClient();

            retrofitClient
                    .getApiService()
                    .getResults()
                    .enqueue(new Callback<MainCity>() {
                        @Override
                        public void onResponse(Call<MainCity> call, Response<MainCity> response) {
                            MainCity mainCity = response.body();

                            if (mainCity != null)
                                listener.onSuccess(mainCity);
                            hideProgressDialog();
                        }

                        @Override
                        public void onFailure(Call<MainCity> call, Throwable t) {
                            hideProgressDialog();
                        }
                    });
        }
    }
}
