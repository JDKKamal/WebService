package com.jdkgroup.loginmvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jdkgroup.loginmvp.widget.CustomProgressbar;

public class BaseAppCompatActivity extends AppCompatActivity {

    private CustomProgressbar customProgressbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    public void DisplayToast(final Activity activity, final String message, final int time, final boolean status) {
        if (status == true) {
            SwitchDisplayToast(activity, message, time);
        }
    }

    private void SwitchDisplayToast(final Activity activity, final String message, final int time) {
        switch (time) {
            case 1:
                Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                break;

            case 2:
                Toast.makeText(activity.getApplicationContext(), message, Toast.LENGTH_LONG).show();
                break;
        }
    }

}
