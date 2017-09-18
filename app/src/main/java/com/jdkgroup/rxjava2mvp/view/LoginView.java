package com.jdkgroup.rxjava2mvp.view;

import com.jdkgroup.rxjava2mvp.baseclasses.BaseView;
import com.jdkgroup.rxjava2mvp.models.Login;

public interface LoginView<T> extends BaseView<Login> {
    void onLogin(T response);
}
