package com.jdkgroup.retrofitmvp3.presenter;

import com.jdkgroup.retrofitmvp3.baseclasses.BasePresenter;
import com.jdkgroup.retrofitmvp3.interacter.InterActorCallback;
import com.jdkgroup.retrofitmvp3.models.Login;
import com.jdkgroup.retrofitmvp3.view.LoginView;

import java.util.HashMap;

public class LoginPresenter extends BasePresenter<LoginView> {


    public void callLoginApi(HashMap<String, String> params) {

        if (hasInternet()) {//If no internet it will show toast automatically.

            addSubscription(getAppInteractor().callLoginApi(params, new InterActorCallback<Login>() {
                @Override
                public void onStart() {
                    getView().showProgressDialog(true);
                }

                @Override
                public void onResponse(Login response) {
                    getView().onSuccess(response);
                  /*  if (response.isStatus()) {
                        getView().onSuccess(response);
                    } else {
                        getView().onFailure(response.getMessage());
                    }*/
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
        }
        else{

        }
            //getView().onFailure(R.string.no_internet_message);
    }





}
