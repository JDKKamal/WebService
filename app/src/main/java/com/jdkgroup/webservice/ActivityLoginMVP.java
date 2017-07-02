/*
 *
 *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.jdkgroup.webservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.jdkgroup.loginmvp.LoginPresenter;
import com.jdkgroup.loginmvp.LoginPresenterImpl;
import com.jdkgroup.loginmvp.LoginView;
import com.jdkgroup.loginmvp.base.BaseAppCompatActivity;
import com.jdkgroup.retrofit2mvp.model.MainCity;
import com.jdkgroup.retrofit2mvp.model.Result;

import java.util.List;

public class ActivityLoginMVP extends BaseAppCompatActivity implements LoginView, View.OnClickListener {

    private Activity activity;

    private AppCompatEditText appedtUsername, appedtPassword;
    private AppCompatButton appbtnLogin;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        activity = this;

        appedtUsername = (AppCompatEditText) findViewById(R.id.appedtUsername);
        appedtPassword = (AppCompatEditText) findViewById(R.id.appedtPassword);

        findViewById(R.id.appbtnLogin).setOnClickListener(this);

        presenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setLoginSuccessful(String username) {

    }

    @Override
    public void setError(String error) {
        DisplayToast(activity, error, 1, true);
    }

    @Override
    public void setCity(MainCity mainCity) {

        List<Result> alResult = mainCity.getRestResponse().getResult();

        for (Result result : alResult) {
            DisplayToast(activity, result.getName(), 1, true);
        }

    }

    @Override
    public void onClick(View v) {
        presenter.validateCredentials(activity, appedtUsername.getText().toString(), appedtPassword.getText().toString());
    }
}
