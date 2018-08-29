package com.tcrj.spv.views.xlist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.DownloadListener;

/**
 * Created by leict on 2018/2/26.
 */

public class MyWebViewDownLoadListener implements DownloadListener {

    private Context context;

    public MyWebViewDownLoadListener(Context context) {

        this.context = context;
    }

    @Override
    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        Log.i("tag", "url="+url);

        Log.i("tag", "userAgent="+userAgent);

        Log.i("tag", "contentDisposition="+contentDisposition);

        Log.i("tag", "mimetype="+mimetype);

        Log.i("tag", "contentLength="+contentLength);

        Uri uri = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        context.startActivity(intent);
    }
}
