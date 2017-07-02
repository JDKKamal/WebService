package com.jdkgroup.loginmvp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.jdkgroup.loginmvp.widget.CustomProgressbar;

public class BaseFragment extends Fragment {

    private CustomProgressbar customProgressbar = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showProgressDialog(Context context) {
        customProgressbar = new CustomProgressbar(context);
        if (customProgressbar != null && customProgressbar.isShowing()) {
            customProgressbar.hide();
        }
        customProgressbar.show(false);
        customProgressbar.isShowing();
    }

    public  void hideProgressDialog() {
        if (customProgressbar != null && customProgressbar.isShowing()) {
            customProgressbar.hide();
        }
        customProgressbar = null;
    }

}
