package com.jdkgroup.rxjava2mvp.baseclasses;

import com.jdkgroup.rxjava2mvp.interacter.AppInteractor;

public abstract class BasePresenter<V extends BaseView> {
    private V view;
    protected AppInteractor appInteractor;

    final void attachView(V view) {
        this.view = view;
    }

    final void detachView() {
        this.view = null;
    }

    public V getView() {
        return view;
    }

    public boolean hasInternet() {
        return view.hasInternet();
    }

    public boolean hasInternetWithoutMessage(){
        return view.hasInternetWithoutMessage();
    }
    public boolean isViewAttached() {
        return view != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
        }
    }

    protected final AppInteractor getAppInteractor() {
        if (appInteractor == null) {
            appInteractor = new AppInteractor();
        }
        return appInteractor;
    }


}
