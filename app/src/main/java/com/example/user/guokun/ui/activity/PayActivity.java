package com.example.user.guokun.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.guokun.R;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.user.guokun.R.id.tv_pay_solder_detail;


public class PayActivity extends InitActivity {
    @BindView(R.id.cb_pay_ali)
    CheckBox mCbPayAli;
    @BindView(R.id.cb_pay_wechat)
    CheckBox mCbPayWechat;
    @BindView(R.id.cb_pay_yue)
    CheckBox mCbPayYue;
    @BindView(R.id.rl_ali)
    RelativeLayout mRlAli;
    @BindView(R.id.rl_wechat)
    RelativeLayout mRlWechat;
    @BindView(R.id.rl_yue)
    RelativeLayout mRlYue;
    @BindView(tv_pay_solder_detail)
    TextView mTvPaySolderDetail;
    @BindView(R.id.tv_pay_money_detail)
    TextView mTvPayMoneyDetail;
    private String PAY_TYPE = "";

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        mTvPaySolderDetail.setText("国坤健康");
        mTvPayMoneyDetail.setText(String.format(getResources().getString(R.string.price), "998"));
    }

    @Override
    public void initData() {
        RxCompoundButton.checkedChanges(mCbPayAli).subscribe(aBoolean -> {
            if (aBoolean) {
                PAY_TYPE = "2";
                mCbPayWechat.setChecked(false);
                mCbPayYue.setChecked(false);
            }
        });
        RxCompoundButton.checkedChanges(mCbPayWechat).subscribe(aBoolean -> {
            if (aBoolean) {
                PAY_TYPE = "1";
                mCbPayAli.setChecked(false);
                mCbPayYue.setChecked(false);
            }
        });
        RxCompoundButton.checkedChanges(mCbPayYue).subscribe(aBoolean -> {
            if (aBoolean) {
                PAY_TYPE = "0";
                mCbPayAli.setChecked(false);
                mCbPayWechat.setChecked(false);
            }
        });
        mRlAli.setOnClickListener(v -> mCbPayAli.setChecked(true));
        mRlWechat.setOnClickListener(v -> mCbPayWechat.setChecked(true));
        mRlYue.setOnClickListener(v -> mCbPayYue.setChecked(true));
    }


    @OnClick({R.id.iv_guige_back, R.id.bt_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_guige_back:
                finish();
                break;
            case R.id.bt_pay:
                startActivity(new Intent(PayActivity.this, GoodsDetailActivity.class));
                break;
        }
    }
}
