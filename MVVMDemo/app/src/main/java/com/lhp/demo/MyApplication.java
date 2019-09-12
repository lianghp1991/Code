package com.lhp.demo;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    public static Context mContext = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
