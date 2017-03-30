package com.tk.iminputdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * <pre>
 *     author : TK
 *     time   : 2017/03/30
 *     desc   : 输入法工具
 * </pre>
 */
public class KeyBoardUtils {

    /**
     * 显示软键盘
     *
     * @param view
     */
    public static void showKeyboard(@NonNull View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public static void hideKeyboard(@NonNull View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
