package com.lhp.eventbussample.test;

import com.lhp.eventbussample.message.UserInfo;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventListener {

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public UserInfo mUserInfo;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void UserInfoListener(UserInfo mUserInfo){
        this.mUserInfo = mUserInfo;
    }

}
