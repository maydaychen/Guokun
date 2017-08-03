package com.example.user.guokun.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.user.guokun.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponActivity extends InitActivity {

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);


    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.iv_coupon_back, R.id.tv_coupon_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_coupon_back:
                finish();
                break;
            case R.id.tv_coupon_detail:
                startActivity(new Intent(CouponActivity.this, CouponDetailActivity.class));
                break;
        }
    }
}
