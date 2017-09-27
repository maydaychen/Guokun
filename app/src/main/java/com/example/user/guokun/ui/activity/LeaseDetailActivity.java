package com.example.user.guokun.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.LeaseDetailBean;
import com.example.user.guokun.bean.ResultBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.user.guokun.Utils.qiangzhi_logout;

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
    @BindView(R.id.tv_lease_end_date)
    TextView mTvLeaseEndDate;
    @BindView(R.id.tv_project1_name)
    TextView mTvProject1Name;
    @BindView(R.id.tv_project1)
    TextView mTvProject1;
    @BindView(R.id.tv_project2_name)
    TextView mTvProject2Name;
    @BindView(R.id.tv_project2)
    TextView mTvProject2;
    @BindView(R.id.tv_project3_name)
    TextView mTvProject3Name;
    @BindView(R.id.tv_project3)
    TextView mTvProject3;
    @BindView(R.id.ll_project1)
    LinearLayout mLlProject1;
    @BindView(R.id.ll_project2)
    LinearLayout mLlProject2;
    @BindView(R.id.ll_project3)
    LinearLayout mLlProject3;

    private SubscriberOnNextListener<LeaseDetailBean> leaseDetailOnNext;
    private SubscriberOnNextListener<ResultBean> leaseStartOnNext;
    private SharedPreferences mPreferences;
    private int ID;
    private double price;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_lease_detail);
        ButterKnife.bind(this);
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        leaseDetailOnNext = leaseDetailBean -> {
            price = leaseDetailBean.getData().getPrice();
            mTvLeasePeriod.setText(String.format(getResources().getString(R.string.tv_lease_period), leaseDetailBean.getData().getPeriod() + ""));
            mTvLeasePrice.setText(String.format(getResources().getString(R.string.tv_lease_price), leaseDetailBean.getData().getPrice() + ""));
            mTvLeaseFee.setText(String.format(getResources().getString(R.string.tv_lease_fee), leaseDetailBean.getData().getFee() + ""));
            mTvLeasePledge.setText(String.format(getResources().getString(R.string.tv_lease_pledge), leaseDetailBean.getData().getPledge() + ""));
            mTvLeaseStartDate.setText(String.format(getResources().getString(R.string.tv_lease_start_date), leaseDetailBean.getData().getStart_time() + ""));
            mTvLeaseEndDate.setText(String.format(getResources().getString(R.string.tv_lease_end_date), leaseDetailBean.getData().getEnd_time() + ""));

            mTvProject1Name.setText(leaseDetailBean.getData().getCosts().get(0).getName());
            mTvProject2Name.setText(leaseDetailBean.getData().getCosts().get(1).getName());
            mTvProject3Name.setText(leaseDetailBean.getData().getCosts().get(2).getName());
            mTvProject1.setText(String.format(getResources().getString(R.string.project_time), leaseDetailBean.getData().getCosts().get(0).getTime_len() + ""));
            mTvProject2.setText(String.format(getResources().getString(R.string.project_time), leaseDetailBean.getData().getCosts().get(1).getTime_len() + ""));
            mTvProject3.setText(String.format(getResources().getString(R.string.project_time), leaseDetailBean.getData().getCosts().get(2).getTime_len() + ""));

            mLlProject1.setOnFocusChangeListener((view, b) -> {
                if (b) {
                    ID = leaseDetailBean.getData().getCosts().get(0).getId();
                }
            });
            mLlProject2.setOnFocusChangeListener((view, b) -> {
                if (b) {
                    ID = leaseDetailBean.getData().getCosts().get(1).getId();
                }
            });
            mLlProject3.setOnFocusChangeListener((view, b) -> {
                if (b) {
                    ID = leaseDetailBean.getData().getCosts().get(2).getId();
                }
            });
        };

        leaseStartOnNext = resultBean -> {
            switch (resultBean.getCode()) {
                case 1:
                    Toast.makeText(LeaseDetailActivity.this, resultBean.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case -9:
                    qiangzhi_logout(LeaseDetailActivity.this);
                    break;
                case 5:
                    new AlertDialog.Builder(LeaseDetailActivity.this)
                            .setTitle("警告")
                            .setMessage("按摩椅已过期，请前往充值")
                            .setPositiveButton("确定", (dialog, which) -> {
                                Intent intent = new Intent(LeaseDetailActivity.this, PayTypeActivity.class);
                                intent.putExtra("id", getIntent().getIntExtra("id", 0));
                                intent.putExtra("fee", price);
                                startActivity(intent);
                            })
                            .setNegativeButton("取消", (dialogInterface, i) -> dialogInterface.dismiss())
                            .show();
                    break;
                default:
                    Toast.makeText(LeaseDetailActivity.this, resultBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void initData() {
        HttpMethods.getInstance().lease_info(
                new ProgressSubscriber(leaseDetailOnNext, LeaseDetailActivity.this),
                mPreferences.getString("token", ""), getIntent().getIntExtra("id", 0));
    }

    @OnClick({R.id.iv_map_back, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_map_back:
                finish();
                break;
            case R.id.bt_submit:
                HttpMethods.getInstance().lease_start(
                        new ProgressSubscriber(leaseStartOnNext, LeaseDetailActivity.this),
                        mPreferences.getString("token", ""), getIntent().getIntExtra("id", 0), ID);
                break;
        }
    }

}
