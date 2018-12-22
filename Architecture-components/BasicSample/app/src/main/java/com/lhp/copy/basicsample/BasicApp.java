package com.lhp.copy.basicsample;

import android.app.Application;

import com.lhp.copy.basicsample.db.AppDatabase;

/**
 * @author ${USER_NAME}
 * @Date 2018/10/23
 **/
public class BasicApp extends Application {
    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppExecutors = new AppExecutors();
    }
    public AppDatabase getDatabase(){
        return AppDatabase.getsInstance(this,mAppExecutors);
    }
    public DataRepository getRepository(){
        return DataRepository.getsInstance(getDatabase());
    }
}
