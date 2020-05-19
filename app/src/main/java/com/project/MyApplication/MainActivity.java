package com.project.MyApplication;

import android.Manifest;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baselib.BaseActivity;
import com.example.baselib.DialogCallBackListener;
import com.example.baselib.FindviewbyId;
import com.just.agentweb.AgentWeb;

public class MainActivity extends BaseActivity {

    @FindviewbyId(value = R.id.tv_message, click = true)
    TextView tv_message;
    @FindviewbyId(value = R.id.ll_test_body, click = false)
    LinearLayout ll_test_body;

    TestDialog testDialog;
    String mPermission[] = {Manifest.permission.CAMERA};

    @Override
    protected int onLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        checkPermission(mPermission, 101);

    }
    AgentWeb mAgentWeb;
    @Override
    protected void onPermissionResult(int code) {
        if (code == 101) {
            testDialog = new TestDialog(this);
            testDialog.setDialogCallBackListener(new DialogCallBackListener() {
                @Override
                public void onSuccess(Object obj) {
                    Toast.makeText(MainActivity.this, "" + obj, Toast.LENGTH_SHORT).show();
                }
            });

            mAgentWeb = AgentWeb.with(this)
                    .setAgentWebParent(ll_test_body, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))
                    .useDefaultIndicator()
                    .createAgentWeb()
                    .ready()
                    .go("https://www.baidu.com/");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_message:
                testDialog.show();
                break;
        }
    }
}


