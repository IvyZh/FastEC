package com.ivy.lattecore.net.interceptors;

import com.ivy.lattecore.utils.FileUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Ivy on 2018/6/11.
 */

public class DebugInterceptor extends BaseInterceptor {
    String DEBUG_URL;
    int DEBUG_RAW;

    public DebugInterceptor(String debug_url, int debug_raw) {
        this.DEBUG_URL = debug_url;
        this.DEBUG_RAW = debug_raw;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String url = chain.request().url().toString();
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_RAW);
        }

        return chain.proceed(chain.request());
    }

    private Response debugResponse(Chain chain, int debug_raw) {
        String json = FileUtil.getRawFile(debug_raw);
        return getReponse(chain, json);
    }

    private Response getReponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }
}
