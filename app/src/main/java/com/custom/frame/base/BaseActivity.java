package com.custom.frame.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.custom.frame.CustomApp;
import com.custom.frame.R;
import com.custom.frame.ui.views.CustomTitleBar;
import com.custom.frame.utils.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author zhangxin
 * @date 2017/12/11
 * @description 基类activity
 */
public abstract class BaseActivity extends AppCompatActivity implements CustomTitleBar.OnCustomTitleBarListener {

    private CustomTitleBar titleBar;
    private FrameLayout flContainer;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {//修改重复回到前一个activity
            finish();
            return;
        }
        setContentView(R.layout.activity_base);
        findBaseViewById();
        initBase();
        initView();
    }

    /**
     * 初始化默认布局的View
     */
    private void findBaseViewById() {
        titleBar = (CustomTitleBar) findViewById(R.id.titleBar);
        flContainer = (FrameLayout) findViewById(R.id.flContainer);
        titleBar.setOnCustomTitleBarListener(this);
    }

    private void initBase(){
        CustomApp.getInstance().addActivity(this);
        setContentLayout(getLayoutId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ViewGroup view = (ViewGroup) getWindow().getDecorView();
            StatusBarUtil.transparencyBar(this);
            view.setPadding(0, getStatusBarHeight(), 0, 0);
        } else {
            setStatusBar(R.color.colorPrimary);
        }

        if(useEventBus()){
            EventBus.getDefault().register(this);
        }
    }

    /**
     * @param resId 资源文件ID
     */
    private void setContentLayout(int resId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(resId, null);
        unbinder = ButterKnife.bind(this, contentView);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(layoutParams);
        flContainer.addView(contentView);
    }


    /**
     * 设置标题栏颜色
     *
     * @param id
     */
    protected void setStatusBar(@ColorRes int id) {
        StatusBarUtil.setStatusBarColor(this, id);
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        // 获得状态栏高度
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        return getResources().getDimensionPixelSize(resourceId);
    }

    protected abstract @LayoutRes int getLayoutId();

    protected abstract void initView();

    protected abstract boolean useEventBus();

    @Override
    public void onLeftImageClick() {

    }

    @Override
    public void onLeftTextClick() {

    }

    @Override
    public void onRightTextClick() {

    }

    @Override
    public void onOneRightImageClick() {

    }

    @Override
    public void onSecondRightImageClick() {

    }

    @Override
    public void onTitleClick() {

    }

    public CustomTitleBar getTitleBar() {
        return titleBar;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CustomApp.getInstance().removeActivity(this);
        if (null != unbinder) {
            unbinder.unbind();
        }
        if(useEventBus()){
            EventBus.getDefault().unregister(this);
        }
    }
}
