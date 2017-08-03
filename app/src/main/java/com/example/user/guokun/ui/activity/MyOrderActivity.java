package com.example.user.guokun.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.user.guokun.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends InitActivity {

    @BindView(R.id.tb_order)
    TabLayout mTbOrder;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

    }

}
