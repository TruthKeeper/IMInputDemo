package com.tk.iminputdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tk.iminputdemo.deal.KeyBoardObserver;
import com.tk.iminputdemo.deal.MeasureLinearLayout;

/**
 * <pre>
 *     author : TK
 *     time   : 2017/04/05
 *     desc   : 内嵌Fragment的场景
 * </pre>
 */
public class DealInFragmentActivity extends AppCompatActivity implements KeyBoardObserver {
    private MeasureLinearLayout rootLayout;

    private TestFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_in_fragment);

        rootLayout = (MeasureLinearLayout) findViewById(R.id.root_layout);

        rootLayout.getKeyBoardObservable().register(this);

        fragment = (TestFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        fragment.setObservable(rootLayout.getKeyBoardObservable());
    }


    @Override
    public void update(boolean keyBoardVisibile, int keyBoardHeight) {
        //转发
        fragment.update(keyBoardVisibile, keyBoardHeight);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rootLayout.getKeyBoardObservable().unRegister(this);
    }
}
