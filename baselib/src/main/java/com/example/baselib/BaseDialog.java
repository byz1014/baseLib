package com.example.baselib;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

public abstract class BaseDialog extends Dialog implements View.OnClickListener {

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onLayout());
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        ViewUtils.getViewUtils().IndexFindViewById(this.getClass(),getWindow().getDecorView(),this);
    }

    protected abstract  int onLayout();

    protected DialogCallBackListener mDialogCallBackListener;

    public void setDialogCallBackListener(DialogCallBackListener mDialogCallBackListener) {
        this.mDialogCallBackListener = mDialogCallBackListener;
    }

}
