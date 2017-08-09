package com.jdkgroup.retrofitmvp3.presenter;
import android.content.Context;

import com.jdkgroup.retrofitmvp3.baseclasses.BasePresenter;
import com.jdkgroup.retrofitmvp3.interacter.InterActorCallback;
import com.jdkgroup.retrofitmvp3.models.Login;
import com.jdkgroup.retrofitmvp3.view.RegistrationView;
import com.jdkgroup.webservice.R;

import java.util.HashMap;

public class RegistrationPresenter extends BasePresenter<RegistrationView> {

    public void callRegistationApi(HashMap<String, String> params) {

        if (hasInternet()) {//If no internet it will show toast automatically.

            addSubscription(getAppInteractor().callLoginApi(params, new InterActorCallback<Login>() {
                @Override
                public void onStart() {
                    getView().showProgressDialog(true);
                }

                @Override
                public void onResponse(Login response) {
                    getView().onSuccess(response);
                }

                @Override
                public void onFinish() {
                    getView().showProgressDialog(false);
                }

                @Override
                public void onError(String message) {
                    getView().onFailure(message);
                }
            }));
        } else {

        }
    }

    public void callCityApi(Context mContext, HashMap<String, String> params) {

        if (hasInternet()) {//If no internet it will show toast automatically.

            addSubscription(getAppInteractor().callLoginApi(params, new InterActorCallback<Login>() {
                @Override
                public void onStart() {
                    getView().showProgressDialog(true);
                }

                @Override
                public void onResponse(Login response) {
                    getView().responseCity(response);
                }

                @Override
                public void onFinish() {
                    getView().showProgressDialog(false);
                }

                @Override
                public void onError(String message) {
                    getView().onFailure(message);
                }
            }));
        } else {
            getView().onFailure(mContext.getString(R.string.no_internet_message));
        }
    }
}