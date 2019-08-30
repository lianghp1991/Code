package com.lhp.eventbussample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lhp.eventbussample.message.UserInfo;
import com.lhp.eventbussample.R;
import com.lhp.eventbussample.TestUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestUtils.getInstance(this).setNameAndPasswordInMainThread();
    }

    @Override
    protected void onStart() {
        EventBus.getDefault().register(this);
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
    public void onMessageEventPost(UserInfo mUserInfo){
        Log.d(TestUtils.TAG," Receive message in POSTING ThreadMode. name = "+ mUserInfo.name +" password = "+ mUserInfo.password);
    }
}
