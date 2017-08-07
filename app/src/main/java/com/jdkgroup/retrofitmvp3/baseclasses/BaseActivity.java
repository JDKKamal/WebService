package com.jdkgroup.retrofitmvp3.baseclasses;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.jdkgroup.retrofitmvp3.connection.RestConstant;
import com.jdkgroup.retrofitmvp3.utils.AppUtils;
import com.jdkgroup.retrofitmvp3.utils.PreferenceUtils;
import com.jdkgroup.webservice.R;

import java.util.HashMap;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends AppCompatActivity {

    public ProgressBar progressToolbar;
    private Dialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        if (!AppConstants.isEnglishLang){

            AppUtils.setLanguage(this, AppConstant.ARABIC‬‬_VALUE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            }
        }*/

        //    setSupportActionBar(toolbar);
      /*  userId= PreferenceUtils.getInstance(this).getUserId();
        accessToken= PreferenceUtils.getInstance(this).getAccessToken();
        AppUtils.loge("id : "+userId);
        AppUtils.loge("token : "+accessToken);*/

    }

    protected void hideSoftKeyboard() {
        try {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getApplicationWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public HashMap<String, String> getDefaultParameter() {
        HashMap<String, String> params = new HashMap<>();
        params.put(RestConstant.DEVICE_TYPE, RestConstant.DEVICE_TYPE_VALUE);

        return params;
    }

    public HashMap<String, String> getDefaultParamWithIdAndToken() {
        HashMap<String, String> params = getDefaultParameter();
        params.put(RestConstant.USER_ID, PreferenceUtils.getInstance(this).getUserId());
        params.put(RestConstant.ACCESS_TOKEN, PreferenceUtils.getInstance(this).getAccessToken());
        return params;
    }

    public void showProgressDialog(boolean show) {
        //Show Progress bar here
        if (show) {
            showProgressDialog();
        } else {
            hideProgressDialog();
        }
    }

    public void showProgressToolBar(boolean show, View view) {
        if (show) {
            progressToolbar.setVisibility(View.VISIBLE);
            if (view != null)
                view.setVisibility(View.GONE);

        } else {
            progressToolbar.setVisibility(View.GONE);
            if (view != null)
                view.setVisibility(View.VISIBLE);
        }
    }

    protected final void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new Dialog(this);
        } else {
            return;
        }
        View view = LayoutInflater.from(this).inflate(R.layout.app_loading_dialog, null, false);

        AppCompatImageView appIvProgressBar = (AppCompatImageView) view.findViewById(R.id.appIvProgressBar);
        Animation a1 = AnimationUtils.loadAnimation(this, R.anim.progress_anim);
        a1.setDuration(1500);
        appIvProgressBar.startAnimation(a1);

        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(view);
        Window window = progressDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent));
        }
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    /**
     * hide progress bar
     */
    protected final void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public Activity getActivity() {
        return this;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void onAuthenticationFailure(String message) {

    }

    public boolean hasInternetWithoutMessage() {
        if (AppUtils.hasInternetConnection(this)) {
            return true;
        } else {
            return false;
        }
    }

}

