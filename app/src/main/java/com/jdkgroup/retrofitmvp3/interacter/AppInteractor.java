package com.jdkgroup.retrofitmvp3.interacter;


import com.google.gson.Gson;
import com.jdkgroup.retrofitmvp3.connection.RestConstant;
import com.jdkgroup.retrofitmvp3.models.Login;
import com.jdkgroup.retrofitmvp3.models.Response;
import com.jdkgroup.retrofitmvp3.utils.AppUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.jdkgroup.retrofitmvp3.connection.RestClient.getPrimaryService;

public class AppInteractor {

    public boolean isCancel;

    public AppInteractor() {

    }

    private void sendResponse(InterActorCallback callback, Response response) {
        if (!isCancel) {
            callback.onResponse(response);
        }
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void cancel() {
        isCancel = true;
    }

    private void displayRequestParams(HashMap<String, String> hashMap) {
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            AppUtils.loge((String) pair.getKey() + (String) pair.getValue());
        }
    }

    //Call Api for Login
    public Subscription callLoginApi(final HashMap<String, String> params, final InterActorCallback<Login> callback) {
        return getPrimaryService().apiPost(RestConstant.BASE_URL + RestConstant.LOGIN, params)
                .map(new Func1<String, Login>() {
                    @Override
                    public Login call(String s) {
                        displayRequestParams(params);
                        AppUtils.loge("callLoginApi" + "" + s);
                        return new Gson().fromJson(s, Login.class);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new InterActorOnSubscribe<>(callback))
                .subscribe(new InterActorSubscriber<Login>(callback, this) {
                    @Override
                    public void onNext(Login response) {
                        sendResponse(callback, response);
                    }
                });
    }

}

