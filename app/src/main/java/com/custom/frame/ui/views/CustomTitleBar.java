package com.custom.frame.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.custom.frame.R;
import com.custom.frame.base.BaseFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @date 2017/12/11
 * @author zhangxin
 * @description  自定义标题栏
 */
public class CustomTitleBar extends BaseFrameLayout {

    @BindView(R.id.btnLeftImage)
    public ImageView btnLeftImage;

    @BindView(R.id.btnLeftText)
    public TextView btnLeftText;

    @BindView(R.id.tvTitle)
    public CustomRadioButton tvTitle;

    @BindView(R.id.btnOneRightImage)
    public ImageView btnOneRightImage;

    @BindView(R.id.btnSecondRightImage)
    public ImageView btnSecondRightImage;

    @BindView(R.id.btnRightText)
    public TextView btnRightText;


    private String leftText = "";
    private int leftTextColor = Color.parseColor("#ffffff");
    private String titleText = "";
    private String rightText = "";
    private int rightTextColor = Color.parseColor("#ffffff");
    private boolean showLeftImage = true;
    private boolean showOneRightImage = false;
    private boolean showSecondRightImage = false;
    private int rightOneImageRes = 0;
    private int rightSecondImageRes = 0;

    private OnCustomTitleBarListener titleBarListener;

    public void setOnCustomTitleBarListener(OnCustomTitleBarListener titleBarListener) {
        this.titleBarListener = titleBarListener;
    }

    public CustomTitleBar(Context context) {
        this(context, null);
    }

    public CustomTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        try {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = mInflater.inflate(R.layout.view_title_bar, this);
            ButterKnife.bind(view);

            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
            leftText = a.getString(R.styleable.CustomTitleBar_leftText);
            leftTextColor = a.getColor(R.styleable.CustomTitleBar_leftTextColor, leftTextColor);
            titleText = a.getString(R.styleable.CustomTitleBar_titleText);
            rightText = a.getString(R.styleable.CustomTitleBar_rightText);
            rightTextColor = a.getColor(R.styleable.CustomTitleBar_rightTextColor, rightTextColor);
            showLeftImage = a.getBoolean(R.styleable.CustomTitleBar_showLeftImage, true);
            showOneRightImage = a.getBoolean(R.styleable.CustomTitleBar_showOneRightImage, false);
            showSecondRightImage = a.getBoolean(R.styleable.CustomTitleBar_showSecondRightImages, false);
            rightOneImageRes = a.getResourceId(R.styleable.CustomTitleBar_rightOneImageRes, 0);
            rightSecondImageRes = a.getResourceId(R.styleable.CustomTitleBar_rightSecondImageRes, 0);
            a.recycle();
            initView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        setTitleText(titleText);
        setRightBtnText(rightText);
        if (!showLeftImage) {
            invisibleView(btnLeftImage);
        }
        if (!showOneRightImage) {
            invisibleView(btnOneRightImage);
        }

        if (!showSecondRightImage) {
            invisibleView(btnSecondRightImage);
        }

        if (rightOneImageRes > 0) {
            setOneRightImageResource(rightOneImageRes);
        }

        if (rightSecondImageRes > 0) {
            setSecondRightImageResource(rightSecondImageRes);
        }
    }

    public void setLeftBtnText(@StringRes int leftTextResId) {
        setLeftBtnText(getStringResources(leftTextResId));
    }

    public void setLeftBtnText(@NonNull String leftText) {
        try {
            if (!TextUtils.isEmpty(leftText)) {
                btnLeftText.setText(leftText);
                btnLeftText.setTextColor(leftTextColor);
                showView(btnLeftText);
                goneView(btnLeftImage);
            } else {
                goneView(btnLeftText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 设置标题
     * @param title
     */
    public void setTitleText(@NonNull String title) {
        try {
            setTextViewData(tvTitle, title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置标题
     * @param titleResId
     */
    public void setTitleText(@StringRes int titleResId) {
        setTitleText(getStringResources(titleResId));
    }

    public void setRightBtnText(@StringRes int rightBtnTextResId) {
        setRightBtnText(getStringResources(rightBtnTextResId));
    }

    public void setRightBtnText(@NonNull String text) {
        try {
            if (!TextUtils.isEmpty(text)) {
                btnRightText.setText(text);
                btnRightText.setTextColor(rightTextColor);
                showView(btnRightText);
                goneView(btnOneRightImage);
            } else {
                goneView(btnRightText);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTitleRightImage(){

    }

    /**
     * 设置左边图片
     * @param drawableId
     */
    public void setleftImageResource(@DrawableRes int drawableId) {
        try {
            btnLeftImage.setImageResource(drawableId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置右边图片
     *
     * @param drawableId
     */
    public void setOneRightImageResource(@DrawableRes int drawableId) {
        try {
            btnOneRightImage.setImageResource(drawableId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置右边图片2
     *
     * @param drawableId
     */
    public void setSecondRightImageResource(@DrawableRes int drawableId) {
        try {
            btnSecondRightImage.setImageResource(drawableId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示左边文字按钮
     */
    public void showLeftText() {
        showView(btnLeftText);
    }

    /**
     * 隐藏左边文字按钮
     */
    public void hideLeftText() {
        goneView(btnLeftText);
    }


    /**
     * 隐藏右边图片
     */
    public void hideOneRightImage() {
        goneView(btnOneRightImage);
    }

    /**
     * 隐藏右边图片2
     */
    public void hideSecondRightImage() {
        goneView(btnSecondRightImage);
    }



    /**
     * 显示左边图片
     */
    public void showLeftImage() {
        showView(btnLeftImage);
    }

    /**
     * 隐藏左边图片
     */
    public void hideLeftImage() {
        goneView(btnLeftImage);
    }


    /**
     * 隐藏右边按钮
     */
    public void hideRightText() {
        goneView(btnRightText);
    }

    /**
     * 显示右边图片
     */
    public void showOneRightImage() {
        showView(btnOneRightImage);
    }

    /**
     * 显示右边图片2
     */
    public void showSecondRightImage() {
        showView(btnSecondRightImage);
    }


    /**
     * 显示右边按钮
     */
    public void showRightText() {
        showView(btnRightText);
    }


    @OnClick({R.id.btnLeftText, R.id.btnLeftImage, R.id.btnOneRightImage, R.id.btnSecondRightImage,R.id.btnRightText,R.id.tvTitle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLeftText:
                if (null != titleBarListener) {
                    titleBarListener.onLeftTextClick();
                }
                break;
            case R.id.btnLeftImage:
                if (null != titleBarListener) {
                    titleBarListener.onLeftImageClick();
                }
                break;
            case R.id.btnOneRightImage:
                if (null != titleBarListener) {
                    titleBarListener.onOneRightImageClick();
                }
                break;
            case R.id.btnSecondRightImage:
                if (null != titleBarListener) {
                    titleBarListener.onSecondRightImageClick();
                }
                break;
            case R.id.btnRightText:
                if (null != titleBarListener) {
                    titleBarListener.onRightTextClick();
                }
                break;
            case R.id.tvTitle:
                if (null != titleBarListener) {
                    titleBarListener.onTitleClick();
                }
                break;
        }
    }


    public interface OnCustomTitleBarListener {

        void onLeftImageClick();

        void onLeftTextClick();

        void onRightTextClick();

        void onOneRightImageClick();

        void onSecondRightImageClick();

        void onTitleClick();
    }
}
