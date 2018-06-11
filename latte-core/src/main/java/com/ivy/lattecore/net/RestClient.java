package com.ivy.lattecore.net;

import android.content.Context;

import com.ivy.lattecore.net.callback.IError;
import com.ivy.lattecore.net.callback.IFailure;
import com.ivy.lattecore.net.callback.ISuccess;
import com.ivy.lattecore.net.callback.RequestCallbacks;
import com.ivy.lattecore.net.download.DownloadHandler;
import com.ivy.lattecore.ui.loader.LatteLoader;
import com.ivy.lattecore.ui.loader.LoaderStyle;
import com.ivy.lattecore.utils.L;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
    private IError mErrror;
    private RequestBody mBody;
    private LoaderStyle mLoaderStyle;
    private Context mContext;
    private File mFile;//文件上传
    private final String DOWNLOAD_DIR;
    private final String DOWNLOAD_EXTENSION;
    private final String DOWNLOAD_NAME;

    public RestClient(Context context, String url, WeakHashMap<String, Object> params, ISuccess success, IFailure failure, IError errror, RequestBody body, LoaderStyle loaderStyle, File file, String dir, String extension, String name) {
        mUrl = url;
        mParams = params;
        mSuccess = success;
        mFailure = failure;
        mErrror = errror;
        mBody = body;
        mLoaderStyle = loaderStyle;
        mContext = context;
        mFile = file;
        DOWNLOAD_DIR = dir;
        DOWNLOAD_EXTENSION = extension;
        DOWNLOAD_NAME = name;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }


    public void get() {
        request(HttpMethod.GET);
    }

    public void post() {
        if (mBody != null) {
            request(HttpMethod.POST);
        } else {
            if (!mParams.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if (mBody == null) {
            request(HttpMethod.PUT);
        } else {
            if (!mParams.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownloadHandler(mUrl, DOWNLOAD_DIR, DOWNLOAD_EXTENSION, DOWNLOAD_NAME,
                mSuccess, mFailure, mErrror)
                .handleDownload();
    }

    private void request(HttpMethod method) {
        ServiceApi serviceApi = RestCreator.getServiceApi();

        Call<String> call = null;
        if (mLoaderStyle != null) {
            LatteLoader.show(mContext, mLoaderStyle.name());
        }

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
                RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), mFile);
                MultipartBody.Part body = MultipartBody.Part.createFormData("file", mFile.getName(), requestBody);
                call = serviceApi.upload(mUrl, body);
                break;
        }

        L.v("request url:" + mUrl);
        if (call != null)
            call.enqueue(new RequestCallbacks(mSuccess, mFailure, mErrror, mLoaderStyle));
    }

}
