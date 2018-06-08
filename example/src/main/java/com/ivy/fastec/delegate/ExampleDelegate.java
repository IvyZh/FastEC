package com.ivy.fastec.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ivy.fastec.R;
import com.ivy.lattecore.delegates.LatteDelegate;

/**
 * Created by Ivy on 2018/6/8.
 */

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
