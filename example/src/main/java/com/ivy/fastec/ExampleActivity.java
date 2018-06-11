package com.ivy.fastec;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ivy.fastec.delegate.ExampleDelegate;
import com.ivy.latte.ec.launcher.LauncherDelegate;
import com.ivy.lattecore.activities.ProxyActivity;
import com.ivy.lattecore.app.Latte;
import com.ivy.lattecore.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
    }


    @Override
    public LatteDelegate setRootDelegate() {
        Log.d("tag", "setRootDelegate...");
        return new LauncherDelegate();
    }


}
