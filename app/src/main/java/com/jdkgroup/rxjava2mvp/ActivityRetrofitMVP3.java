package com.jdkgroup.rxjava2mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.jdkgroup.rxjava2mvp.baseclasses.SimpleMVPActivity;
import com.jdkgroup.rxjava2mvp.connection.RestConstant;
import com.jdkgroup.rxjava2mvp.models.Login;
import com.jdkgroup.rxjava2mvp.models.User;
import com.jdkgroup.rxjava2mvp.presenter.LoginPresenter;
import com.jdkgroup.rxjava2mvp.utils.AppUtils;
import com.jdkgroup.rxjava2mvp.validator.Validator;
import com.jdkgroup.rxjava2mvp.view.LoginView;
import com.jdkgroup.webservice.R;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<String, String> paramMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideSoftKeyboard();
        setContentView(R.layout.activity_retrofit_mvp_3);
        //getWindow().setBackgroundDrawableResource(R.drawable.bg);
        ButterKnife.bind(this);
        init();
    }

    public void init() {
        AndroidNetworking.initialize(getApplicationContext());

        paramMap = new HashMap<>();
        paramMap.put("location", "38.908133,-77.047119");
        paramMap.put("timestamp", "1458000000");
        getPresenter().callLoginApi(paramMap);

        paramMap = new HashMap();
        paramMap.put("limit", "3");
        getPresenter().callLoginDetail(paramMap);
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
    public void onSuccess(Object response) {
        Login login = (Login) response;
        AppUtils.showToast(getActivity(), login.getStatus());
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

    @Override
    public void onLoginDetail(Object response) {
        List<User> alUser = (List<User>) response;
        AppUtils.showToast(getActivity(), alUser.size() + "");
    }
}
