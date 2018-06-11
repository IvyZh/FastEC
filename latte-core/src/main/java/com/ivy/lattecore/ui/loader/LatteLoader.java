package com.ivy.lattecore.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.ivy.lattecore.R;
import com.ivy.lattecore.utils.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by Ivy on 2018/6/11.
 */

public class LatteLoader {
    private static final String DEFAULT_TYPE = LoaderStyle.BallClipRotatePulseIndicator.name();
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    public static void show(Context context, String type) {

        AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
//        AppCompatDialog dialog = new AppCompatDialog(context);
        AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int screenHeight = DimenUtil.getScreenHeight();
        int screenWidth = DimenUtil.getScreenWidth();
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = screenWidth / 8;
            lp.height = screenHeight / 8;
            lp.gravity = Gravity.CENTER;
        }


        LOADERS.add(dialog);
        dialog.show();
    }

    public static void show(Context context) {
        show(context, DEFAULT_TYPE);
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }
}
