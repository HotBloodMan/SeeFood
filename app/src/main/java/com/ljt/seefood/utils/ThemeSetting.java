package com.ljt.seefood.utils;

import com.ljt.seefood.application.BaseApplication;

/**
 * Created by 1 on 2017/3/22.
 */

public class ThemeSetting {
    public static final String THEME = "theme_color";//主题


    public static void setTheme(int themeIndex) {
        SPUtil.put(BaseApplication.getContext(), THEME, themeIndex);
    }

    public static int getTheme() {
        return (int) SPUtil.get(BaseApplication.getContext(), THEME, 0);
    }
}
