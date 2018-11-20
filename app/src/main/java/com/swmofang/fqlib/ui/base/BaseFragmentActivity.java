package com.swmofang.fqlib.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.swmofang.fqlib.R;
import com.swmofang.fqlib.utils.dialog.NewDialogDismissHandler;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author :feiqing
 * Created on 2018/5/26
 * Motify on 2018/5/26
 * Version : 1.0
 * Description :Fragmentation 上层再次封装一层的基类
 */

public class BaseFragmentActivity extends SupportActivity {

    private static final int DIALOG_DISMISS_FLAG = 1;
    private final String fullScreenForDialog_TAG = "FullScreenForDialog_TAG";
    private NewDialogDismissHandler dialogDismissHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialogDismissHandler = new NewDialogDismissHandler(getMainLooper());
        dialogDismissHandler.setActivity(this);
    }

    public synchronized void showLoadDialog(boolean delayAutoDismiss){
        if(dialogDismissHandler!=null){
            dialogDismissHandler.removeMessages(DIALOG_DISMISS_FLAG);//清空上一次的标记，避免倒计时影响永不消逝的dialog
        }
        if(delayAutoDismiss){
            dialogDismissHandler.sendEmptyMessageDelayed(DIALOG_DISMISS_FLAG,15000);
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Window window = getWindow();
                ViewGroup decorView = (ViewGroup) window.getDecorView();

                if (decorView.findViewWithTag(fullScreenForDialog_TAG) == null) {
                    View inflate = LayoutInflater.from(BaseFragmentActivity.this).inflate(R.layout.loading_dialog, null);
                    inflate.setTag(fullScreenForDialog_TAG);
                    decorView.addView(inflate);
                    decorView.requestFocus();
                    setSystemUiVisibility1(decorView);
                }
            }
        });
    }

    public void setSystemUiVisibility1(View view){
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY//能够让Activity在沉浸式全屏模式下,避免触发获取焦点问题，可以试一下取消这一行代码
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    public synchronized void showLoadDialog() {
        showLoadDialog(false);
    }

    public synchronized void dismissLoadingDialog() {
        if(dialogDismissHandler!=null)
            dialogDismissHandler.removeMessages(DIALOG_DISMISS_FLAG);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Window window = getWindow();
                ViewGroup decorView = (ViewGroup) window.getDecorView();
                View fullScreenForDialog = decorView.findViewWithTag(fullScreenForDialog_TAG);
                if (fullScreenForDialog != null) {
                    decorView.removeView(fullScreenForDialog);
                    decorView.requestFocus();
                    setSystemUiVisibility1(decorView);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        if(dialogDismissHandler!=null){
            dialogDismissHandler.removeMessages(DIALOG_DISMISS_FLAG);
            dialogDismissHandler = null;
        }
        super.onDestroy();
    }
}
