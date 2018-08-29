package com.tcrj.spv.views.dialog;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.tcrj.spv.R;
import com.tcrj.spv.views.dialog.basedialog.DialogBase;
import com.tcrj.spv.views.utils.Utils;

/**
 * 拍照
 * Created by leict on 2017/12/18.
 */

public class DialogTakePhoto extends DialogBase {
    private LinearLayout layoutPhotoTake;
    private LinearLayout layoutPhotoPicture;
    private ITakePhotoCallBack callBack;

    public DialogTakePhoto(Context context) {
        super(context);
    }

    @Override
    public void setTitleContent() {

    }

    @Override
    public void setContainer() {
        this.setCanceledOnTouchOutside(true);
        View view = Utils.getLayoutInflater(getContext()).inflate(R.layout.dialog_tackphoto, null);
        layoutPhotoTake = (LinearLayout) view.findViewById(R.id.layout_photo_take);
        layoutPhotoPicture = (LinearLayout) view.findViewById(R.id.layout_photo_picture);
        addView(view);
        layoutPhotoPicture.setOnClickListener(viewOnClickListen);
        layoutPhotoTake.setOnClickListener(viewOnClickListen);
    }

    @Override
    public void OnClickListenEvent(View v) {
        switch (v.getId()) {
            case R.id.layout_photo_picture:
                if (callBack != null) {
                    callBack.setOnPicturesListener();
                    dismiss();
                }
                break;
            case R.id.layout_photo_take:
                if (callBack != null) {
                    callBack.setOnTakePhotoListener();
                    dismiss();
                }
                break;
        }
    }

    public void setOnClickListener(ITakePhotoCallBack callBack) {
        this.callBack = callBack;
    }

    public interface ITakePhotoCallBack {
        void setOnTakePhotoListener();

        void setOnPicturesListener();
    }
}
