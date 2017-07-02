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

package com.jdkgroup.loginmvp;

import android.app.Activity;

import com.jdkgroup.retrofit2mvp.model.MainCity;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override public void validateCredentials(final Activity activity, String username, String password) {
        if (loginView != null) {
        }
        loginInteractor.doLogin(activity, username, password, this);
    }

    @Override public void onDestroy() {
        loginView = null;
    }

    @Override public void onFailure(String failure) {
        if (loginView != null) {
            loginView.setError(failure);
        }
    }

    @Override public void onSuccess(MainCity mainCity) {
        if (loginView != null) {
            loginView.setCity(mainCity);
        }
    }
}
