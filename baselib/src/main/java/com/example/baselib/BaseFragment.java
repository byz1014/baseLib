package com.example.baselib;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * fragment暂时没有做过多的定义
 * 目前只定义注解获取id
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewUtils.getViewUtils().IndexFindViewById(this.getClass(),view,this);
        onInit();
    }

    protected abstract void onInit();

}
