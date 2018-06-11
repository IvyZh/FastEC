package com.ivy.lattecore.net;

import android.content.Context;

import com.ivy.lattecore.net.callback.IError;
import com.ivy.lattecore.net.callback.IFailure;
import com.ivy.lattecore.net.callback.ISuccess;
import com.ivy.lattecore.ui.LoaderStyle;

import java.io.File;
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
    private IError mErrror;
    private RequestBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;
    private File mFile;
    private String DOWNLOAD_DIR;
    private String DOWNLOAD_EXTENSION;
    private String DOWNLOAD_NAME;

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

    public final RestClientBuilder error(IError errror) {
        mErrror = errror;
        return this;
    }

    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        mContext = context;
        mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder downLoadDir(String dir) {
        this.DOWNLOAD_DIR = dir;
        return this;
    }

    public final RestClientBuilder downLoadExtension(String extension) {
        this.DOWNLOAD_EXTENSION = extension;
        return this;
    }

    public final RestClientBuilder downLoadName(String name) {
        this.DOWNLOAD_NAME = name;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mContext, mUrl, mParams, mSuccess, mFailure, mErrror, mBody, mLoaderStyle, mFile, DOWNLOAD_DIR, DOWNLOAD_EXTENSION, DOWNLOAD_NAME);
    }
}
