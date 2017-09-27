package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.user.guokun.R;
import com.example.user.guokun.adapter.LeaseListAdapter;
import com.example.user.guokun.bean.LeaseBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyLeaseActivity extends InitActivity {

    @BindView(R.id.rv_my_lease_list)
    RecyclerView mRvMyLeaseList;

    private LeaseListAdapter mLeaseListAdapter;
    private SubscriberOnNextListener<LeaseBean> leaseListOnNext;
    private SharedPreferences mPreferences;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_lease);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        HttpMethods.getInstance().lease_list(
                new ProgressSubscriber(leaseListOnNext, MyLeaseActivity.this), mPreferences.getString("token", ""));
    }

    @Override
    public void initData() {
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        mRvMyLeaseList.setLayoutManager(new LinearLayoutManager(this));
        mLeaseListAdapter = new LeaseListAdapter(this);

        leaseListOnNext = leaseBean -> {
            Log.i("chenyi", "initData: " + leaseBean.getStatus());
            if (leaseBean.getStatus() == 1) {
                mLeaseListAdapter.clearData();
                mLeaseListAdapter.addAllData(leaseBean.getData().getList());
                mRvMyLeaseList.setAdapter(mLeaseListAdapter);
                mLeaseListAdapter.setOnItemClickListener((view, data) -> {
                    Intent intent = new Intent(MyLeaseActivity.this, LeaseDetailActivity.class);
                    intent.putExtra("id", leaseBean.getData().getList().get(data).getId());
                    startActivity(intent);
                });
            }
        };
    }


    @OnClick(R.id.iv_map_back)
    public void onViewClicked() {
        finish();
    }
}
