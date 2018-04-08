package com.custom.frame.ui.activitys;

import android.os.Bundle;

import com.custom.frame.R;
import com.custom.frame.base.BaseActivity;

import butterknife.BindView;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;

/**
 * @author zhangxin
 * @date 2018/2/27
 * @description 首页
 */
public class MainActivity extends BaseActivity {

    private static final int SAVE_PHOTO = 1;
    private static final int PRC_PHOTO_PREVIEW = 2;

    @BindView(R.id.photoLayout)
    BGANinePhotoLayout photoLayout;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        getTitleBar().setTitleText("爱笑的你");
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
