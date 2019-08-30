package com.lhp.eventbussample;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.lhp.eventbussample.message.UserInfo;

import org.greenrobot.eventbus.EventBus;

public class TestUtils {
    public static final String TAG = "EventBus";
    private static TestUtils mInstance;
    private static Context mContext;
    private TestUtils(){}
    public static TestUtils getInstance(Context context){
        mContext = context;
        if(mInstance ==null){
            mInstance = new TestUtils();
        }
        return mInstance;
    }
    public void setNameAndPasswordInMainThread(){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                setNameAndPasswordInSubThread();
                EventBus.getDefault().post(new UserInfo("EventBus_main","exmaple_main"));
            }
        },5000);
    }
    public void setNameAndPasswordInSubThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new UserInfo("EventBus_posting","exmaple_posting"));
            }
        }).start();
    }

}
