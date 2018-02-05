package com.custom.frame.base;

import android.content.Context;
import android.util.AttributeSet;

import com.custom.frame.R;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

/**
 * @Author zhangxin
 * @date 2018/2/5 15:19
 * @description 下拉刷新
 **/
public class BaseRefreshLayout extends TwinklingRefreshLayout {


    public BaseRefreshLayout(Context context) {
        this(context, null);
    }

    public BaseRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ProgressLayout progressLayout = new ProgressLayout(context);
        progressLayout.setProgressBackgroundColorSchemeResource(R.color.colorAccent);
        progressLayout.setColorSchemeResources(R.color.white);
        setHeaderView(progressLayout);
        setFocusableInTouchMode(true);
        setFocusable(true);
    }


}
