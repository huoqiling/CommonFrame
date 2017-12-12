package com.custom.frame.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.telephony.TelephonyManager;
import android.text.ClipboardManager;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.custom.frame.CustomApp;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @date 2017/12/12
 * @author zhangxin
 * @description 公共方法类
 */
public class CommonUtil {

    /**
     * 防止图片oom
     *
     * @return
     */
    public static Bitmap getBitmapForImgResources(Context mContext, int imgId) {
        Bitmap bitmap = null;
        InputStream is = null;
        try {
            is = mContext.getResources().openRawResource(imgId);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inSampleSize = 1;
            bitmap = BitmapFactory.decodeStream(is, null, options);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    /**
     * 获取应用的版本号
     *
     * @return
     */
    public static int getLocalVersionCode(Context context) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取应用的版本名称
     *
     * @return
     */
    public static String getLocalVersionName(Context context) {
        String localVersionName = "1.0.0";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            localVersionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersionName;
    }

    /**
     * @describe 获取android设备id
     * String @throws
     */
    public static String getDeviceId(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param root       最外层布局，需要调整的布局
     * @param scrollView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    public static void controlKeyboardLayout(final View root, final ScrollView scrollView, final int dpHeight) {

        root.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if ((oldBottom - bottom) > 100) {
                    scrollView.smoothScrollTo(0, DensityUtil.dip2px(dpHeight));
                } else if ((bottom - oldBottom) > 100) {
                    scrollView.smoothScrollTo(0, 0);
                }
            }
        });
    }


    /**
     * 字符串强转成数字
     *
     * @param data
     * @return
     */
    public static int str2Int(@NonNull String data) {
        int num = 0;
        if (!TextUtils.isEmpty(data)) {
            try {
                num = Integer.valueOf(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return num;
    }


    /**
     * 保留1位小数
     *
     * @return
     */
    public static String numPointOne(@NonNull String numStr) {
        try {
            double num = Double.parseDouble(numStr);
            DecimalFormat df = new DecimalFormat("0.#");
            df.setRoundingMode(RoundingMode.FLOOR);
            return df.format(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /**
     * 保留两位小数
     *
     * @return
     */
    public static String numPointTwo(@NonNull String numStr) {
        try {
            double num = Double.parseDouble(numStr);
            DecimalFormat df = new DecimalFormat("0.00");
            df.setRoundingMode(RoundingMode.HALF_UP);
            return df.format(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }


    /**
     * double 进一法的实现整数
     *
     * @param num
     * @return
     */
    public static String numPoint2Intger(double num) {
        try {

            return String.valueOf((int) Math.ceil(num));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 字符串转double
     *
     * @param value
     * @return
     */
    public static double Str2Double(@NonNull String value) {
        try {
            if (!TextUtils.isEmpty(value)) {
                return Double.valueOf(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * float转double
     *
     * @param value
     * @return
     */
    public static double float2Double(@NonNull Float value) {
        try {
            if (!TextUtils.isEmpty(String.valueOf(value))) {
                return Double.parseDouble(String.valueOf(value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param et
     * @param s
     * @param pointsNum 小数点位数
     */
    public static void setInput(EditText et, CharSequence s, int pointsNum) {
        try {
            if (s.toString().contains(".")) {
                if (s.length() - 1 - s.toString().indexOf(".") > pointsNum) {
                    s = s.toString().subSequence(0, s.toString().indexOf(".") + pointsNum + 1);
                    et.setText(s);
                    et.setSelection(s.length());
                }
            }
            if (s.toString().trim().substring(0).equals(".")) {
                s = "0" + s;
                et.setText(s);
                et.setSelection(2);
            }
            if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
                if (!s.toString().substring(1, 2).equals(".")) {
                    String str = s.toString().replaceAll("0", "");
                    if (TextUtils.isEmpty(str)) {
                        str = str + "0";
                        et.setText(str);
                    } else {
                        et.setText(str);
                    }
                    et.setSelection(str.length());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 减法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 乘法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }


    /**
     * 除法
     *
     * @param v1
     * @param v2
     * @param scale 精确范围 精确度不能小于0
     * @return
     */
    public static double divide(double v1, double v2, int scale) {
        if (scale > 0) {
            //如果精确范围小于0，抛出异常信息
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
        }
        return 0;
    }

    /**
     * 去除科学计数法
     *
     * @param val
     * @param scale 精确范围 精确度不能小于0
     * @return 截取小数点
     */
    public static String numberFormat(double val, int scale) {
        try {
            java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
            nf.setMaximumFractionDigits(scale);//设置数值的小数部分允许的最大位数。
            nf.setMinimumFractionDigits(scale);// 设置数值的小数部分允许的最小位数。
            nf.setGroupingUsed(false);
            nf.setRoundingMode(RoundingMode.DOWN);
            return nf.format(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /**
     *
     * @param val
     * @param scale
     * @return   保留小数点后四舍五入
     */
    public static String numberRounding(double val, int scale) {
        try {
            java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
            nf.setMaximumFractionDigits(scale);//设置数值的小数部分允许的最大位数。
            nf.setMinimumFractionDigits(scale);// 设置数值的小数部分允许的最小位数。
            nf.setGroupingUsed(false);
            nf.setRoundingMode(RoundingMode.HALF_DOWN);
            return nf.format(val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    /**
     * 去除科学计数法
     * 保留4位小数
     *
     * @param val
     * @return
     */
    public static String numberFormat(double val) {
        return numberFormat(val, 4);
    }


    /**
     * drawable 设置大小
     *
     * @param drawable
     * @param w
     * @param h
     * @return
     */
    public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(null, newbmp);
    }

    private static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 设置TabLayout下划线的边距dp数，该方法要放在TabLayout绘制出来以后
     *
     * @param dp
     * @param tl
     */
    public static void setTabLayoutLineMargin(int dp, TabLayout tl) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR1)
            return;
        try {
            Class tabLayout = tl.getClass();
            Field tabStrip = tabLayout.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
            LinearLayout ll_tab = (LinearLayout) tabStrip.get(tl);
            for (int i = 0; i < ll_tab.getChildCount(); i++) {
                View child = ll_tab.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.setMarginStart(DensityUtil.dip2px(dp));
                params.setMarginEnd(DensityUtil.dip2px(dp));
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取金额
     *
     * @param money
     * @return
     */
    public static String getMoney(@NonNull String money) {
        try {
            if (!TextUtils.isEmpty(money)) {
                return numberFormat(Str2Double(money));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0.0000";
    }


    /**
     * 给TextView 赋值
     *
     * @param textView
     * @param str
     */
    public static void setTextViewData(TextView textView, @NonNull String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            } else {
                textView.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给TextView 赋值
     *
     * @param textView
     * @param htmlStr
     */
    public static void setTextViewHtmlData(TextView textView, @NonNull String htmlStr) {
        try {
            if (!TextUtils.isEmpty(htmlStr)) {
                textView.setText(Html.fromHtml(htmlStr));
            } else {
                textView.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给带单位的TextView
     *
     * @param textView
     * @param str
     * @param colorResId
     * @param colorStr
     */
    public static void setTextViewHtmlData(TextView textView, @NonNull String str, @ColorRes int colorResId, @NonNull String colorStr) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(colorStr)) {
                textView.setText(Html.fromHtml(str + "<font color=" + getColorResources(colorResId) + ">" + colorStr + "</font>"));
            } else {
                textView.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 给EditText赋值
     *
     * @param editText
     * @param str
     */
    public static void setEditTextData(EditText editText, @NonNull String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                editText.setText(str);
                editText.setSelection(str.length());
            } else {
                editText.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取str资源
     *
     * @param strResId
     * @return
     */
    public static String getStringResources(@StringRes int strResId) {
        try {
            return CustomApp.getInstance().getResources().getString(strResId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取颜色
     *
     * @param colorResId
     * @return
     */
    public static int getColorResources(@ColorRes int colorResId) {
        return CustomApp.getInstance().getResources().getColor(colorResId);
    }

    /**
     * 复制
     *
     * @param content
     * @param context
     */
    public static void copy(String content, Context context) {
        if (!TextUtils.isEmpty(content)) {
            ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            cmb.setText(content.trim());
        }
    }

    /**
     * 检查密码
     *
     * @param pass
     * @return
     */
    public static boolean checkPassword(String pass) {
        String str = "^(?![0-9]+$)^(?![a-zA-Z]+$)^(?![!@#$%^&amp;*()_]+$)^(?![\u0391-\uFFE5]+$).{0,100}$";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(pass + "");
        return matcher.matches();
    }


    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context
     * @param packageName：应用包名
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String pkName = packageInfos.get(i).packageName;
                if (pkName.equals(packageName)) return true;
            }
        }
        return false;
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conn.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }


}
