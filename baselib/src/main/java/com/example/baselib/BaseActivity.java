package com.example.baselib;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onLayout());
        ViewUtils.getViewUtils().IndexFindViewById(this);

        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                init();
                return false;
            }
        });

    }

    protected abstract int onLayout();

    protected abstract void init();

    protected abstract void onPermissionResult(int code);

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (checkResultPermission(grantResults)) {
            onPermissionResult(requestCode);
        } else {
            if (permissions.length == grantResults.length) {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        Log.e("BaseActivity", permissions[i] + "没有申请成功");
                    }
                }
            } else {
                Log.e("BaseActivity", "不合理现象检查代码");
            }
        }
    }

    protected void checkPermission(String[] mPermissions, int mCode) {
        if (checkPermissions(mPermissions)) {
            onPermissionResult(mCode);
        } else {
            ActivityCompat.requestPermissions(this, mPermissions, mCode);
        }
    }


    private boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String permission : permissions) {
            if (PermissionChecker.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    ArrayList<String> mPermissionList = new ArrayList<>();

    private boolean checkResultPermission(int grantResults[]) {
        mPermissionList.clear();
        for (int granresult : grantResults) {
            if (granresult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    protected void onToast(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }


    protected void goActivity(Class<?> cla, Bundle bundle) {
        Intent intent = new Intent(this, cla);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
    }

    protected void goResultActivity(Class<?> cla, Bundle bundle, int code) {
        Intent intent = new Intent(this, cla);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivityForResult(intent, code);
    }

    protected Bundle getBundle() {
        return getIntent().getBundleExtra("bundle");
    }
}
