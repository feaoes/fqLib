package com.swmofang.fqlib.utils.dialog;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.swmofang.fqlib.ui.base.BaseFragmentActivity;

import java.lang.ref.WeakReference;

/**
 * Author :feiqing
 * Created on 2018/5/26
 * Motify on 2018/5/26
 * Version : 1.0
 * Description :定时隐藏Dialog的Handler
 */

public class NewDialogDismissHandler extends Handler {

    private WeakReference<BaseFragmentActivity> wf;

    public NewDialogDismissHandler(Looper looper){
        super(looper);
    }

    public void setActivity(BaseFragmentActivity activity){
        wf = new WeakReference<>(activity);
    }
    @Override
    public void handleMessage(Message msg) {
        if(wf.get()!=null){
            wf.get().dismissLoadingDialog();
        }
    }
}