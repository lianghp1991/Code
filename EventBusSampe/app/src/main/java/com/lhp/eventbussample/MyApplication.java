package com.lhp.eventbussample;

import android.app.Application;

public class MyApplication extends Application {
    public MyApplication mContext;
    @Override
    public void onCreate() {
        mContext = this;
        super.onCreate();
    }
}
