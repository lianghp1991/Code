package com.lhp.demo.utils;

import android.location.Location;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.LocationSource;
import com.lhp.demo.MyApplication;

public class MyLocationSource implements LocationSource {
    OnLocationChangedListener mMyOnLocationChangedListener = new MyOnLocationChangedListener();
    AMapLocationClient mAMapLocationClient = null;
    AMapLocationClientOption mAMapLocationClientOption = null;
    @Override
    public void activate(OnLocationChangedListener mOnLocationChangedListener) {
        mMyOnLocationChangedListener = mOnLocationChangedListener;
        if(mAMapLocationClient == null){
            mAMapLocationClient = new AMapLocationClient(MyApplication.getContext());
            mAMapLocationClientOption = new AMapLocationClientOption();
            mAMapLocationClient.setLocationListener(new MyAMapLocationListener());
            mAMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            mAMapLocationClient.setLocationOption(mAMapLocationClientOption);
            mAMapLocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {

    }

    class MyOnLocationChangedListener implements OnLocationChangedListener{

        @Override
        public void onLocationChanged(Location mLocation) {

        }
    }
    class MyAMapLocationListener implements AMapLocationListener{

        @Override
        public void onLocationChanged(AMapLocation mAMapLocation) {
            if(mAMapLocation != null){

            }
        }
    }
}
