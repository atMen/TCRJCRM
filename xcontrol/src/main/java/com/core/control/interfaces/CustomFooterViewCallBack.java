package com.core.control.interfaces;

import android.view.View;

/**
 * Created by leict on 2017/11/8.
 */

public interface CustomFooterViewCallBack {
    void onLoadingMore(View footerView);

    void onLoadMoreComplete(View footerView);

    void onSetNoMore(View footerView, boolean noMore);
}
