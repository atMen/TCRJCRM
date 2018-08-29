package com.tcrj.spv.views.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.tcrj.spv.R;


/**
 * Created by leict on 2018/7/25.
 */

public class selectDialog extends Dialog {
        private Context context;
        private int height, width;
        private boolean cancelTouchout;
        private View view;

        private String title;
        private EditText sptj;

        private selectDialog(Builder builder) {
            super(builder.context);
            context = builder.context;
            height = builder.height;
            width = builder.width;
            cancelTouchout = builder.cancelTouchout;
            view = builder.view;
            title = builder.title;
        }


        private selectDialog(Builder builder, int resStyle) {
            super(builder.context, resStyle);
            context = builder.context;
            height = builder.height;
            width = builder.width;
            cancelTouchout = builder.cancelTouchout;
            view = builder.view;
            title = builder.title;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(view);

            initview();

            setCanceledOnTouchOutside(cancelTouchout);

            Window win = getWindow();
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.gravity = Gravity.CENTER;
            lp.width= WindowManager.LayoutParams.MATCH_PARENT;
            lp.height= WindowManager.LayoutParams.WRAP_CONTENT;
            win.getDecorView().setPadding(25, 0, 25, 0);
            win.setAttributes(lp);
        }

    private void initview() {
        sptj = (EditText)view.findViewById(R.id.edt_spyj);
        TextView viewBytitle = (TextView) view.findViewById(R.id.title_dialog);
        viewBytitle.setText(title);

    }

    public View getEditText(){
        return sptj;
    }

    public static final class Builder {

            private Context context;
            private int height, width;
            private boolean cancelTouchout;
            private View view;
            private int resStyle = -1;
            private String title;


            public Builder(Context context) {
                this.context = context;
            }

            public Builder view(int resView) {
                view = LayoutInflater.from(context).inflate(resView, null);
                return this;
            }

            public Builder setTitle(String s) {
                title = s;
                return this;
            }

            public Builder heightpx(int val) {
                height = val;
                return this;
            }

            public Builder widthpx(int val) {
                width = val;
                return this;
            }

//            public Builder heightdp(int val) {
//                height = DensityUtil.dip2px(context, val);
//                return this;
//            }
//
//            public Builder widthdp(int val) {
//                width = DensityUtil.dip2px(context, val);
//                return this;
//            }

            public Builder heightDimenRes(int dimenRes) {
                height = context.getResources().getDimensionPixelOffset(dimenRes);
                return this;
            }

            public Builder widthDimenRes(int dimenRes) {
                width = context.getResources().getDimensionPixelOffset(dimenRes);
                return this;
            }

            public Builder style(int resStyle) {
                this.resStyle = resStyle;
                return this;
            }

            public Builder cancelTouchout(boolean val) {
                cancelTouchout = val;
                return this;
            }

            public Builder addViewOnclick(int viewRes,View.OnClickListener listener){
                view.findViewById(viewRes).setOnClickListener(listener);
                return this;
            }


            public selectDialog build() {
                if (resStyle != -1) {
                    return new selectDialog(this, resStyle);
                } else {
                    return new selectDialog(this);
                }
            }
        }

}
