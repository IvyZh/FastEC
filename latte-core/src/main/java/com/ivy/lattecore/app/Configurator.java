package com.ivy.lattecore.app;

import android.util.Log;

import com.ivy.lattecore.utils.L;
import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by Ivy on 2018/6/8.
 */

public class Configurator {
    public static final String TAG = "Configurator";

    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), false);
    }

//    public static Configurator getConfigurator() {
//        return Holder.INSTANCE;
//    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(), true);
    }


    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(), host);
        return this;
    }

    public final Configurator withInterceptors(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }


    private void checkConfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure()");
        }
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            Log.d(TAG, " ICONS.size:" + ICONS.size());

            for (int i = 0; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor iconFontDescriptor) {
        ICONS.add(iconFontDescriptor);
        return this;
    }

    @SuppressWarnings("unchecked")
//    final <T> T getConfiguration(Enum<ConfigKeys> key) {
//        checkConfiguration();
//        return (T) LATTE_CONFIGS.get(key.name());
//    }

    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        Object value = LATTE_CONFIGS.get(key);
        L.v("key:" + key + ",value:" + value);
        if (value == null)
            throw new NullPointerException(key.toString() + " IS NULL");
        return (T) LATTE_CONFIGS.get(key);
    }
}
