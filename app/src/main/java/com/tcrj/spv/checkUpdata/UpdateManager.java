package com.tcrj.spv.checkUpdata;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tcrj.spv.R;
import com.tcrj.spv.views.application.BaseApplication;
import com.tcrj.spv.views.dialog.SweetAlertDialog;
import com.tcrj.spv.views.utils.Api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;


/**
 * APK更新管理类
 *
 * @author Royal
 */
public class UpdateManager {

    // 上下文对象
    private Context mContext;
    //更新版本信息对象
    private VersionInfo info = null;
    // 下载进度条
    private ProgressBar progressBar;
    // 是否终止下载UpdateManager
    private boolean isInterceptDownload = false;
    //进度条显示数值
    private int progress = 0;

    private ProgressDialog dialog;

    private MyTask myTask;

    public AlertDialog alertDialog;

    private final String[][] MIME_MapTable = {
            //{后缀名，MIME类型} 
            {".3gp", "video/3gpp"},
            {".apk", "application/vnd.android.package-archive"},
            {".asf", "video/x-ms-asf"},
            {".avi", "video/x-msvideo"},
            {".bin", "application/octet-stream"},
            {".bmp", "image/bmp"},
            {".c", "text/plain"},
            {".class", "application/octet-stream"},
            {".conf", "text/plain"},
            {".cpp", "text/plain"},
            {".doc", "application/msword"},
            {".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
            {".xls", "application/vnd.ms-excel"},
            {".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
            {".exe", "application/octet-stream"},
            {".gif", "image/gif"},
            {".gtar", "application/x-gtar"},
            {".gz", "application/x-gzip"},
            {".h", "text/plain"},
            {".htm", "text/html"},
            {".html", "text/html"},
            {".jar", "application/java-archive"},
            {".java", "text/plain"},
            {".jpeg", "image/jpeg"},
            {".jpg", "image/jpeg"},
            {".js", "application/x-javascript"},
            {".log", "text/plain"},
            {".m3u", "audio/x-mpegurl"},
            {".m4a", "audio/mp4a-latm"},
            {".m4b", "audio/mp4a-latm"},
            {".m4p", "audio/mp4a-latm"},
            {".m4u", "video/vnd.mpegurl"},
            {".m4v", "video/x-m4v"},
            {".mov", "video/quicktime"},
            {".mp2", "audio/x-mpeg"},
            {".mp3", "audio/x-mpeg"},
            {".mp4", "video/mp4"},
            {".mpc", "application/vnd.mpohun.certificate"},
            {".mpe", "video/mpeg"},
            {".mpeg", "video/mpeg"},
            {".mpg", "video/mpeg"},
            {".mpg4", "video/mp4"},
            {".mpga", "audio/mpeg"},
            {".msg", "application/vnd.ms-outlook"},
            {".ogg", "audio/ogg"},
            {".pdf", "application/pdf"},
            {".png", "image/png"},
            {".pps", "application/vnd.ms-powerpoint"},
            {".ppt", "application/vnd.ms-powerpoint"},
            {".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
            {".prop", "text/plain"},
            {".rc", "text/plain"},
            {".rmvb", "audio/x-pn-realaudio"},
            {".rtf", "application/rtf"},
            {".sh", "text/plain"},
            {".tar", "application/x-tar"},
            {".tgz", "application/x-compressed"},
            {".txt", "text/plain"},
            {".wav", "audio/x-wav"},
            {".wma", "audio/x-ms-wma"},
            {".wmv", "audio/x-ms-wmv"},
            {".wps", "application/vnd.ms-works"},
            {".xml", "text/plain"},
            {".z", "application/x-compress"},
            {".zip", "application/x-zip-compressed"},
            {"", "*/*"}
    };

    /**
     * 参数为Context(上下文activity)的构造函数
     *
     * @param context
     */
    public UpdateManager(Context context) {
        this.mContext = context;
    }

    public void checkUpdate(String Version, int type) {

        if (Version.equals("")) {
            dialog = new ProgressDialog(mContext);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setTitle("系统提示");
            dialog.setMessage("正在检查更新...");
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();
            getVersionInfoFromServer();
        } else {
            try {
                // 获取当前软件包信息
                PackageInfo pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), PackageManager.GET_CONFIGURATIONS);
                // 当前软件版本号
                int versionCode = pi.versionCode;
                Log.e("TAG","本地版本号："+versionCode+"服务器版本号："+Version);

                if (versionCode >= Integer.parseInt(Version)) {
                    // 如果当前版本号于服务端版本号相同
                    if (type == 1) {
                        return;
                    } else {
                        Toast.makeText(mContext, "目前为最新版本", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    dialog = new ProgressDialog(mContext);
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setTitle("系统提示");
                    dialog.setMessage("正在检查更新...");
                    dialog.setIndeterminate(false);
                    dialog.setCancelable(false);
                    dialog.show();
                    getVersionInfoFromServer();
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
        }


    }

    // 异步操作类
    class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            Message msg1 = new Message();
            msg1.what = 1;
            msg1.getData().putString("name", "正在检查更新...");
            handler2.sendMessage(msg1);// 发送消息
            getVersionInfoFromServer();
            return null;
        }


    }

    private Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String nameString = msg.getData().getString("name");
                    dialog.setMessage(nameString);
                    break;
            }
        }
    };


    public Handler handler3 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(mContext, "请检查网络，当前没有网络连接", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(mContext, "服务器异常，获取数据失败", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    break;
                case 3:

                    showUpdateDialog();

                    if(dialog != null){
                        dialog.dismiss();
                    }
                    break;
                case 4:
                    Toast.makeText(mContext, "已是最新版本，不需要更新", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    break;
            }
        }
    };

    /**
     * 从服务端获取版本信息
     *
     * @return
     */
    public Api api;
    private void getVersionInfoFromServer() {

        OkHttpClient okHttpClient = new OkHttpClient();
        //Form表单格式的参数传递
        FormBody formBody = new FormBody
                .Builder()
                .add("","")//设置参数名称和参数值
                .build();
        Request request = new Request
                .Builder()
                .post(formBody)//Post请求的参数传递
                .url(BaseApplication.getConfig()+"GetVersionDetails.ashx?")
                .build();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("TAG","失败啦");
                handler3.sendEmptyMessage(2);
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {

                Gson gson = new Gson();
                VersionInfo stu = gson.fromJson(response.body().string(), VersionInfo.class);

                info = stu;
                // 获取当前软件包信息
                try {
                    PackageInfo pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), PackageManager.GET_CONFIGURATIONS);
                    // 当前软件版本号
                    int versionCode = pi.versionCode;
                    String Code = info.getEntity().getVersions();
                    if (versionCode >= Integer.parseInt(Code)) {
                        // 如果当前版本号于服务端版本号相同
//                        Toast.makeText(mContext, "已是最新版本，不需要更新", Toast.LENGTH_SHORT).show();
                        handler3.sendEmptyMessage(4);
                    } else {
                        // 如果当前版本号于服务端版本号不相同,则弹出提示更新对话框
                        handler3.sendEmptyMessage(3);

                    }

                } catch (NameNotFoundException e) {
                    e.printStackTrace();
                }

            }

        });

//
//
//////            showDownloadDialog();
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(BaseApplication.getConfig())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .build();
//            api = retrofit.create(Api.class);
//            api.getGetVersion("")
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Observer<VersionInfo>() {
//                        @Override
//                        public void onCompleted() {
//
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            handler3.sendEmptyMessage(2);
//                            Log.e("TAG","错误");
//                        }
//
//                        @Override
//                        public void onNext(VersionInfo versionInfo) {
//
//                            Log.e("TAG","版本信息："+versionInfo.toString());
//                            info = versionInfo;
//                            // 获取当前软件包信息
//                            try {
//                                PackageInfo pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), PackageManager.GET_CONFIGURATIONS);
//                                // 当前软件版本号
//                                int versionCode = pi.versionCode;
//                                String Code = info.getVersionCode();
//                                Log.e("TAG","当前版本号："+versionCode+"服务器版本号："+Code);
//                                if (versionCode >= Integer.parseInt(Code)) {
//                                    // 如果当前版本号于服务端版本号相同
//                                    Toast.makeText(mContext, "已是最新版本，不需要更新", Toast.LENGTH_SHORT).show();
//
//                                } else {
//                                    // 如果当前版本号于服务端版本号不相同,则弹出提示更新对话框
//                                    showUpdateDialog();
//                                }
//
//                            } catch (NameNotFoundException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//
//                    });


    }

    /**
     * 提示更新对话框
     *
     * @param版本信息对象
     */
    private void showUpdateDialog() {

/*		Intent intent = new Intent();
        intent.setClass(mContext, IsDownLoad.class);
		mContext.startActivity(intent);*/
        final SweetAlertDialog sad = new SweetAlertDialog(mContext);
        sad.setTitleText("版本更新!");
        sad.setContentText(info.getEntity().getRemark());
        sad.setConfirmText("下载");
        sad.setCancelText("取消");
        sad.setCanceledOnTouchOutside(true);
        sad.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

                Toast.makeText(mContext, "本次为强制更新，请下载最新版本", Toast.LENGTH_SHORT).show();

            }
        });
        sad.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                // 弹出下载框
                showDownloadDialog();
                sad.dismiss();

            }
        });
        sad.setCancelable(false);
        sad.show();
    }

    /**
     * 弹出下载框
     */
    private void showDownloadDialog() {
        try {
            Builder builder = new Builder(mContext);
            builder.setTitle("版本更新中...");
            final LayoutInflater inflater = LayoutInflater.from(mContext);
            View v = inflater.inflate(R.layout.softupdate_progress, null);

            progressBar = (ProgressBar) v.findViewById(R.id.update_progress);
            builder.setView(v);
            builder.setNegativeButton("取消", new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //终止下载
                    isInterceptDownload = true;
                }
            });
            builder.create().show();
            //下载apk
            downloadApk();
        } catch (Exception e) {

        }
    }


    /**
     * 下载apk
     */
    private void downloadApk() {
        //开启另一线程下载
        Thread downLoadThread = new Thread(downApkRunnable);
        downLoadThread.start();
    }

    /**
     * 从服务器下载新版apk的线程
     */
    private Runnable downApkRunnable = new Runnable() {
        @Override
        public void run() {
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //如果没有SD卡
                Builder builder = new Builder(mContext);
                builder.setTitle("提示");
                builder.setMessage("当前设备无SD卡，数据无法下载");
                builder.setPositiveButton("确定", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                return;
            } else {
                try {
                    //服务器上新版apk地址
                    //URL url = new URL("http://192.168.15.222:8888/Map/GridManagerPhone.apk");
                    URL url = new URL(info.getEntity().getAndUrl());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/updateFile/");
                    if (!file.exists()) {
                        //如果文件夹不存在,则创建
                        file.mkdir();
                    }
                    //下载服务器中新版本软件（写文件）
                    String apkFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/updateFile/" + info.getEntity().getApkName();
                    File ApkFile = new File(apkFile);
                    FileOutputStream fos = new FileOutputStream(ApkFile);
                    int count = 0;
                    byte buf[] = new byte[1024];
                    do {
                        int numRead = is.read(buf);
                        count += numRead;
                        //更新进度条
                        progress = (int) (((float) count / length) * 100);
                        handler.sendEmptyMessage(1);
                        if (numRead <= 0) {
                            //下载完成通知安装
//                            handler.sendEmptyMessage(0);
                            installApk();
                            break;
                        }
                        fos.write(buf, 0, numRead);
                        //当点击取消时，则停止下载
                    } while (!isInterceptDownload);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * 声明一个handler来跟进进度条
     */
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    // 更新进度情况
                    Log.e("TAG","进度更新"+progress);
                    progressBar.setProgress(progress);
                    break;
                case 0:
                    progressBar.setVisibility(View.INVISIBLE);
                    // 安装apk文件
                    alertDialog.dismiss();
                    installApk();
                    break;
                default:
                    break;
            }
        }

        ;
    };

    /**
     * 安装apk
     */
    private void installApk() {
        // 获取当前sdcard存储路径
        File apkfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/updateFile/" + info.getEntity().getApkName());
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent();
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setAction(Intent.ACTION_VIEW);
        // 安装，如果签名不一致，可能出现程序未安装提示
        String type = getMIMEType(apkfile);
        i.setDataAndType(Uri.fromFile(new File(apkfile.getAbsolutePath())), type);
        try {
            mContext.startActivity(i);
        } catch (Exception e) {

        }


    }

    private String getMIMEType(File file) {
        String type = "*/*";
        String fName = file.getName();
        //获取后缀名前的分隔符"."在fName中的位置。
        int dotIndex = fName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        /* 获取文件的后缀名*/
        String end = fName.substring(dotIndex, fName.length()).toLowerCase();
        if (end == "") return type;
        //在MIME和文件类型的匹配表中找到对应的MIME类型。
        for (int i = 0; i < MIME_MapTable.length; i++) { //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
            if (end.equals(MIME_MapTable[i][0]))
                type = MIME_MapTable[i][1];
        }
        return type;
    }
}
