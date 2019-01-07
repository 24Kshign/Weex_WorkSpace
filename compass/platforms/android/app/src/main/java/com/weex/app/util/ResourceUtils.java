package com.weex.app.util;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.weex.app.WXApplication;

/**
 * 作者：yang
 * 时间：17/6/9
 * 邮箱：yangyang@maihaoche.com
 */
public class ResourceUtils {

    @ColorInt
    public static int getColor(@ColorRes int colorResId) {
        return WXApplication.getInstance().getResources().getColor(colorResId);
    }

    public static Drawable getDrawable(@DrawableRes int drawableResId) {
        return WXApplication.getInstance().getResources().getDrawable(drawableResId);
    }
}
