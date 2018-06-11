package com.ivy.lattecore.net.callback;

import android.os.Handler;

import com.ivy.lattecore.net.RestCreator;
import com.ivy.lattecore.ui.LatteLoader;
import com.ivy.lattecore.ui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivy on 2018/6/11.
 */

public class RequestCallbacks implements Callback<String> {
    ISuccess mSuccess;
    IFailure mFailure;
    IError mErrror;
    LoaderStyle mLoaderStyle;
//    private static final Handler HANDLER = Latte.getHandler();

    private static final Handler HANDLER = new Handler();

    public RequestCallbacks(ISuccess success, IFailure failure, IError errror, LoaderStyle loaderStyle) {
        mSuccess = success;
        mFailure = failure;
        mErrror = errror;
        mLoaderStyle = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (mSuccess != null) {
                mSuccess.onSuccess(response.body());
            }
        } else {
            if (mErrror != null) {
                mErrror.onError(response.code(), response.message());
            }
        }
        onRequestFinish();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (mFailure != null) {
            mFailure.onFailure();
        }
        onRequestFinish();
    }

    private void onRequestFinish() {
        if (mLoaderStyle != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestCreator.getParams().clear();
                    LatteLoader.stopLoading();
                }
            }, 3000);
        }
    }
}
