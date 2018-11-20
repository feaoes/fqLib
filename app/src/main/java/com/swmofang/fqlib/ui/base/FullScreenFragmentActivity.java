package com.swmofang.fqlib.ui.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Author :feiqing
 * Created on 2018/5/26
 * Motify on 2018/5/26
 * Version : 1.0
 * Description :全屏BaseFragmentActivity
 */

public class FullScreenFragmentActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setFullScreenStyle();
    }

    private void setFullScreenStyle() {
        try {
            View rootView = getWindow().getDecorView().getRootView();
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            setSystemUiVisibility1(rootView);
        } catch (Exception e) {

        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setFullScreenStyle();
        } else {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setFullScreenStyle();
    }
}
