package com.jdkgroup.retrofitmvp3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.google.gson.Gson;
import com.jdkgroup.retrofitmvp3.baseclasses.SimpleMVPActivity;
import com.jdkgroup.retrofitmvp3.connection.RestConstant;
import com.jdkgroup.retrofitmvp3.models.Login;
import com.jdkgroup.retrofitmvp3.presenter.LoginPresenter;
import com.jdkgroup.retrofitmvp3.utils.AppUtils;
import com.jdkgroup.retrofitmvp3.validator.Validator;
import com.jdkgroup.retrofitmvp3.view.LoginView;
import com.jdkgroup.webservice.R;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityRetrofitMVP3 extends SimpleMVPActivity<LoginPresenter, LoginView> implements LoginView {

    @BindView(R.id.etEmail)
    AppCompatEditText etEmail;
    @BindView(R.id.etPassword)
    AppCompatEditText etPassword;
    @BindView(R.id.tvForgotPassword)
    AppCompatTextView tvForgotPassword;
    @BindView(R.id.ivLoginButton)
    AppCompatImageView ivLoginButton;
    @BindView(R.id.tvRegister)
    AppCompatTextView tvRegister;
    boolean flag = false;
    @BindView(R.id.ivEye)
    AppCompatImageView ivEye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSoftKeyboard();
        setContentView(R.layout.activity_retrofit_mvp_3);
        //getWindow().setBackgroundDrawableResource(R.drawable.bg);
        ButterKnife.bind(this);
        init();

        HashMap<String, String> mapLogin = getDefaultParameter();
        getPresenter().callLoginApi(mapLogin);
    }

    public void init() {

    }

    @NonNull
    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @NonNull
    @Override
    public LoginView attachView() {
        return this;
    }

    @Override
    public void onSuccess(Login response) {
        Gson gson = new Gson();
        AppUtils.showToast(this, gson.toJson(response));
        //AppUtils.startActivity(this,HomeActivity.class);
//        Intent intent = new Intent(this, HomeActivity.class);
//
//        startActivity(intent);
        openHomeScreen();
    }

    @Override
    public void onFailure(String message) {
        AppUtils.showToast(this, message);
    }

    @OnClick({R.id.tvForgotPassword, R.id.ivLoginButton, R.id.tvRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvForgotPassword:
                break;
            case R.id.ivLoginButton:
                checkCredential();
                //openHomeScreen();

                break;
            case R.id.tvRegister:
                break;
        }
    }

    public void checkCredential() {
        if (Validator.isEmplty(etEmail)) {
            AppUtils.showToastById(this, R.string.val_empty_email);
        } else if (!Validator.isEmail(Validator.getText(etEmail))) {
            AppUtils.showToastById(this, R.string.val_invalid_email);
        } else if (Validator.isEmplty(etPassword)) {
            AppUtils.showToastById(this, R.string.val_empty_password);
        } else {
            HashMap<String, String> mapLogin = getDefaultParameter();
            mapLogin.put(RestConstant.EMAIL, Validator.getText(etEmail));
            try {
                mapLogin.put(RestConstant.PASSWRORD, AppUtils.SHA1(Validator.getText(etPassword)));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            mapLogin.put(RestConstant.USER_TYPE, RestConstant.USER_TYPE_VALUE);
            getPresenter().callLoginApi(mapLogin);
        }
    }

    private void openHomeScreen() {
    }

    @OnClick(R.id.ivEye)
    public void onViewClicked() {

        // your action here
        if (flag) {
            flag = false;
            ivEye.setImageResource(R.drawable.eye);
            etPassword.setTransformationMethod(new PasswordTransformationMethod());
        } else {
            flag = true;
            ivEye.setImageResource(R.drawable.eye_gray);
            etPassword.setTransformationMethod(null);
        }
        etPassword.setSelection(etPassword.getText().length());
    }
}
