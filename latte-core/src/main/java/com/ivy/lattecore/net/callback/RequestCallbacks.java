package com.ivy.lattecore.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ivy on 2018/6/11.
 */

public class RequestCallbacks implements Callback<String> {
    ISuccess mSuccess;
    IFailure mFailure;
    IErrror mErrror;

    public RequestCallbacks(ISuccess success, IFailure failure, IErrror errror) {
        mSuccess = success;
        mFailure = failure;
        mErrror = errror;
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
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (mFailure != null) {
            mFailure.onFailure();
        }
    }
}
