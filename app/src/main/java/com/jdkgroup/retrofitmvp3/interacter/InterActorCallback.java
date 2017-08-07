package com.jdkgroup.retrofitmvp3.interacter;

import com.jdkgroup.retrofitmvp3.models.Response;

public interface InterActorCallback<T extends Response> {

  public void onStart();

  public void onResponse(T response);

  public void onFinish();

  public void onError(String message);

}
