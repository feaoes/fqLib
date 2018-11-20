package com.swmofang.fqlib;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.os.IBinder;

import com.swmofang.fqlib.server.HttpProxyService;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * Author :feiqing
 * Created on 2018/7/27
 * Motify on 2018/7/27
 * Version : 1.0
 * Description :
 */

public class MyApplication extends Application {

    private final String TAG = this.getClass().getSimpleName();
    private static MyApplication instance;
    private Typeface xingqiuti;
    private Typeface shangwei;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        onInitCreate();

    }

    private void onInitCreate() {
        //初始化Fragmentation的Dialog
//        initFragmentationDialog();
        //初始化VideoPlay全局设置
        initVideoPlay();
    }


    private void initVideoPlay() {
    }

    public static MyApplication getInstance(){
        return instance;
    }

    private void initFragmentationDialog() {
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }

    public void startHttpProxyService() {
        HttpProxyService.startHttpProxy(this);
    }
    public void stopHttpProxyService() {
        HttpProxyService.stopHttpProxy(this);
    }

}
