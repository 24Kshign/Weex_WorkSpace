package com.weex.app.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.taobao.weex.ui.component.WXA;
import com.weex.app.WXApplication;

/**
 * Created by gujian
 * Time is 2017/6/8
 * Email is gujian@maihaoche.com
 */

public class SizeUtil {
    public static int dip2px(float dipValue) {
        float reSize = WXApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) ((dipValue * reSize) + 0.5);
    }

    /**
     * 获取某个dp的资源对应的pix值
     */
    public static int pxOfDpResId(int dpResId) {
        try {
            return WXApplication.getInstance().getResources().getDimensionPixelSize(dpResId);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static float sp2px(int spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue,
                WXApplication.getInstance().getResources().getDisplayMetrics());
    }

    public static float dp2sp(int dpValue) {
        DisplayMetrics displayMetrics = WXApplication.getInstance().getResources().getDisplayMetrics();
        return (float) dpValue / displayMetrics.density * displayMetrics.scaledDensity;
    }

    public static int px2dip(int pxValue) {
        float reSize = WXApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) ((pxValue / reSize) + 0.5);
    }

    public static int getScreenWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }
}

