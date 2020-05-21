package com.project.MyApplication.test;

import android.util.Log;
import android.view.View;

import com.example.baselib.BaseActivity;
import com.example.baselib.FindviewbyId;
import com.example.baselib.HttpSerializable;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.project.MyApplication.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class FlycoTabLayoutActivity extends BaseActivity {

    @FindviewbyId(value = R.id.ctl_body,click = false)
    CommonTabLayout ctl_body;
    @FindviewbyId(value = R.id.stl_body,click = false)
    SegmentTabLayout stl_body;
    @FindviewbyId(value = R.id.stl_body_Sliding,click = false)
    SlidingTabLayout stl_body_Sliding;

    ArrayList<CustomTabEntity> customTabEntityArrayList;
    String tags[]={"个","十","百","千","万"};

    @Override
    protected int onLayout() {
        return R.layout.activity_flyco_tab_layout;
    }

    @Override
    protected void init() {
        customTabEntityArrayList = new ArrayList<>();
        customTabEntityArrayList.add(new TagMain("一",0,0));
        customTabEntityArrayList.add(new TagMain("二",R.mipmap.ic_launcher,R.mipmap.ic_launcher));
        customTabEntityArrayList.add(new TagMain("三",R.mipmap.ic_launcher,R.mipmap.ic_launcher));
        customTabEntityArrayList.add(new TagMain("四",R.mipmap.ic_launcher,R.mipmap.ic_launcher));
        customTabEntityArrayList.add(new TagMain("五",R.mipmap.ic_launcher,R.mipmap.ic_launcher));
        ctl_body.setTabData(customTabEntityArrayList);
        ctl_body.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                Log.e("byz_show_log","onTabSelect       1    "+position);
            }

            @Override
            public void onTabReselect(int position) {
                Log.e("byz_show_log","onTabReselect     2      "+position);
            }
        });


        stl_body.setTabData(tags);


        stl_body_Sliding.setTabWidth(0.5f);
        stl_body_Sliding.setTextsize(20);
    }

    @Override
    protected void onPermissionResult(int i) {

    }

    @Override
    @Subscribe(threadMode  = ThreadMode.MAIN,priority = 1000)
    public void onHttpCallBack(HttpSerializable httpSerializable) {

    }

    @Override
    public void onClick(View view) {

    }

}
