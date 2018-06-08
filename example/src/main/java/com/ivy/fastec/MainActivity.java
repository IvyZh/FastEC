package com.ivy.fastec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ivy.fastec.delegate.ExampleDelegate;
import com.ivy.lattecore.activities.ProxyActivity;
import com.ivy.lattecore.app.Latte;
import com.ivy.lattecore.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {
    @Override
    public LatteDelegate setRootDelegate() {
        Log.d("tag", "setRootDelegate...");
        return new ExampleDelegate();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toast.makeText(Latte.getApplication(), "msg", Toast.LENGTH_LONG).show();
//    }
}
