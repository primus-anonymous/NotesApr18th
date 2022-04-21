package com.neocaptainnemo.notesapr18th.domain;

public interface Callback<T> {

    void onSuccess(T data);

    void onError(Throwable exception);
}
