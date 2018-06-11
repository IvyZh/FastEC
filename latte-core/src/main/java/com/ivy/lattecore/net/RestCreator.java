package com.ivy.lattecore.net;

import com.ivy.lattecore.app.ConfigType;
import com.ivy.lattecore.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by Ivy on 2018/6/11.
 */

public class RestCreator {

    private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();

    public static ServiceApi getServiceApi() {
        return ServiceApiHolder.SERVICE_API;

    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OkHttpClientHolder.OKHTTPCLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OkHttpClientHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OKHTTPCLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class ServiceApiHolder {
        private static final ServiceApi SERVICE_API = RetrofitHolder.RETROFIT.create(ServiceApi.class);
    }

    public static WeakHashMap<String, Object> getParams(){
        return PARAMS;
    }
}
