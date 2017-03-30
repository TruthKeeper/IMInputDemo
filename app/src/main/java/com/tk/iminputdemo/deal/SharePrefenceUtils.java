package com.tk.iminputdemo.deal;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * <pre>
 *     author : TK
 *     time   : 2017/03/30
 *     desc   : 记录工具
 * </pre>
 */
public class SharePrefenceUtils {
    public static final String KEYBOARD = "keyboard";
    public static final String HEIGHT = "height";

    /**
     * 保存软键盘高度
     *
     * @param context
     * @param height
     */
    public static void saveKeyBoardHeight(@NonNull Context context, int height) {
        context.getSharedPreferences(KEYBOARD, Context.MODE_PRIVATE).edit().putInt(HEIGHT, height).commit();
    }

    /**
     * 获取软键盘高度，默认为界面一半高度
     *
     * @param context
     * @return
     */
    public static int getKeyBoardHeight(@NonNull Context context) {
        int defaultHeight = context.getResources().getDisplayMetrics().heightPixels >> 1;
        return context.getSharedPreferences(KEYBOARD, Context.MODE_PRIVATE).getInt(HEIGHT, defaultHeight);
    }
}
