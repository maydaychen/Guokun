package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.adapter.CouponsAdapter;
import com.example.user.guokun.bean.CouponBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressErrorSubscriber;
import com.example.user.guokun.http.SubscriberOnNextAndErrorListener;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponActivity extends InitActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    @BindView(R.id.rv_coupon)
    PullLoadMoreRecyclerView mRvCoupon;
    @BindView(R.id.fl_coupon)
    FrameLayout mFlCoupon;

    private int page = 1;
    private SharedPreferences mPreferences;
    private CouponsAdapter mRecyclerViewAdapter;
    private SubscriberOnNextAndErrorListener<CouponBean> AllCouponsOnNext;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        AllCouponsOnNext = new SubscriberOnNextAndErrorListener<CouponBean>() {
            @Override
            public void onNext(CouponBean vspaBean) {
                mRvCoupon.setVisibility(View.VISIBLE);
                try {
                    mRvCoupon.setPullLoadMoreCompleted();
                    mRecyclerViewAdapter.addAllData(vspaBean.getData().getList());
                } catch (NullPointerException e) {
                    if (page != 0) {
                        Toast.makeText(CouponActivity.this, "已经加载完毕！", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                mRvCoupon.setVisibility(View.VISIBLE);
                mRvCoupon.setPullLoadMoreCompleted();
            }
        };

        RecyclerView recyclerView = mRvCoupon.getRecyclerView();
        recyclerView.setVerticalScrollBarEnabled(true);
        mRvCoupon.setRefreshing(false);
        mRvCoupon.setPullRefreshEnable(true);
        mRvCoupon.setPushRefreshEnable(true);
        mRvCoupon.setFooterViewText("正在加载，请稍后");
        mRvCoupon.setFooterViewTextColor(R.color.second_font);
        mRvCoupon.setFooterViewBackgroundColor(R.color.order_e7);
        mRvCoupon.setLinearLayout();

        mRvCoupon.setOnPullLoadMoreListener(this);
        mRvCoupon.setEmptyView(LayoutInflater.from(CouponActivity.this).inflate(R.layout.empty_view, null));
        mRecyclerViewAdapter = new CouponsAdapter(CouponActivity.this);
        mRvCoupon.setAdapter(mRecyclerViewAdapter);

    }

    @Override
    public void initData() {
        getData1();
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

    @Override
    public void onRefresh() {
        setRefresh();
        getData1();
    }

    private void setRefresh() {
        mRecyclerViewAdapter.clearData();
        mRecyclerViewAdapter.notifyDataSetChanged();
        page = 1;
        mRvCoupon.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoadMore() {
        page = page + 1;
        getData1();
    }

    private void getData1() {
        HttpMethods.getInstance().all_coupon(new ProgressErrorSubscriber<>(AllCouponsOnNext,
                CouponActivity.this), mPreferences.getString("token", ""), page);
    }

}
