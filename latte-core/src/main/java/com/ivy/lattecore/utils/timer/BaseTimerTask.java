package com.ivy.lattecore.utils.timer;

import java.util.TimerTask;

/**
 * Created by Ivy on 2018/6/11.
 */

public class BaseTimerTask extends TimerTask {
    ITimerListener mITimerListener;

    public BaseTimerTask(ITimerListener ITimerListener) {
        mITimerListener = ITimerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
