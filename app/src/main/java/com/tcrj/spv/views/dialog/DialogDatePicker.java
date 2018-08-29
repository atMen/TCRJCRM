package com.tcrj.spv.views.dialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tcrj.spv.R;
import com.tcrj.spv.views.dialog.basedialog.DialogBase;
import com.tcrj.spv.views.utils.Utils;

/**
 * 日期
 * Created by leict on 2017/11/23.
 */

public class DialogDatePicker extends DialogBase {
    private LinearLayout layoutDatepicker;
    private TextView tvDateTitle;
    private DatePicker dpkPicker;
    private Button btnDateSubmit;
    private Button btnDateCancel;
    private IDatePickerCallBack callback = null;

    public DialogDatePicker(Context context) {
        super(context);
    }

    @Override
    public void setTitleContent() {

    }

    @Override
    public void setContainer() {
        this.setCanceledOnTouchOutside(true);
        View view = Utils.getLayoutInflater(getContext()).inflate(R.layout.dialog_datepicker, null);
        tvDateTitle = (TextView) view.findViewById(R.id.tv_date_title);
        dpkPicker = (DatePicker) view.findViewById(R.id.dpk_picker);
        btnDateSubmit = (Button) view.findViewById(R.id.btn_date_submit);
        btnDateCancel = (Button) view.findViewById(R.id.btn_date_cancel);
        addView(view);
        btnDateCancel.setOnClickListener(viewOnClickListen);
        btnDateSubmit.setOnClickListener(viewOnClickListen);
    }

    @Override
    public void OnClickListenEvent(View v) {
        switch (v.getId()) {
            case R.id.btn_date_cancel:
                dismiss();
                break;
            case R.id.btn_date_submit:
                if (callback != null) {
                    callback.onClickListener(getTime(dpkPicker));
                    dismiss();
                }
                break;
        }
    }

    private String getTime(DatePicker date) {
        StringBuffer time = new StringBuffer();
        int year = date.getYear();
        int month = date.getMonth() + 1;
        int day = date.getDayOfMonth();
        time.append(year);
        time.append("-");
        if (1 <= month && month <= 9) {
            time.append("0");
        }
        time.append(month);
        time.append("-");
        if (1 <= day && day <= 9) {
            time.append("0");
        }
        time.append(day);
        return time.toString();
    }

    public void onDatePickerListener(IDatePickerCallBack callback) {
        this.callback = callback;
    }

    public interface IDatePickerCallBack {
        void onClickListener(String time);
    }
}
