package com.tk.iminputdemo.deal;

import android.content.res.Resources;

/**
 * <pre>
 *     author : TK
 *     time   : 2017/03/30
 *     desc   : 像素工具
 * </pre>
 */
public class DensityUtil {
    /**
     * dp > px
     *
     * @param dpValue
     * @return
     */
    public static int dp2px(float dpValue) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }
}
