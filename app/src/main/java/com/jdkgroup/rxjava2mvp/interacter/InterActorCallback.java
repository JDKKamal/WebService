package com.jdkgroup.rxjava2mvp.interacter;

import com.jdkgroup.rxjava2mvp.models.Response;

import java.util.List;

public interface InterActorCallback<T extends Response> {

  void onStart();

  void onResponse(T response);

  void onResponse(List<?> response);

  void onFinish();

  void onError(String message);
}
