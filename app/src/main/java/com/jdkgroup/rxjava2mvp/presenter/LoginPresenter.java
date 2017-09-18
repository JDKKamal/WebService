package com.jdkgroup.rxjava2mvp.presenter;

import com.jdkgroup.rxjava2mvp.baseclasses.BasePresenter;
import com.jdkgroup.rxjava2mvp.interacter.InterActorCallback;
import com.jdkgroup.rxjava2mvp.models.Login;
import com.jdkgroup.rxjava2mvp.view.LoginView;

import java.util.List;
import java.util.Map;

public class LoginPresenter extends BasePresenter<LoginView> {

    public void callLoginApi(Map<String, String> params) {
        if (hasInternet()) {
            getAppInteractor().callAPISingle(params, new InterActorCallback<Login>() {
                @Override
                public void onStart() {
                    getView().showProgressDialog(true);
                }

                @Override
                public void onResponse(Login response) {
                    getView().onSuccess(response);
                }

                @Override
                public void onResponse(List<?> response) {

                }

                @Override
                public void onFinish() {
                    getView().showProgressDialog(false);
                }

                @Override
                public void onError(String message) {
                    getView().onFailure(message);
                }
            });
        } else {
            getView().onFailure("Internet connection is not available!");
        }
    }

    public void callLoginDetail(Map<String, String> params) {
        if (hasInternet()) {
            getAppInteractor().callAPIList(params, new InterActorCallback<Login>() {
                @Override
                public void onStart() {
                    getView().showProgressDialog(true);
                }

                @Override
                public void onResponse(Login response) {
                }

                @Override
                public void onResponse(List<?> response) {
                    getView().onLoginDetail(response);
                }

                @Override
                public void onFinish() {
                    getView().showProgressDialog(false);
                }

                @Override
                public void onError(String message) {
                    getView().onFailure(message);
                }
            });
        } else {
            getView().onFailure("Internet connection is not available!");
        }
    }
}
