package com.jdkgroup.retrofitmvp3.view;

import com.jdkgroup.retrofitmvp3.baseclasses.BaseView;
import com.jdkgroup.retrofitmvp3.models.Login;
import com.jdkgroup.retrofitmvp3.models.ModelGetAllCricketFans;

import java.util.List;

public interface LoginView<T> extends BaseView<Login> {
    void onLogin(T response);
}
