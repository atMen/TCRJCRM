package com.tcrj.spv.views.application;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tcrj.spv.R;
import com.tcrj.spv.views.dialog.DialogLoading;
import com.tcrj.spv.views.dialog.basedialog.Effectstype;

/**
 * 所有Activity都要继承此类
 * Created by leict on 2017/10/23.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public static int win_height;
    public static int win_width;
    public DialogLoading loading;

    /**
     * 获取页面控件
     */
    public abstract void initView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dm = this.getApplicationContext().getResources().getDisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        win_width = dm.widthPixels;
        win_height = dm.heightPixels;
    }

    /**
     * 加载进度条
     *
     * @param message
     */
    public void showProgressDialog(String message) {
        loading = DialogLoading.getInstance(this);
        loading.setMessage(message)
                .setDuration(700)
                .setEffect(Effectstype.Fadein)
                .show();
    }

    public void showProgressDialog() {
        loading = DialogLoading.getInstance(this);
        loading.setMessage("正在加载...")
                .setDuration(700)
                .setEffect(Effectstype.Fadein)
                .show();
    }

    /**
     * 关闭进度条
     */
    public void dismisProgressDialog() {
        if (loading == null) {
            return;
        } else {
            if (loading.isShowing()) {
                loading.dismiss();
                loading = null;
            }
        }
    }

    /**
     * 根据传入的类(class)打开指定的activity
     *
     * @param pClass
     */
    protected void openNewActivity(Class<?> pClass) {
        Intent _Intent = new Intent();
        _Intent.setClass(this, pClass);
        startActivity(_Intent);
    }

    /**
     * Intent带值跳转
     * @param context
     * @param clazz
     * @param bundle
     */
    protected void toClass(Context context, Class clazz, Bundle bundle){

        Intent intent = new Intent(context,clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * Intent带值跳转
     * @param context
     * @param clazz
     * @param bundle
     */
    protected void toClass(Context context, Class clazz, Bundle bundle,int num){

        Intent intent = new Intent(context,clazz);
        if(bundle != null){
            intent.putExtras(bundle);
        }
        startActivityForResult(intent,num);
    }

    /**
     * Toast提示
     *
     * @param text
     */
    public void displayToast(String text) {
        if (text == null)
            return;
        View view = getLayoutInflater().inflate(R.layout.myself_toast, null);
        TextView message = (TextView) view.findViewById(R.id.chapterName);
        RelativeLayout relativeToast = (RelativeLayout) view.findViewById(R.id.relative_toast);
        relativeToast.getBackground().setAlpha(200);
        message.setText(text);
        Toast start = new Toast(this);
        int get_height = win_height / 6;
        start.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0,
                get_height);
        start.setDuration(Toast.LENGTH_SHORT);
        start.setView(view);
        start.show();
    }

    /**
     * 提示框
     *
     * @param view
     * @param message
     */
    public void showSnackbar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundResource(R.color.toast);//设置SnackBar背景颜色
        snackbar.setActionTextColor(Color.WHITE);               //设置按钮文字颜色
        setSnackbarMessageTextColor(snackbar, Color.WHITE);
        snackbar.show();
    }

    /**
     * 设置Snackbar上的字体颜色
     *
     * @param snackbar
     * @param color
     */
    public static void setSnackbarMessageTextColor(Snackbar snackbar, int color) {
        View view = snackbar.getView();
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(color);
    }
}
