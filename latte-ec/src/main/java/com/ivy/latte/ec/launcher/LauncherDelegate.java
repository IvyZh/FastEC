package com.ivy.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ivy.latte.ec.R;
import com.ivy.latte.ec.R2;
import com.ivy.lattecore.delegates.LatteDelegate;
import com.ivy.lattecore.utils.L;
import com.ivy.lattecore.utils.LattePreference;
import com.ivy.lattecore.utils.timer.BaseTimerTask;
import com.ivy.lattecore.utils.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ivy on 2018/6/11.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    TextView mTvTime;
    private Timer mTimer;
    private int mCount = 5;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        L.v("tvTime:" + mTvTime);
//        tvTime.setText("1ssss");
        initTimer();
    }

    private void initTimer() {
        mTimer = new Timer();
        BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTime != null) {
                    mTvTime.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    private void checkIsShowScroll() {
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            L.v("没有进入过LauncherScrollDelegate");
            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            L.v("进入过LauncherScrollDelegate");

        }
    }
}
