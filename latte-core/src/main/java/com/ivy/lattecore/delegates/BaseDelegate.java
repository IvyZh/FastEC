package com.ivy.lattecore.delegates;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivy.lattecore.activities.ProxyActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by Ivy on 2018/6/8.
 */

public abstract class BaseDelegate extends SwipeBackFragment {
    private Unbinder mUnbinder = null;
    protected FragmentActivity mActivity;
    private final SupportFragmentDelegate DELEGATE = new SupportFragmentDelegate(this);


    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DELEGATE.onAttach((Activity) context);
        mActivity = DELEGATE.getActivity();
    }

    public final ProxyActivity getProxyActivity() {
        return (ProxyActivity) mActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("setLayout() type must be int or view!");
        }

        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null)
            mUnbinder.unbind();
    }
}
