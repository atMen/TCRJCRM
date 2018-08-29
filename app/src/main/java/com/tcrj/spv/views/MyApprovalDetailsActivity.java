package com.tcrj.spv.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.views.application.BaseActivity;
import com.tcrj.spv.views.dialog.SweetAlertDialog;
import com.tcrj.spv.views.xlist.MyWebViewDownLoadListener;


/**
 * 我的审批详情
 * Created by Administrator on 2016/3/3.
 */
public class MyApprovalDetailsActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton imgbtn_back;
    private TextView txtTitle;
    private String Title;
    private String Url;
    private WebView mWebView;
    private ProgressBar mProgressBar = null;

    @Override
    public void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myapprovaldetails);
        Intent intent = getIntent();
        Title = intent.getStringExtra("Title");
        Url = intent.getStringExtra("Url");
//        if (Utils.isStringEmpty(Url)) {
//            setContent();
//        }
        findViewById();
        loadData();
    }

//    private void setContent() {
//        UserInfoEntity userInfoEntity = BaseApplication.getUserInfoEntity();
//        Intent intent = getIntent();
//        if (null != intent) {
//            Bundle bundle = getIntent().getExtras();
//            String result = bundle.getString(JPushInterface.EXTRA_EXTRA);
//            try {
//                String value = new JSONObject(result).getString("url");
//                Url = value + userInfoEntity.getUserTitle();
//                Title = "审批处理";
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public void findViewById() {
        imgbtn_back = (ImageButton) findViewById(R.id.img_back);
        imgbtn_back.setVisibility(View.VISIBLE);
        txtTitle = (TextView) findViewById(R.id.tv_title);
        txtTitle.setText(Title);
        mWebView = (WebView) findViewById(R.id.ariginating_web);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        imgbtn_back.setOnClickListener(this);

    }


    public void loadData() {


        //设置支持JavaScript脚本
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置可以支持缩放
        webSettings.setSupportZoom(true);
        //设置默认缩放方式尺寸是far
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        //设置出现缩放工具
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDefaultFontSize(20);
        //访问地址设置
        mWebView.loadUrl(Url);

        // 设置WebViewClient
        mWebView.setWebViewClient(new WebViewClient() {
            // url拦截
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
                view.loadUrl(url);
                // 相应完成返回true
                return true;
                //return super.shouldOverrideUrlLoading(view, url);
            }

            // 页面开始加载
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            // 页面加载完成
            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            // WebView加载的所有资源url
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });

        // 设置WebChromeClient
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            // 处理javascript中的alert
            public boolean onJsAlert(WebView view, String url, final String message,
                                     final JsResult result) {

                final SweetAlertDialog sad = new SweetAlertDialog(MyApprovalDetailsActivity.this);
                sad.setTitleText("提示");
                sad.setContentText(message);
                sad.setCanceledOnTouchOutside(true);
                sad.setCancelable(true);
                sad.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        if (message.indexOf("成功") >= 0 || message.indexOf("完成") >= 0) {
                            result.confirm();
                            sad.dismiss();
                            finish();
                        } else {
                            result.confirm();
                            sad.dismiss();
                        }

                    }
                });
                sad.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        if (message.indexOf("成功") >= 0 || message.indexOf("完成") >= 0) {
                            result.confirm();
                            sad.dismiss();
                            finish();
                        } else {
                            result.confirm();
                            sad.dismiss();
                        }
                    }
                });
                sad.show();
                result.confirm();
                return true;

            }


            @Override
            // 处理javascript中的confirm
            public boolean onJsConfirm(WebView view, String url,
                                       String message, final JsResult result) {


                return super.onJsConfirm(view, url, message, result);
            }

            ;

            @Override
            // 处理javascript中的prompt
            public boolean onJsPrompt(WebView view, String url, String message,
                                      String defaultValue, final JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            ;

            //设置网页加载的进度条
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }

            //设置程序的Title
            @Override
            public void onReceivedTitle(WebView view, String title) {
                setTitle(title);
                super.onReceivedTitle(view, title);
            }
        });

        mWebView.setDownloadListener(new MyWebViewDownLoadListener(MyApprovalDetailsActivity.this));
//        WebSettings webSettings = ariginating_web.getSettings();
//        //设置WebView属性，能够执行Javascript脚本
//
//        webSettings.setJavaScriptEnabled(true);
//        //设置可以访问文件
//        webSettings.setAllowFileAccess(true);
//        //设置支持缩放
//        webSettings.setBuiltInZoomControls(true);
//        ariginating_web.loadUrl(Url);
//        ariginating_web.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // TODO Auto-generated method stub
//                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
//                view.loadUrl(url);
//                return true;
//            }
//        }
// );

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    /**
     * 拦截返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 是否触发按键为back键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
            return true;
            //如果不是back键正常响应
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void back() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWebView.pauseTimers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.resumeTimers();
    }
}
