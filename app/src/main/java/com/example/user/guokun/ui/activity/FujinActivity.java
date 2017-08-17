package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.user.guokun.R;
import com.example.user.guokun.adapter.FujinAdapter;
import com.example.user.guokun.bean.ChairNearbyBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FujinActivity extends InitActivity {
    @BindView(R.id.rv_fujin)
    RecyclerView mRvFujin;
    private SubscriberOnNextListener<ChairNearbyBean> VspaOnNext;
    private SharedPreferences mPreferences;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fujin);
        ButterKnife.bind(this);

    }

    @Override
    public void initData() {
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        VspaOnNext = chairNearbyBean -> {
            mRvFujin.setLayoutManager(new LinearLayoutManager(this));
            FujinAdapter fujinAdapter = new FujinAdapter(chairNearbyBean.getData().getList(), getApplicationContext());
            mRvFujin.setAdapter(fujinAdapter);
        };
        Log.i("chenyi", "initData: " + getIntent().getDoubleExtra("lat", 0.00) + "+" + getIntent().getDoubleExtra("lon", 0.00));
        HttpMethods.getInstance().char_nearby(new ProgressSubscriber<>(VspaOnNext, FujinActivity.this),
                mPreferences.getString("token", ""), getIntent().getDoubleExtra("lon", 0.00) + "",
                getIntent().getDoubleExtra("lat", 0.00) + "");
    }


    @OnClick({R.id.iv_coupon_back, R.id.tv_coupon_detail, R.id.tv_relocate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_coupon_back:
                finish();
                break;
            case R.id.tv_coupon_detail:
                startActivity(new Intent(FujinActivity.this, CouponDetailActivity.class));
                break;
            case R.id.tv_relocate:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
