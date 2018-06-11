package com.ivy.fastec.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ivy.fastec.R;
import com.ivy.lattecore.app.Latte;
import com.ivy.lattecore.delegates.LatteDelegate;
import com.ivy.lattecore.net.RestClient;
import com.ivy.lattecore.net.callback.IErrror;
import com.ivy.lattecore.net.callback.IFailure;
import com.ivy.lattecore.net.callback.ISuccess;
import com.ivy.lattecore.ui.LatteLoader;
import com.ivy.lattecore.ui.LoaderStyle;
import com.ivy.lattecore.utils.L;

import butterknife.BindView;

/**
 * Created by Ivy on 2018/6/8.
 */

public class ExampleDelegate extends LatteDelegate {

    @BindView(R.id.tv_result)
    TextView mTextView;

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        testRestClient();

        LatteLoader.show(getActivity(), LoaderStyle.LineSpinFadeLoaderIndicator.name());


    }

    private void testRestClient() {
        L.v("testRestClient...");

        RestClient.builder().url("https://blog.csdn.net/norwaya007/article/details/52165078")
                .params("k", "v")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        L.v("onSuccess:" + response);
                        Toast.makeText(Latte.getApplication(), response, Toast.LENGTH_LONG).show();

//                        mTextView.setText(response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        L.v("onFailure:");
                    }
                })
                .error(new IErrror() {
                    @Override
                    public void onError(int code, String msg) {
                        L.v("onError:" + code + ",msg:" + msg);
                    }
                })
                .build()
                .get();


    }
}
