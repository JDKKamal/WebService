package com.jdkgroup.rxjava2mvp.interacter;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.AnalyticsListener;
import com.jdkgroup.rxjava2mvp.connection.RestConstant;
import com.jdkgroup.rxjava2mvp.models.Login;
import com.jdkgroup.rxjava2mvp.models.Response;
import com.jdkgroup.rxjava2mvp.models.User;
import com.jdkgroup.rxjava2mvp.utils.AppUtils;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AppInteractor {

    public AppInteractor() {
    }

    public void sendResponse(InterActorCallback callback, Response response) {
        callback.onResponse(response);
    }

    private void displayRequestParams(HashMap<String, String> hashMap) {
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            AppUtils.loge((String) pair.getKey() + (String) pair.getValue());
        }
    }

    public void callAPISingle(final Map<String, String> params, final InterActorCallback<Login> callback) {
        getAPISingle(RestConstant.BASE_URL + RestConstant.END_POINT_STRING, params, Login.class, callback);
        callback.onStart();
    }

    public void callAPIList(final Map<String, String> params, final InterActorCallback<Login> callback) {
        getAPIList(RestConstant.API_GETANUSER, params, User.class, callback);
        callback.onStart();
    }

    /* TODO GENERIC Rx2AndroidNetworking USE FOR API CALLING */
    public <T extends Response> void getAPISingle(String url, Map<String, String> params, final Class<T> classObject, final InterActorCallback<?> callback) {
        Rx2AndroidNetworking.get(url)
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
                        callback.onFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFinish();
                        if (e instanceof ANError) {
                            ANError anError = (ANError) e;
                            if (anError.getErrorCode() != 0) {
                                callback.onError(anError.getErrorDetail());
                            } else {
                                callback.onError(anError.getErrorDetail());
                            }
                        } else {
                            callback.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull T response) {
                        sendResponse(callback, response);
                    }
                });
    }

    public void getAPIList(String url, Map<String, String> paramMap, Class classObject, final InterActorCallback<?> callback) {
        Rx2AndroidNetworking.get(url)
                .addPathParameter("pageNumber", "0")
                .addQueryParameter(paramMap)
                .build()
                .getObjectListObservable(classObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<?>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof ANError) {
                            ANError anError = (ANError) e;
                            if (anError.getErrorCode() != 0) {
                                callback.onError(anError.getErrorDetail());
                            } else {
                                callback.onError(anError.getErrorDetail());
                            }
                        } else {
                            callback.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        callback.onFinish();
                    }

                    @Override
                    public void onNext(List<?> user) {
                        callback.onResponse(user);
                    }
                });
    }

    public <T extends Response> void postAPISingle(String url, Map<String, String> params, final Class<T> classObject, final InterActorCallback<?> callback) {
        Rx2AndroidNetworking.post(url)
                .addBodyParameter(params)
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
                        callback.onFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFinish();
                        if (e instanceof ANError) {
                            ANError anError = (ANError) e;
                            if (anError.getErrorCode() != 0) {
                                callback.onError(anError.getErrorDetail());
                            } else {
                                callback.onError(anError.getErrorDetail());
                            }
                        } else {
                            callback.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull T response) {
                        sendResponse(callback, response);
                    }
                });
    }

    public void postAPIList(String url, Map<String, String> paramMap, Class classObject, final InterActorCallback<?> callback) {
        Rx2AndroidNetworking.post(url)
                .addBodyParameter(paramMap)
                .build()
                .getObjectListObservable(classObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<?>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof ANError) {
                            ANError anError = (ANError) e;
                            if (anError.getErrorCode() != 0) {
                                callback.onError(anError.getErrorDetail());
                            } else {
                                callback.onError(anError.getErrorDetail());
                            }
                        } else {
                            callback.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        callback.onFinish();
                    }

                    @Override
                    public void onNext(List<?> user) {
                        callback.onResponse(user);
                    }
                });
    }
    /* FINISH GENERIC Rx2AndroidNetworking USE FOR API CALLING */
}

