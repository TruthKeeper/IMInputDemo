package com.tk.iminputdemo.deal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : TK
 *     time   : 2017/03/30
 *     desc   : 监听软键盘
 * </pre>
 */
public class KeyBoardObservable {
    private static final String TAG = "KeyBoardObservable";

    private int lastHeight;
    private List<KeyBoardObserver> observers;

    private boolean keyBoardVisibile;

    /**
     * 注册监听
     *
     * @param listener
     */
    public void register(@NonNull KeyBoardObserver listener) {
        if (observers == null) {
            observers = new ArrayList<>();
        }
        observers.add(listener);
    }

    /**
     * 注销
     *
     * @param listener
     */
    public void unRegister(@NonNull KeyBoardObserver listener) {
        if (observers != null) {
            observers.remove(listener);
        }
    }

    /**
     * 抢先测量
     *
     * @param heightMeasureSpec
     */
    public void beforeMeasure(Context context, int heightMeasureSpec) {
        int height = View.MeasureSpec.getSize(heightMeasureSpec);
        Log.d(TAG, "height: " + height);
        if (lastHeight == 0) {
            lastHeight = height;
            return;
        }
        if (lastHeight == height) {
            //没有发生挤压
            return;
        }
        final int offset = lastHeight - height;
        if (Math.abs(offset) < DensityUtil.dp2px(80)) {
            //80 dp 挤压阈值
            return;
        }

        if (offset > 0) {
            Log.d(TAG, "软键盘显示了");
            keyBoardVisibile = true;
        } else {
            Log.d(TAG, "软键盘隐藏了");
            keyBoardVisibile = false;
        }
        int keyBoardHeight = Math.abs(offset);
        SharePrefenceUtils.saveKeyBoardHeight(context, keyBoardHeight);
        update(keyBoardVisibile, keyBoardHeight);
        lastHeight = height;
    }

    public boolean isKeyBoardVisibile() {
        return keyBoardVisibile;
    }

    /**
     * 通知更新
     *
     * @param keyBoardVisibile
     */
    private void update(final boolean keyBoardVisibile, int keyBoardHeight) {
        if (observers != null) {
            for (KeyBoardObserver observable : observers) {
                observable.update(keyBoardVisibile, keyBoardHeight);
            }
        }
    }
}
