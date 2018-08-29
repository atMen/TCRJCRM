package com.tcrj.spv.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tcrj.spv.R;
import com.tcrj.spv.callback.NoticeDetailCallBack;
import com.tcrj.spv.model.NoticeDetailEntity;
import com.tcrj.spv.presenter.NoticeDetailPresenter;
import com.tcrj.spv.views.application.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * 通知公告详情
 */
public class NoticeDetailActivity extends BaseActivity implements NoticeDetailCallBack.View, AdapterView.OnItemClickListener {
    private NoticeDetailCallBack.Presenter presenter;
    private ImageButton imgBack;
    private TextView tvTitle;
    private TextView tvNoticeTitle;
    private TextView tvPerson;
    private TextView tvScope;
    private TextView tvTime;
    private WebView mWebView;

    private ListView listview_fj;
    private LinearLayout ll_fj;
    private int id;

//    private String url = "https://minisite.daojia.com/assets/minisite/2017/sy/lxy_20170324_looking/looking.html";

    private ProgressDialog mProgressDialog;

    // 下载失败
    public static final int DOWNLOAD_ERROR = 2;
    // 下载成功
    public static final int DOWNLOAD_SUCCESS = 1;
    private File file1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticedetail);
        new NoticeDetailPresenter(this);
        initView();
        presenter.start();
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("Id", -1);
        imgBack = (ImageButton) findViewById(R.id.img_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvNoticeTitle = (TextView) findViewById(R.id.tv_notice_detail_title);
        tvPerson = (TextView) findViewById(R.id.tv_notice_detail_person);
        tvScope = (TextView) findViewById(R.id.tv_notice_detail_scope);
        tvTime = (TextView) findViewById(R.id.tv_notice_detail_time);
        mWebView = (WebView) findViewById(R.id.webview_notice);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitle.setText("通知详情");

        listview_fj = (ListView) findViewById(R.id.listview_fj);
        listview_fj.setOnItemClickListener(this);
        ll_fj = (LinearLayout) findViewById(R.id.ll_fj);

        initwebView();
    }

    /**
     * 初始化webView
     */
    private void initwebView() {
        mWebView.requestFocusFromTouch();
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);

    }

    @Override
    public void setPresenter(NoticeDetailCallBack.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void LoadingOn() {
        showProgressDialog();
    }

    @Override
    public void LoadingOff() {
        dismisProgressDialog();
    }
    List<NoticeDetailEntity.FileListBean> fileList;
    @Override
    public void getData(NoticeDetailEntity entity) {
        if (entity != null) {
             fileList = entity.getFileList();
            Log.e("TAG","检查获取到的数据:"+ fileList.size());
            if(fileList.size() != 0){

                ll_fj.setVisibility(View.VISIBLE);

                listview_fj.setAdapter(new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return fileList.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return fileList.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return position;
                    }

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = null;
                        //布局不变，数据变

                        //如果缓存为空，我们生成新的布局作为1个item
                        if(convertView==null){
                            Log.i("info:", "没有缓存，重新生成"+position);
                            LayoutInflater inflater = NoticeDetailActivity.this.getLayoutInflater();
                            //因为getView()返回的对象，adapter会自动赋给ListView
                            view = inflater.inflate(R.layout.listview_item_layout, null);
                        }else{
                            Log.i("info:", "有缓存，不需要重新生成"+position);
                            view = convertView;
                        }
                        NoticeDetailEntity.FileListBean m = fileList.get(position);
                        TextView tv_userName = (TextView)view.findViewById(R.id.tv_fj);
                        tv_userName.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                        Log.e("TAG","要显示的内容:"+ m.getItem2());
                        tv_userName.setText(m.getItem2());
                        tv_userName.setTextSize(15);


                        return view;
                    }
                });


            }else{

                ll_fj.setVisibility(View.GONE);
            }

            tvNoticeTitle.setText(entity.getEntity().getTitle());
            tvPerson.setText("发起人：" + entity.getEntity().getDoStaffName());
            tvScope.setText("发布范围：" + entity.getEntity().getReceivingObject());
            tvTime.setText("发布时间：" + entity.getEntity().getReleaseTime());
            mWebView.loadDataWithBaseURL(null, entity.getEntity().getContent(), "text/html", "UTF-8", null);
            Log.e("TAG","html:"+ entity.getEntity().getContent());
        }
    }

    @Override
    public int setParameterId() {
        return id;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item1 = fileList.get(position).getItem1();
        Log.e("TAG","文档连接地址："+item1);
        initData(item1);
    }

    public void initData(final String Strname) {
        // TODO Auto-generated method stub
//        Intent intent = act.getIntent();
//        final String Strname = intent.getStringExtra("url");
//        final String Strname = "http://192.168.10.6:8066/DownLoadFile.aspx?File=upfile%2f201605161136431.jpg";
//        final String Strname = "http://113.200.26.66:8000/upfile/201609220553341.xlsx";


        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        //截取最后14位 作为文件名
        String s = Strname.substring(Strname.length()-14);
        //文件存储
        file1 = new File(Environment.getExternalStorageDirectory(), getFileName(s));
        new Thread() {
            public void run() {

                File haha = new File( file1.getAbsolutePath());
                //判断是否有此文件
                if (haha.exists()) {
                    //有缓存文件,拿到路径 直接打开
                    Message msg = Message.obtain();
                    msg.obj = haha;
                    msg.what = DOWNLOAD_SUCCESS;
                    handler.sendMessage(msg);
                    mProgressDialog.dismiss();
                    return;
                }
//              本地没有此文件 则从网上下载打开
                File downloadfile = downLoad(Strname, file1.getAbsolutePath(), mProgressDialog);
//              Log.i("Log",file1.getAbsolutePath());
                Message msg = Message.obtain();
                if (downloadfile != null) {
                    // 下载成功,安装....
                    msg.obj = downloadfile;
                    msg.what = DOWNLOAD_SUCCESS;
                } else {
                    // 提示用户下载失败.
                    msg.what = DOWNLOAD_ERROR;
                }
                handler.sendMessage(msg);
                mProgressDialog.dismiss();
            };
        }.start();
    }

    /**
     * 下载完成后  直接打开文件
     */
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case DOWNLOAD_SUCCESS:
                    File file = (File) msg.obj;
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType (Uri.fromFile(file), "application/pdf");
//              startActivity(intent);
                    startActivity(Intent.createChooser(intent, "标题"));
                    /**
                     * 弹出选择框   把本activity销毁
                     */
//                    finish();
                    break;
                case DOWNLOAD_ERROR:
                    Toast.makeText(NoticeDetailActivity.this, "文件加载失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
/**
 *
 */


    /**
     * 传入文件 url  文件路径  和 弹出的dialog  进行 下载文档
     */
    public static File downLoad(String serverpath, String savedfilepath, ProgressDialog pd) {
        try {
            URL url = new URL(serverpath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            if (conn.getResponseCode() == 200) {
                int max = conn.getContentLength();
                pd.setMax(max);
                InputStream is = conn.getInputStream();
                File file = new File(savedfilepath);
                FileOutputStream fos = new FileOutputStream(file);
                int len = 0;
                byte[] buffer = new byte[1024];
                int total = 0;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    total += len;
                    pd.setProgress(total);
                    Log.e("TAG","total"+total);
                }
                fos.flush();
                fos.close();
                is.close();
                return file;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getFileName(String serverurl) {
        return serverurl.substring(serverurl.lastIndexOf("/") + 1);
    }

}
