package com.jdkgroup.retrofitmvp3.interacter;

import android.content.ActivityNotFoundException;

import com.jdkgroup.retrofitmvp3.models.Response;
import com.jdkgroup.webservice.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Subscriber;

import static com.jdkgroup.retrofitmvp3.baseclasses.BaseApplication.getBaseApplication;

/**
 * This class is used for Indicating InterActor process whether completed or in error otherwise you
 * need to override onNext method for handling response.
 */
abstract class InterActorSubscriber<T extends Response> extends Subscriber<T> {

    private final String TAG = getClass().getSimpleName();
    private InterActorCallback<T> mInterActorCallback;
    private AppInteractor appInteractor;

    InterActorSubscriber(InterActorCallback<T> mInterActorCallback, AppInteractor appInteractor) {
        this.mInterActorCallback = mInterActorCallback;
        this.appInteractor = appInteractor;
    }

    @Override
    public void onCompleted() {
        if (!appInteractor.isCancel) {
            mInterActorCallback.onFinish();
        }
    }

  /*  @Override
    public void onError(Throwable e) {
        if (!appInteractor.isCancel) {
            if (e instanceof SocketTimeoutException) {
                mInterActorCallback.onError("Connection time out, please try again");
            } else {
                mInterActorCallback.onError("Connection time out, please try again");
            }
            mInterActorCallback.onFinish();
        }
    }
*/

    @Override
    public void onError(Throwable e) {
        if (!appInteractor.isCancel) {
            if (e instanceof SocketTimeoutException) {
                mInterActorCallback.onError(getBaseApplication().getResources().getString(R.string.msg_connection_time_out));
            } else if (e instanceof ActivityNotFoundException) {
                mInterActorCallback.onError(getBaseApplication().getResources().getString(R.string.msg_activity_not_found));
            } else if (e instanceof UnknownHostException || e instanceof ConnectException) {
                mInterActorCallback.onError(getBaseApplication().getResources().getString(R.string.msg_server_not_responding));
            } else {
                mInterActorCallback.onError(getBaseApplication().getResources().getString(R.string.msg_server_not_responding));
                //mInterActorCallback.onError(getBaseApplication().getResources().getString(R.string.msg_something_went_wrong));
            }
            mInterActorCallback.onFinish();
        }
    }
}