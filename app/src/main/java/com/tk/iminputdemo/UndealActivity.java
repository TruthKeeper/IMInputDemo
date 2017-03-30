package com.tk.iminputdemo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

public class UndealActivity extends AppCompatActivity {
    private SwipeRefreshLayout swipeLayout;
    private RecyclerView recyclerview;
    private EditText etContent;
    private CheckBox cbxEmoji;
    private Button btnSend;
    private LinearLayout pannel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_undeal);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        etContent = (EditText) findViewById(R.id.et_content);
        cbxEmoji = (CheckBox) findViewById(R.id.cbx_emoji);
        btnSend = (Button) findViewById(R.id.btn_send);
        pannel = (LinearLayout) findViewById(R.id.pannel);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);
        recyclerview.setAdapter(new TestAdapter());
        cbxEmoji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbxEmoji.isChecked()) {
                    //显示Emoji面板
                    pannel.setVisibility(View.VISIBLE);
                    etContent.clearFocus();
                    KeyBoardUtils.hideKeyboard(etContent);
                } else {
                    //隐藏
                    pannel.setVisibility(View.GONE);
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
        etContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && !etContent.isFocused()) {
                    //触摸输入框时弹出输入法
                    cbxEmoji.setChecked(false);
                    pannel.setVisibility(View.GONE);
                    KeyBoardUtils.showKeyboard(etContent);
                }
                return false;
            }
        });

    }


}
