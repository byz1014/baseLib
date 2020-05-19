package com.project.MyApplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import com.example.baselib.BaseDialog;
import com.example.baselib.FindviewbyId;

public class TestDialog extends BaseDialog {
    @FindviewbyId(value = R.id.tv_true,click = true)
    TextView tv_true;
    @FindviewbyId(value = R.id.tv_cancle,click = true)
    TextView tv_cancle;
    public TestDialog(@NonNull Context context) {
        super(context, R.style.customDialog);
    }
    @Override
    protected int onLayout() {
        return R.layout.dialog_message;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_true:
                if(mDialogCallBackListener!=null){
                    mDialogCallBackListener.onSuccess(null);
                }
                dismiss();
                break;
            case R.id.tv_cancle:
                dismiss();
                break;
        }
    }
}
