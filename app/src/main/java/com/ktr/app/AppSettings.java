package com.ktr.app;

import com.ktr.utils.ActivityHelper;

/**
 * Created by kisstherain on 2015/9/27.
 */
public class AppSettings {

    public static int getThemeColor() {
        return ActivityHelper.getIntShareData("Theme_index", 8);
    }

    public static void setThemeColor(int theme) {
        ActivityHelper.putIntShareData("Theme_index", theme);
    }
}
