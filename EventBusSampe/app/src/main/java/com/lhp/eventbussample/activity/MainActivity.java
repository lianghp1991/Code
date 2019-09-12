package com.lhp.eventbussample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lhp.eventbussample.message.UserInfo;
import com.lhp.eventbussample.R;
import com.lhp.eventbussample.test.TestUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestUtils.getInstance(this).setUserInfoInMainThread();
    }

    @Override
    protected void onStart() {
        EventBus.getDefault().register(this);
//        TestUtils.getInstance(this).setUserInfoInSubThread();
        super.onStart();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UserInfo mUserInfo){
        Log.d(TestUtils.TAG," Receive message in MAIN ThreadMode. name = "+ mUserInfo.name +" password = "+ mUserInfo.password);
        ((TextView)findViewById(R.id.tv_name)).setText(mUserInfo.name);
        ((TextView)findViewById(R.id.tv_password)).setText(mUserInfo.password);
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEventPosting(UserInfo mUserInfo){
        Log.d(TestUtils.TAG," Receive message in POSTING ThreadMode. name = "+ mUserInfo.name +" password = "+ mUserInfo.password);
    }
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onMessageEventAsync(UserInfo mUserInfo){
        Log.d(TestUtils.TAG," Receive message in ASYNC ThreadMode. name = "+ mUserInfo.name +" password = "+ mUserInfo.password);
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEventBackgroud(UserInfo mUserInfo){
        Log.d(TestUtils.TAG," Receive message in BACKGROUD ThreadMode. name = "+ mUserInfo.name +" password = "+ mUserInfo.password);
    }
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onMessageEventMainOrdered(UserInfo mUserInfo){
        Log.d(TestUtils.TAG," Receive message in MAINORDERED ThreadMode. name = "+ mUserInfo.name +" password = "+ mUserInfo.password);
    }
}
