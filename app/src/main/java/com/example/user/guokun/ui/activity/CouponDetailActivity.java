package com.example.user.guokun.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.user.guokun.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponDetailActivity extends InitActivity {

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_coupon_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.iv_coupon_detail_back)
    public void onViewClicked() {
        finish();
    }
}
