package com.swmofang.fqlib.server;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ServiceUtils;

public class HttpProxyService extends Service {

    public static final String ACTION_FOO = "com.swmofang.teacher.jingju.action.startHttpProxy";
    public HttpProxyService() {
        super();
    }

    public static void startHttpProxy(Context context) {
        Intent intent = new Intent(context, HttpProxyService.class);
        intent.setAction(ACTION_FOO);
        context.startService(intent);
    }

    public static void stopHttpProxy(Context context) {
        if(ServiceUtils.isServiceRunning(HttpProxyService.class.getName())){
            Intent name = new Intent(context, HttpProxyService.class);
            name.setAction(HttpProxyService.ACTION_FOO);
            context.stopService(name);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleActionFoo();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        NIOHttpServer.getInstance().stopServer();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void handleActionFoo() {
        NIOHttpServer.getInstance().startServer();
    }
}
