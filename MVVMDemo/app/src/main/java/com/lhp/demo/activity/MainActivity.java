package com.lhp.demo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.lhp.demo.R;
import com.lhp.demo.utils.InitMap;

public class MainActivity extends AppCompatActivity {
    MapView mMapView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView = findViewById(R.id.map_view);

        mMapView.onCreate(savedInstanceState);
        if(mMapView != null) {
            InitMap.getInstance().setAMap(mMapView.getMap());

            InitMap.getInstance().init();
        }
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        return Navigation.findNavController(this,R.id.my_host_fragment).navigateUp();
//    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
}
