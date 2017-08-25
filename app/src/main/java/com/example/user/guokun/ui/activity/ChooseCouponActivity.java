package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.user.guokun.R;
import com.example.user.guokun.adapter.CouponsAdapter;
import com.example.user.guokun.bean.CouponBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseCouponActivity extends InitActivity {
    private SubscriberOnNextListener<CouponBean> AllCouponsOnNext;
    private SharedPreferences mPreferences;

    @BindView(R.id.rv_coupon)
    RecyclerView rvCoupon;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_choose_coupon);
        ButterKnife.bind(this);
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        AllCouponsOnNext = couponBean -> {
            CouponsAdapter couponsAdapter = new CouponsAdapter(ChooseCouponActivity.this);
            rvCoupon.setLayoutManager(new LinearLayoutManager(ChooseCouponActivity.this));
            rvCoupon.setAdapter(couponsAdapter);
            couponsAdapter.addAllData(couponBean.getData());
            couponsAdapter.setOnItemClickListener((view, data) -> {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("objs", couponBean.getData().get(data));
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            });
        };

    }


    @Override
    public void initData() {
        HttpMethods.getInstance().useable_coupon(new ProgressSubscriber<>(AllCouponsOnNext,
                ChooseCouponActivity.this), mPreferences.getString("token", ""));
    }

    @OnClick({R.id.iv_coupon_back, R.id.tv_coupon_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_coupon_back:
                finish();
                break;
            case R.id.tv_coupon_detail:
                break;
        }
    }
}
