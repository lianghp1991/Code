package com.lhp.demo.utils;

import android.content.Intent;
import android.location.Location;

import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.MyLocationStyle;

public class InitMap {
    private AMap mAMap = null;
    private MyLocationStyle mMyLocationStyle = null;

    public static InitMap getInstance(){
        return InitMapHolder.INIT_MAP;
    };

    public void init(){
        if(null != mAMap){
//            mAMap.setLocationSource(new MyLocationSource());
            mMyLocationStyle = new MyLocationStyle();
            mMyLocationStyle.interval(2000);
            mMyLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
            mAMap.setMyLocationStyle(mMyLocationStyle);
            mAMap.getUiSettings().setMyLocationButtonEnabled(true);
            mAMap.setMyLocationEnabled(true);
            mAMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location mLocation) {

                }
            });

        }
    }
    public AMap getAMap() {
        return mAMap;
    }

    public void setAMap(AMap mAMap) {
        this.mAMap = mAMap;
    }

    public MyLocationStyle getMyLocationStyle() {
        return mMyLocationStyle;
    }

    public void setMyLocationStyle(MyLocationStyle mMyLocationStyle) {
        this.mMyLocationStyle = mMyLocationStyle;
    }

    private static class InitMapHolder{
        private static final InitMap INIT_MAP = new InitMap();
     }

}
