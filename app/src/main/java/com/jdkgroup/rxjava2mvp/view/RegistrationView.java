package com.jdkgroup.rxjava2mvp.view;

import com.jdkgroup.rxjava2mvp.baseclasses.BaseView;
import com.jdkgroup.rxjava2mvp.models.Login;

public interface RegistrationView extends BaseView<Login> {
    public void responseCity(Login response);
}

