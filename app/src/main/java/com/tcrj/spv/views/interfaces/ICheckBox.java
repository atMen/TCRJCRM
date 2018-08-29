package com.tcrj.spv.views.interfaces;

/**
 * 多选列表
 * Created by leict on 2017/11/22.
 */

public interface ICheckBox {
    /**
     * Change the checked state of the view
     *
     * @param checked The new checked state
     */
    void setChecked(boolean checked);

    /**
     * @return The current checked state of the view
     */
    boolean isChecked();

    /**
     * Change the checked state of the view to the inverse of its current state
     */
    void toggle();
}
