package com.custom.frame.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author zhangxin
 * @date 2017/12/12
 * @description 对输入法的控制
 */
public class KeyBoardUtil {

    /**
     * 打卡软键盘
     *
     * @param view 获取焦点的view
     * @param mContext  上下文
     */
    public static void openKeybord(View view, Context mContext) {
        try {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭软键盘
     *
     * @param view 获取焦点的view
     * @param mContext  上下文
     */
    public static void closeKeybord(View view, Context mContext) {
        try {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if(imm.isActive()){
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
