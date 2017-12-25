package com.custom.frame.ui.views;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @date 2017/12/20
 * @author zhangxin
 * @description 去除ScrollView自动滑动到底部
 */
public class NoAutoScrollView extends ScrollView {

    public NoAutoScrollView(Context context) {
        super(context);
    }

    public NoAutoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoAutoScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }
}
