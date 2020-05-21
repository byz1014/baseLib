package com.project.MyApplication.test;

import android.view.View;
import android.widget.LinearLayout;

import com.example.baselib.BaseActivity;
import com.example.baselib.FindviewbyId;
import com.example.baselib.HttpSerializable;
import com.just.agentweb.AgentWeb;
import com.project.MyApplication.R;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AgenWebActivity extends BaseActivity {

    @FindviewbyId(value = R.id.ll_body, click = false)
    LinearLayout ll_body;

    @Override
    protected int onLayout() {
        return R.layout.activity_agen_web;
    }
    AgentWeb mAgentWeb;
    @Override
    protected void init() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(ll_body, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go("http://www.jd.com");
    }

    @Override
    protected void onPermissionResult(int i) {

    }

    @Override
    @Subscribe(threadMode  = ThreadMode.MAIN,priority = 1000)
    public  void onHttpCallBack(HttpSerializable httpSerial) {

    }

    @Override
    public void onClick(View view) {

    }



}
