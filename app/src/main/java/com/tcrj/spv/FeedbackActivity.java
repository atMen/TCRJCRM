package com.tcrj.spv;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import com.tcrj.spv.views.application.BaseActivity;

/**
 * 意见反馈
 * Created by leict on 2016/1/19.
 */
public class FeedbackActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton imgbtnBack;
    private TextView txtTitle;
    private EditText edtFeedbackContent;
    private Button btnFeedbackSubmit;


    @Override
    public void initView() {
        imgbtnBack = (ImageButton) findViewById(R.id.img_back);
        txtTitle = (TextView) findViewById(R.id.tv_title);
        edtFeedbackContent = (EditText) findViewById(R.id.edt_feedback_content);
        btnFeedbackSubmit = (Button) findViewById(R.id.btn_feedback_submit);
        imgbtnBack.setVisibility(View.VISIBLE);
        imgbtnBack.setOnClickListener(this);
        txtTitle.setText("意见反馈");
        btnFeedbackSubmit.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_feedback_submit:
                String content = edtFeedbackContent.getText().toString().trim();
                if ("".equals(content)) {
                    displayToast("请输入您宝贵意见");
                    return;
                }
//                loadData();
                displayToast("意见反馈成功");
                finish();
                break;
        }
    }
}
