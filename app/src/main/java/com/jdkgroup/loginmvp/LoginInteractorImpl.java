package com.jdkgroup.loginmvp;

import android.os.Handler;
import android.text.TextUtils;

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {

        boolean error = false;
        if (TextUtils.isEmpty(username)) {
            listener.onUsernameError(username);
            error = true;
            return;
        }
        if (TextUtils.isEmpty(password)) {
            listener.onPasswordError(password);
            error = true;
            return;
        }
        if (!error) {
            listener.onSuccess();
        }
    }
}
