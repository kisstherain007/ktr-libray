package com.ktr.net;

public interface NetWorkResultListener<T> {

	void onResultSuccess(int id, T t);

    void onResultFailed(int id, Exception ex);
}
