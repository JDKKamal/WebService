package com.jdkgroup.retrofitmvp3.interacter;

import com.jdkgroup.retrofitmvp3.models.Response;

import rx.functions.Action0;

class InterActorOnSubscribe<T extends Response> implements Action0 {

  private InterActorCallback<T> mInterActorCallback;

  InterActorOnSubscribe(InterActorCallback<T> mInterActorCallback) {
    this.mInterActorCallback = mInterActorCallback;
  }

  @Override
  public void call() {
    mInterActorCallback.onStart();
  }
}
