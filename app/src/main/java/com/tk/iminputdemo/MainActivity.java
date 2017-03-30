package com.tk.iminputdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void undeal(View v) {
        startActivity(new Intent(this,UndealActivity.class));
    }

    public void deal(View v) {
        startActivity(new Intent(this,DealActivity.class));
    }
}
