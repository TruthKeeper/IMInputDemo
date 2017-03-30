package com.tk.iminputdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * <pre>
 *     author : TK
 *     time   : 2017/03/30
 *     desc   : xxxx描述
 * </pre>
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.Holder> {
    @Override
    public TestAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg, parent, false));
    }

    @Override
    public void onBindViewHolder(TestAdapter.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
