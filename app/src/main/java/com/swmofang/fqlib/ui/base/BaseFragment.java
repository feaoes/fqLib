package com.swmofang.fqlib.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.util.ObjectsCompat;
import android.view.View;

import com.swmofang.fqlib.MyApplication;
import com.swmofang.fqlib.R;
import com.swmofang.fqlib.utils.doubleclick.AntiShake;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Author :feiqing
 * Created on 2018/5/26
 * Motify on 2018/5/26
 * Version : 1.0
 * Description :
 */

public class BaseFragment extends SupportFragment {
    protected final String TAG = this.getClass().getSimpleName();
    protected AntiShake antiShake;//防抖动

    protected BaseFragmentActivity mActivity;//声明为本项目中重新定义的基类

    public BaseFragment(){
        antiShake = new AntiShake();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (BaseFragmentActivity) activity;
    }

    @Override
    public void onDetach() {
        this.mActivity = null;
        super.onDetach();
    }

    protected void readyGo(Class<? extends Activity> clazz){
        readyGo(clazz,null);
    }
    protected void readyGo(Class<? extends Activity> clazz, Bundle bundle){
        Intent intent = new Intent(mActivity, clazz);
        startActivity(intent,bundle);
    }

    public void showDialog() {
        mActivity.showLoadDialog(true);//最长显示15s，然后自动隐藏
    }

    public void hideDialog() {
        mActivity.dismissLoadingDialog();
    }

    private void setFullScreenStyle() {
        try {
            View rootView = mActivity.getWindow().getDecorView().getRootView();
            mActivity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
            setSystemUiVisibility1(rootView);
        } catch (Exception e) {

        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            setFullScreenStyle();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setFullScreenStyle();
    }

    //设置全屏模式
    public void setSystemUiVisibility1(View view){
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY//能够让Activity在沉浸式全屏模式下,避免触发获取焦点问题，可以试一下取消这一行代码
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

}
