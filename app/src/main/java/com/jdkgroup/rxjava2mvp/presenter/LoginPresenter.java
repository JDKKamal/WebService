package com.jdkgroup.rxjava2mvp.presenter;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.AnalyticsListener;
import com.jdkgroup.rxjava2mvp.baseclasses.BasePresenter;
import com.jdkgroup.rxjava2mvp.connection.RestConstant;
import com.jdkgroup.rxjava2mvp.models.Login;
import com.jdkgroup.rxjava2mvp.view.LoginView;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginView> {

    public void callLoginApi(Map<String, String> params) {
        if (hasInternet()) {
            getAPI(params, Login.class);
        }
    }

    public <T> void getAPI(Map<String, String> params, final Class<T> classObject) {
        Rx2AndroidNetworking.get(RestConstant.BASE_URL + RestConstant.END_POINT_STRING)
                .addQueryParameter(params)
                .build()
                .setAnalyticsListener(new AnalyticsListener() {
                    @Override
                    public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {

                    }
                })
                .getObjectObservable(classObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onComplete() {
                        getView().showProgressDialog(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showProgressDialog(false);
                        if (e instanceof ANError) {
                            ANError anError = (ANError) e;
                            if (anError.getErrorCode() != 0) {
                                getView().onFailure(anError.getErrorDetail());
                            } else {
                                getView().onFailure(anError.getErrorDetail());
                            }
                        } else {
                            getView().onFailure(e.getMessage());
                        }
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull T response) {
                      getView().onLogin(response);

                    }
                });
    }
}
