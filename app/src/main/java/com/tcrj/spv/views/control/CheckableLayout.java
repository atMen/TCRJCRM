package com.tcrj.spv.views.control;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.tcrj.spv.views.interfaces.ICheckBox;

/**
 * 多选列表
 * Created by leict on 2017/11/22.
 */

public class CheckableLayout extends RelativeLayout implements ICheckBox {
    private static final int[] CHECKED_STATE_SET = {android.R.attr.state_checked};
    private boolean mChecked;

    public CheckableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置是否选中。当我们点击item的时候，会调用这个方法。
     *
     * @param b
     */
    @Override
    public void setChecked(boolean b) {
        if (b != mChecked) {
            mChecked = b;
            refreshDrawableState();
        }
    }

    /**
     * 判断是否选中。
     *
     * @return
     */
    @Override
    public boolean isChecked() {
        return mChecked;
    }

    /**
     * 开关，如果当前是选中的状态，调用该方法后取消选中，反之，选中。
     */
    @Override
    public void toggle() {
        setChecked(!mChecked);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        return drawableState;
    }
}