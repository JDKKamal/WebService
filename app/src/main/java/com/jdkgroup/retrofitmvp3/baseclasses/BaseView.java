package com.jdkgroup.retrofitmvp3.baseclasses;

import android.app.Activity;
import android.view.View;

import java.util.HashMap;

public interface BaseView<T> {
    boolean hasInternet();

    boolean hasInternetWithoutMessage();

    void showProgressDialog(boolean show);

    void showProgressToolBar(boolean show, View view);

    void onSuccess(T response);

    void onFailure(String message);

    void onAuthenticationFailure(String message);

    HashMap<String, String> getDefaultParameter();

    Activity getActivity();
}