package com.jdkgroup.retrofitmvp3.view;

import com.jdkgroup.retrofitmvp3.baseclasses.BaseView;
import com.jdkgroup.retrofitmvp3.models.Login;

public interface RegistrationView extends BaseView<Login> {
    public void responseCity(Login response);
}

