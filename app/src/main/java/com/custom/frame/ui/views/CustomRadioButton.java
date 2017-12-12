package com.custom.frame.ui.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.custom.frame.R;
import com.custom.frame.utils.DensityUtil;


/**
 * @date 2017/12/11
 * @author zhangxin
 * @description 自定义RadioButton
 */
@SuppressLint("AppCompatCustomView")
public class CustomRadioButton extends RadioButton {

    private int mDrawableWidth = 30;// 图片宽度
    private int mDrawableHeight = 30;// 图片高度

    private Drawable drawableLeft = null;
    private Drawable drawableTop = null;
    private Drawable drawableRight = null;
    private Drawable drawableBottom = null;

    public CustomRadioButton(Context context) {
        this(context, null, 0);
    }

    public CustomRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomRadioButton);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CustomRadioButton_drawableWidth:
                    mDrawableWidth = a.getDimensionPixelSize(R.styleable.CustomRadioButton_drawableWidth, mDrawableWidth);
                    break;
                case R.styleable.CustomRadioButton_drawableHeight:
                    mDrawableHeight = a.getDimensionPixelSize(R.styleable.CustomRadioButton_drawableHeight, mDrawableHeight);
                    break;
                case R.styleable.CustomRadioButton_drawableTop:
                    drawableTop = a.getDrawable(attr);
                    break;
                case R.styleable.CustomRadioButton_drawableBottom:
                    drawableBottom = a.getDrawable(attr);
                    break;
                case R.styleable.CustomRadioButton_drawableRight:
                    drawableRight = a.getDrawable(attr);
                    break;
                case R.styleable.CustomRadioButton_drawableLeft:
                    drawableLeft = a.getDrawable(attr);
                    break;
                default:
                    break;
            }
        }
        a.recycle();

        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);

    }

    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if (left != null) {
            left.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }
        if (right != null) {
            right.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }
        if (top != null) {
            top.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }
        if (bottom != null) {
            bottom.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }
        setCompoundDrawables(left, top, right, bottom);
    }

    /**
     * 设置图片宽度
     *
     * @param mDrawableWidth
     */
    public CustomRadioButton setDrawableWidth(int mDrawableWidth) {
        try {
            this.mDrawableWidth = DensityUtil.dip2px(mDrawableWidth);
            setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 设置图片高度
     *
     * @param mDrawableHeight
     */
    public CustomRadioButton setDrawableHeight(int mDrawableHeight) {
        try {
            this.mDrawableHeight = DensityUtil.dip2px(mDrawableHeight);
            setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 设置左方图片
     *
     * @param drawableLeftResId
     */
    public CustomRadioButton setDrawableLeft(@DrawableRes int drawableLeftResId) {
        try {
            this.drawableLeft = getResources().getDrawable(drawableLeftResId);
            setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 设置上方图片
     *
     * @param drawableTopResId
     */
    public CustomRadioButton setDrawableTop(@DrawableRes int drawableTopResId) {
        try {
            this.drawableTop = getResources().getDrawable(drawableTopResId);
            setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 设置右方图片
     *
     * @param drawableRightResId
     */
    public CustomRadioButton setDrawableRight(@DrawableRes int drawableRightResId) {
        try {
            this.drawableRight = getResources().getDrawable(drawableRightResId);
            setCompoundDrawablesWithIntrinsicBounds(null, null, drawableRight, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 设置下方图片
     *
     * @param drawableBottomResId
     */
    public CustomRadioButton setDrawableBottom(@DrawableRes int drawableBottomResId) {
        try {
            this.drawableBottom = getResources().getDrawable(drawableBottomResId);
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawableBottom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}
