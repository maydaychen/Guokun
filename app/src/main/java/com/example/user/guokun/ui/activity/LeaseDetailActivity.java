package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.LeaseDetailBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeaseDetailActivity extends InitActivity {
    @BindView(R.id.tv_lease_period)
    TextView mTvLeasePeriod;
    @BindView(R.id.tv_lease_price)
    TextView mTvLeasePrice;
    @BindView(R.id.tv_lease_fee)
    TextView mTvLeaseFee;
    @BindView(R.id.tv_lease_pledge)
    TextView mTvLeasePledge;
    @BindView(R.id.tv_lease_start_date)
    TextView mTvLeaseStartDate;

    private SubscriberOnNextListener<LeaseDetailBean> leaseDetailOnNext;
    private SharedPreferences mPreferences;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_lease_detail);
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        leaseDetailOnNext = leaseDetailBean -> {
            mTvLeasePeriod.setText();
        };
    }

    @Override
    public void initData() {
        HttpMethods.getInstance().lease_info(
                new ProgressSubscriber(leaseDetailOnNext, LeaseDetailActivity.this),
                mPreferences.getString("token", ""), getIntent().getStringExtra("id"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_map_back, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_map_back:
                break;
            case R.id.bt_submit:
                break;
        }
    }
}
