package com.jdkgroup.retrofitmvp3.customviews;

import android.app.Dialog;
import android.content.Context;
import com.jdkgroup.webservice.R;

public class CustomProgressbar {
    private Context context;
    private Dialog dialog;

    public CustomProgressbar(final Context context) {
        this.context = context;
        dialog = new Dialog(context, R.style.CircularProgressTransparent);
    }

    public void show(final Boolean isCancelable) {
        dialog.setCancelable(isCancelable);
        dialog.setContentView(R.layout.custom_progressbar);
        dialog.show();
    }

    public Boolean isShowing() {
        return dialog.isShowing();
    }

    public void hide() {
        if (dialog != null) {
            dialog.cancel();
            dialog.dismiss();
        }
    }
}
