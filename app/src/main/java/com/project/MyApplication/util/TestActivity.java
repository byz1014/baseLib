package com.project.MyApplication.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.baselib.BaseActivity;
import com.example.baselib.BaseHttpUtil;
import com.example.baselib.FindviewbyId;
import com.example.baselib.HttpSerializable;
import com.example.baselib.LogUtil;
import com.project.MyApplication.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestActivity extends BaseActivity {

    private MyListView listView;
    private List<WeakReference<View>> views;
    @FindviewbyId(value = R.id.tv_location, click = true)
    TextView tv_location;
    @FindviewbyId(value = R.id.tv_message,click = false)
    TextView tv_message;
    @Override
    protected int onLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void init() {
//        MyAdapter myAdapter = new MyAdapter(this);
//        views = myAdapter.getBaseView();
//        listView = (MyListView) findViewById(R.id.lv_Date);
//        listView.setAdapter(myAdapter);
//        listView.setOnLazyLoadListener(new MyListView.OnLazyLoadListener() {
//            @Override
//            public void shouldLoad(List<Integer> itemsPos) {
//
//            }
//        });
    }

    @Override
    protected void onPermissionResult(int i) {

    }

    @Override
    @Subscribe(threadMode  = ThreadMode.MAIN,priority = 1000)
    public void onHttpCallBack(HttpSerializable httpSerializable) {
        LogUtil.e("byz_json",httpSerializable.toString()+"" );
        tv_message.setText("asdasda");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_location:
                BaseHttpUtil.getBaseHttpUtil().setUrl("http://192.168.0.14:8088/centre/gridTravelTecord/saveGridTravelTecord");
                Map<String,String> map = new HashMap<>();
                map.put("lat", "44");
                map.put("lng", "100");
                BaseHttpUtil.getBaseHttpUtil().post(map,"saveGridTravelTecord");
                break;
        }
    }


    LocationManager locationManager;

    @SuppressLint("MissingPermission")
    private void getLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        Location location = null;
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1f, mLocationListener);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {//网络
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1f, mLocationListener);
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        } else {
        }//
        Log.e("byz_location", location.getLatitude() + "           " + location.getLongitude() + "        " + location.getProvider());
    }


    LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.e("byz_location_show","onLocationChanged     "+(location==null ? "location == null" : ("Latitude:"+location.getLatitude()+"       Longitude:"+location.getLongitude() ) ));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            Log.e("byz_location_show","onStatusChanged     "+s+"   "+i+"   "+(bundle==null?"bundle == null":bundle.toString()));
        }

        @Override
        public void onProviderEnabled(String s) {
            Log.e("byz_location_show","onProviderEnabled     "+s+"");
        }

        @Override
        public void onProviderDisabled(String s) {
            Log.e("byz_location_show","onProviderDisabled     "+s+"");
        }
    };
}
