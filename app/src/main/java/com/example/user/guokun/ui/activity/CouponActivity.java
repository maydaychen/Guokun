package com.example.user.guokun.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.user.guokun.R;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponActivity extends InitActivity {

    @BindView(R.id.rv_coupon)
    PullLoadMoreRecyclerView mRvCoupon;
    @BindView(R.id.fl_coupon)
    FrameLayout mFlCoupon;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);
        mFlCoupon.removeAllViews();
        mFlCoupon.addView(LayoutInflater.from(this).inflate(R.layout.empty_view, null));
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
