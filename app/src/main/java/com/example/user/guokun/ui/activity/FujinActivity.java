package com.example.user.guokun.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.user.guokun.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FujinActivity extends InitActivity {

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fujin);

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_coupon_back, R.id.tv_coupon_detail, R.id.tv_relocate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_coupon_back:
                finish();
                break;
            case R.id.tv_coupon_detail:
                startActivity(new Intent(FujinActivity.this,CouponDetailActivity.class));
                break;
            case R.id.tv_relocate:
                break;
        }
    }
}
