package com.ivy.lattecore.ui;

import android.content.Context;
import android.text.TextUtils;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by Ivy on 2018/6/11.
 */

public class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {
        AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null) {
            Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String type) {
        if (TextUtils.isEmpty(type))
            return null;
        StringBuilder sb = new StringBuilder();
        if (!type.contains(".")) {
            String className = AVLoadingIndicatorView.class.getPackage().getName();
            sb.append(className)
                    .append(".indicators")
                    .append(".");
        }
        sb.append(type);
        Class<?> aClass = null;
        try {
            aClass = Class.forName(sb.toString());
            return (Indicator) aClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
