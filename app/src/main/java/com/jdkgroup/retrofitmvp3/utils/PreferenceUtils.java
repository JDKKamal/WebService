package com.jdkgroup.retrofitmvp3.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.jdkgroup.retrofitmvp3.connection.RestConstant;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceUtils {
    private final static String SP_NAME = "otc";
    private static PreferenceUtils preferenceUtils;
    private SharedPreferences sharedPreferences;
    private Context mContext;

    private PreferenceUtils(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = mContext.getSharedPreferences(SP_NAME, MODE_PRIVATE);
    }

    public static PreferenceUtils getInstance(Context mContext) {
        return preferenceUtils = (preferenceUtils == null ? new PreferenceUtils(mContext) : preferenceUtils);
    }

    public String getDeviceToken() {
        return sharedPreferences.getString(RestConstant.DEVICE_TOKEN, "");
    }

    public void setDeviceToken(String device_token) {
        sharedPreferences.edit().putString(RestConstant.DEVICE_TOKEN, device_token).apply();
    }

    public String getAccessToken() {
        return sharedPreferences.getString(RestConstant.ACCESS_TOKEN, "");
    }

    public String getUserId() {
        return sharedPreferences.getString(RestConstant.USER_ID, "");
    }

    private static void removeInstance() {
        preferenceUtils = null;
    }

    public void clearAllPrefs() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        removeInstance();
    }
}
