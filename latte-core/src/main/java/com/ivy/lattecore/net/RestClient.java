package com.ivy.lattecore.net;

import com.ivy.lattecore.net.callback.IErrror;
import com.ivy.lattecore.net.callback.IFailure;
import com.ivy.lattecore.net.callback.ISuccess;
import com.ivy.lattecore.net.callback.RequestCallbacks;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by Ivy on 2018/6/11.
 */

public class RestClient {
    private String mUrl;
    private WeakHashMap<String, Object> mParams;
    private ISuccess mSuccess;
    private IFailure mFailure;
    private IErrror mErrror;
    private RequestBody mBody;

    public RestClient(String url, WeakHashMap<String, Object> params, ISuccess success, IFailure failure, IErrror errror, RequestBody body) {
        mUrl = url;
        mParams = params;
        mSuccess = success;
        mFailure = failure;
        mErrror = errror;
        mBody = body;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    public void get() {
        request(HttpMethod.GET);
    }

    private void request(HttpMethod method) {
        ServiceApi serviceApi = RestCreator.getServiceApi();

        Call<String> call = null;

        switch (method) {
            case GET:
                call = serviceApi.get(mUrl, mParams);
                break;
            case POST:
                call = serviceApi.post(mUrl, mParams);
                break;
            case POST_RAW:
                call = serviceApi.postRaw(mUrl, mBody);
                break;
            case PUT:
                call = serviceApi.put(mUrl, mParams);
                break;
            case DELETE:
                call = serviceApi.delete(mUrl, mParams);
                break;
            case UPLOAD:
                break;
        }

        if (call != null)
            call.enqueue(new RequestCallbacks(mSuccess, mFailure, mErrror));
    }

}
