package com.jdkgroup.retrofitmvp3.presenter;
import android.content.Context;

import com.jdkgroup.retrofitmvp3.baseclasses.BasePresenter;
import com.jdkgroup.retrofitmvp3.view.RegistrationView;

import java.util.HashMap;

public class RegistrationPresenter extends BasePresenter<RegistrationView> {

    public void callRegisterAPI(HashMap<String, String> params) {

        if (hasInternet()) {//If no internet it will show toast automatically.

        } else {

        }
    }

    public void callCityApi(Context mContext, HashMap<String, String> params) {

    }
}