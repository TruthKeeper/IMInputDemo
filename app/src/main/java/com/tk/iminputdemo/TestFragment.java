package com.tk.iminputdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tk.iminputdemo.deal.KeyBoardObservable;
import com.tk.iminputdemo.deal.SharePrefenceUtils;

/**
 * <pre>
 *     author : TK
 *     time   : 2017/04/05
 *     desc   : Fragment，业务逻辑代码与 DealInActivityActivity 雷同
 * </pre>
 */
public class TestFragment extends Fragment {
    private SwipeRefreshLayout swipeLayout;
    private RecyclerView recyclerview;
    private EditText etContent;
    private CheckBox cbxEmoji;
    private Button btnSend;
    private LinearLayout pannel;

    private KeyBoardObservable observable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.include_im_content, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        etContent = (EditText) view.findViewById(R.id.et_content);
        cbxEmoji = (CheckBox) view.findViewById(R.id.cbx_emoji);
        btnSend = (Button) view.findViewById(R.id.btn_send);
        pannel = (LinearLayout) view.findViewById(R.id.pannel);

        //初始化高度，和软键盘一致，初值为手机高度一半
        pannel.getLayoutParams().height = SharePrefenceUtils.getKeyBoardHeight(getContext());

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        recyclerview.setAdapter(new TestAdapter());
        cbxEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbxEmoji.isChecked()) {
                    //想要显示面板
                    if (observable.isKeyBoardVisibile()) {
                        //当前软键盘为 挂起状态
                        //隐藏软键盘并显示面板
                        etContent.clearFocus();
                        KeyBoardUtils.hideKeyboard(etContent);
                    } else {
                        //显示面板
                        pannel.setVisibility(View.VISIBLE);
                    }
                } else {
                    //想要关闭面板
                    //挂起软键盘，并隐藏面板
                    etContent.requestFocus();
                    KeyBoardUtils.showKeyboard(etContent);
                }
            }
        });
        recyclerview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //点击列表部分就清除焦点并初始化状态
                    if (pannel.getVisibility() == View.VISIBLE) {
                        pannel.setVisibility(View.GONE);
                        cbxEmoji.setChecked(false);
                    } else {
                        etContent.clearFocus();
                        KeyBoardUtils.hideKeyboard(etContent);
                    }
                }
                return false;
            }
        });
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeLayout.setRefreshing(false);
            }
        });
    }

    public void update(boolean keyBoardVisibile, int keyBoardHeight) {
        if (keyBoardVisibile) {
            //软键盘挂起
            pannel.setVisibility(View.GONE);
            cbxEmoji.setChecked(false);

        } else {
            //回复原样
            if (cbxEmoji.isChecked()) {
                if (pannel.getLayoutParams().height != keyBoardHeight) {
                    pannel.getLayoutParams().height = keyBoardHeight;
                }
                pannel.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setObservable(KeyBoardObservable keyBoardObservable) {
        observable = keyBoardObservable;
    }
}
