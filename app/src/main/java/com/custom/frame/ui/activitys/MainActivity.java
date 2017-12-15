package com.custom.frame.ui.activitys;

import com.custom.frame.R;
import com.custom.frame.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        getTitleBar().setTitleText("首页");
        getTitleBar().hideLeftImage();
    }

    @Override
    public boolean doubleExitAppEnable() {
        return true;
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }
}
