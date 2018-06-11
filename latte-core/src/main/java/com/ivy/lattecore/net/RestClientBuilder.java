package com.ivy.lattecore.net;

import com.ivy.lattecore.net.callback.IErrror;
import com.ivy.lattecore.net.callback.IFailure;
import com.ivy.lattecore.net.callback.ISuccess;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Ivy on 2018/6/11.
 */

public final class RestClientBuilder {

    private String mUrl;
    private WeakHashMap<String, Object> mParams = RestCreator.getParams();
    private ISuccess mSuccess;
    private IFailure mFailure;
    private IErrror mErrror;
    private RequestBody mBody;

    public final RestClientBuilder url(String url) {
        mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
//        if (mParams == null)
//            mParams = new WeakHashMap<>();
        mParams.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, String value) {
        mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder failure(IFailure failure) {
        mFailure = failure;
        return this;
    }

    public final RestClientBuilder success(ISuccess success) {
        mSuccess = success;
        return this;
    }

    public final RestClientBuilder error(IErrror errror) {
        mErrror = errror;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, mParams, mSuccess, mFailure, mErrror, mBody);
    }
}
