package com.ivy.lattecore.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * Created by Ivy on 2018/6/8.
 */

public class Latte {
    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

//    public static HashMap<String, Object> getConfigurations() {
//        return Configurator.getInstance().getLatteConfigs();
//    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplication() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT.name());
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER.name());
    }
}
