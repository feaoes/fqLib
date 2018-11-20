package com.swmofang.fqlib.server;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.koushikdutta.async.Util;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;

import java.io.File;
import java.util.Hashtable;

public class NIOHttpServer implements HttpServerRequestCallback {

    private static final String TAG = "NIOHttpServer";

    private static NIOHttpServer mInstance;

    public static int PORT_LISTEN_DEFALT = 6001;

    AsyncHttpServer server = new AsyncHttpServer();

    public static NIOHttpServer getInstance() {
        if (mInstance == null) {
            // 增加类锁,保证只初始化一次
            synchronized (NIOHttpServer.class) {
                if (mInstance == null) {
                    mInstance = new NIOHttpServer();
                }
            }
        }
        return mInstance;
    }

    public void stopServer() {
        if(server!=null){
            server.stop();
        }
    }

    //仿照nanohttpd的写法
    public static enum Status {
        REQUEST_OK(200, "请求成功"),
        REQUEST_ERROR(500, "请求失败"),
        REQUEST_ERROR_API(501, "无效的请求接口"),
        REQUEST_ERROR_CMD(502, "无效命令"),
        REQUEST_ERROR_DEVICEID(503, "不匹配的设备ID"),
        REQUEST_ERROR_ENV(504, "不匹配的服务环境");

        private final int requestStatus;
        private final String description;

        Status(int requestStatus, String description) {
            this.requestStatus = requestStatus;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public int getRequestStatus() {
            return requestStatus;
        }
    }

    /**
 * 开启本地服务
 */
    public void startServer() {
//如果有其他的请求方式，例如下面一行代码的写法
        server.addAction("OPTIONS", "[\\d\\D]*", this);
        server.get("[\\d\\D]*", this);
        server.post("[\\d\\D]*", this);
        server.listen(PORT_LISTEN_DEFALT);
    }

    @Override
    public void onRequest(AsyncHttpServerRequest request, final AsyncHttpServerResponse response) {
        Log.d(TAG,"进入了");
        String uri = request.getPath();
            // 针对的是静态资源的处理
            String filePath = getFilePath(uri); // 根据url获取文件路径

            if (filePath == null) {
                Log.d(TAG, "sd卡没有找到");
                response.send("sd卡没有找到");
                return;
            }
            File file = new File(filePath);

            if (file != null && file.exists()) {
                Log.d(TAG, "file path = " + file.getAbsolutePath());
                String type = myGetContentType(file.getAbsolutePath());
                response.setContentType(type);
                Log.d(TAG, "contentType:"+type );
                response.sendFile(file);//和nanohttpd不一样的地方
//                BufferedInputStream inputStream = null;
//                try {
//                    inputStream = new BufferedInputStream(new FileInputStream(file));
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                response.sendStream(inputStream,file.length());//和nanohttpd不一样的地方
                Util.pump(file, null, new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        response.end();
                    }
                });
            } else {
                Log.d(TAG, "file path = " + file.getAbsolutePath() + "的资源不存在");
            }
        }

    static Hashtable<String, String> mContentTypes = new Hashtable<String, String>();
    {
        mContentTypes.put("js", "application/javascript");
        mContentTypes.put("json", "application/json");
        mContentTypes.put("png", "image/png");
        mContentTypes.put("jpg", "image/jpeg");
        mContentTypes.put("html", "text/html");
        mContentTypes.put("css", "text/css");
        mContentTypes.put("mp4", "video/mp4");
        mContentTypes.put("mov", "video/quicktime");
        mContentTypes.put("wmv", "video/x-ms-wmv");
        mContentTypes.put("mp3", "audio/mpeg");
        mContentTypes.put("wav", "audio/x-wav");
    }

    public String myGetContentType(String path){
        int index = path.lastIndexOf(".");
        if (index != -1) {
            String e = path.substring(index + 1);
            String ct = mContentTypes.get(e);
            if (ct != null)
                return ct;
        }
        return "text/plain";
    }



    private String getFilePath(String uri) {
        String decode = Uri.decode(uri);
        String replace = new File(Environment.getExternalStorageDirectory(),decode).getAbsolutePath();
//        String replace = uri.replace("http;//localhost:5000", Environment.getExternalStorageDirectory().getPath());
        return replace;
    }
}