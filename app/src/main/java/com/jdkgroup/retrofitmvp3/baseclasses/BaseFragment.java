package com.jdkgroup.retrofitmvp3.baseclasses;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.jdkgroup.retrofitmvp3.connection.RestConstant;
import com.jdkgroup.retrofitmvp3.utils.AppUtils;
import com.jdkgroup.retrofitmvp3.utils.PreferenceUtils;
import com.jdkgroup.webservice.R;

import java.util.HashMap;

public class BaseFragment extends Fragment {

    private Dialog progressDialog;
    public ProgressBar progress;

    public boolean onParentViewClick(Object parent, View view) {
        return false;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //  setRetainInstance(true);
    }

    public void showProgressDialog(boolean show) {
        //Show Progress bar here
        if (show) {
            showProgressBar();
        } else {
            hideProgressDialog();
        }
    }

    /**
     * show progress bar
     */
    protected final void showProgressBar() {
        if (progressDialog == null) {
            progressDialog = new Dialog(getActivity());
        }
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.app_loading_dialog, null, false);

        AppCompatImageView imageView1 = (AppCompatImageView) view.findViewById(R.id.appIvProgressBar);
        Animation a1 = AnimationUtils.loadAnimation(getActivity(), R.anim.progress_anim);
        a1.setDuration(1500);
        imageView1.startAnimation(a1);

        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(view);
        Window window = progressDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), android.R.color.transparent));
            //window.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        }
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public void showProgressToolBar(boolean show,View view) {
  //      ((BaseActivity)getActivity()).showProgressToolBar(show,view);

    }

    public void onAuthenticationFailure(String msg) {
        // logoutUser(msg);
    }

    /**
     * hide progress bar
     */
    protected final void hideProgressBar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
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

    public HashMap<String, String> getDefaultParameter() {
        HashMap<String, String> params = new HashMap<>();
        params.put(RestConstant.DEVICE_TYPE, RestConstant.DEVICE_TYPE_VALUE);

        return params;
    }

    public HashMap<String, String> getDefaultParamWithIdAndToken() {
        HashMap<String, String> params = getDefaultParameter();
        params.put(RestConstant.USER_ID, PreferenceUtils.getInstance(getContext()).getUserId());
        params.put(RestConstant.ACCESS_TOKEN, PreferenceUtils.getInstance(getContext()).getAccessToken());
        return params;
    }

    public boolean hasInternetWithoutMessage(){
        if(AppUtils.hasInternetConnection(getActivity())){
            return true;
        }else{
            return false;
        }
    }
}
