package com.lhp.eventbussample.test;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.lhp.eventbussample.message.UserInfo;

import org.greenrobot.eventbus.EventBus;

public class TestUtils {
    public static final String TAG = "EventBus";
    private static TestUtils mInstance;
    private static Context mContext;
    public EventBus mEventBus = new EventBus();
    private TestUtils(){}
    public static TestUtils getInstance(Context context){
        mContext = context;
        if(mInstance ==null){
            mInstance = new TestUtils();
        }
        return mInstance;
    }
    public void setUserInfoInMainThread(){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"Post event in Main thread .");
                EventBus.getDefault().post(new UserInfo("EventBus_main","exmaple_main"));
                getUserInfoInTestUtils();
            }
        },5000);
    }
    public void setUserInfoInSubThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"Post event in Sub thread .");
                EventBus.getDefault().post(new UserInfo("EventBus_Sub","exmaple_sub"));
            }
        }).start();
    }

    public void getUserInfoInTestUtils(){
        EventListener eventListener = new EventListener();
        mEventBus.register(eventListener);
        mEventBus.post(new UserInfo("EventBus_self","exmaple_main"));
        Log.d(TAG,"recevice event in getUserInfoInTestUtils UserInfo : " + eventListener.getUserInfo());
    }

}
