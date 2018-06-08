package com.ivy.fastec.app;

import android.app.Application;

import com.ivy.latte.ec.icon.FontEcModule;
import com.ivy.lattecore.app.Latte;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;

/**
 * Created by Ivy on 2018/6/8.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Latte.init(this)
                .withApiHost("http://www.baidu.com")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .configure();


    }
}
