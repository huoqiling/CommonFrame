package com.custom.frame.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.custom.frame.R;

/**
 * @date 2017/12/11
 * @author zhangxin
 * @description 用于显示背景透明的DialogFragment
 */
public abstract class BaseDialogFragment extends AppCompatDialogFragment {

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog == null ? null : dialog.getWindow();
        if (null != dialog && null != window) {
            window.setLayout(-1, -2);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_Dialog);  //具有阴影效果
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(getLayoutId(), null);
        initDialog();

        return mView;
    }

    private void initDialog() {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); //去除标题栏
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().setCancelable(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        afterCreate(savedInstanceState);
    }

    protected abstract @LayoutRes int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);

    /**
     * 添加在onStart()
     */
    public void viewGravityBottom(){
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);
    }
}
