package com.jdkgroup.loginmvp;

import android.app.Activity;
import com.jdkgroup.retrofit2mvp.model.MainCity;

public interface LoginInteractor {

    interface OnLoginFinishedListener {
        void onFailure(String username);
        void onSuccess(MainCity mainCity);
    }

    void doLogin(final Activity activity, String username, String password, OnLoginFinishedListener listener);

}
