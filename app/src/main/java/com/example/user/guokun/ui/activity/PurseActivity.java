package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.adapter.ChargeListAdapter;
import com.example.user.guokun.alipay.AliPayManager;
import com.example.user.guokun.bean.ChargeBean;
import com.example.user.guokun.bean.ChargePayBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;
import com.example.user.guokun.wxapi.pay.WXPayEntry;
import com.example.user.guokun.wxapi.pay.WXUtils;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PurseActivity extends InitActivity {

    @BindView(R.id.cb_purse_ali)
    CheckBox mCbPurseAli;
    @BindView(R.id.cb_purse_wechat)
    CheckBox mCbPurseWechat;
    @BindView(R.id.rv_charge_list)
    RecyclerView mRvChargeList;
    @BindView(R.id.bt_chongzhi)
    Button mBtChongzhi;
    @BindView(R.id.cb_chongzhi)
    CheckBox mCbChongzhi;
    private SubscriberOnNextListener<ChargeBean> ChargeOnNext;
    private SubscriberOnNextListener<ChargePayBean> ChargePayOnNext;
    private SharedPreferences mPreferences;
    private String PAY_TYPE = "";
    private ChargeListAdapter chargeListAdapter;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_purse);
        ButterKnife.bind(this);
        RxCompoundButton.checkedChanges(mCbPurseAli).subscribe(aBoolean -> {
            if (aBoolean) {
                PAY_TYPE = "2";
                mCbPurseWechat.setChecked(false);
            }
        });
        RxCompoundButton.checkedChanges(mCbPurseWechat).subscribe(aBoolean -> {
            if (aBoolean) {
                PAY_TYPE = "1";
                mCbPurseAli.setChecked(false);
            }
        });
        RxCompoundButton.checkedChanges(mCbChongzhi).subscribe(aBoolean -> {
            if (!aBoolean) {
                mBtChongzhi.setClickable(false);
                mBtChongzhi.setBackgroundColor(getResources().getColor(R.color.second_font));
            } else {
                mBtChongzhi.setClickable(true);
                mBtChongzhi.setBackgroundResource(R.drawable.boder_red);
            }
        });

    }

    @Override
    public void initData() {
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        ChargeOnNext = resultBean -> {
            if (resultBean.getCode() == 1) {
//                mRvChargeList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                mRvChargeList.setLayoutManager(new GridLayoutManager(this, 2));
                chargeListAdapter = new ChargeListAdapter(resultBean.getData(), getApplicationContext());
                mRvChargeList.setAdapter(chargeListAdapter);
            }
        };
        ChargePayOnNext = resultBean -> {
            if (resultBean.getCode() == 1) {
                if (PAY_TYPE.equals("1")) {
                    WXPayEntry entry = WXUtils.parseWXData(resultBean.getData().getResult());
                    WXUtils.startWeChat(PurseActivity.this, entry);
                } else {
                    AliPayManager.getInstance().payV2(PurseActivity.this, resultBean.getData().getResult());
                }
            }
        };
        HttpMethods.getInstance().charge(
                new ProgressSubscriber(ChargeOnNext, PurseActivity.this), mPreferences.getString("token", ""));
    }

    @OnClick({R.id.iv_purse_back, R.id.rl_ali, R.id.rl_wechat, R.id.bt_chongzhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_purse_back:
                finish();
                break;
            case R.id.rl_ali:
                mCbPurseAli.setChecked(true);
                break;
            case R.id.rl_wechat:
                mCbPurseWechat.setChecked(true);
                break;
            case R.id.bt_chongzhi:
                if (PAY_TYPE.equals("")) {
                    Toast.makeText(this, "请选择支付方式！", Toast.LENGTH_SHORT).show();
                } else {
                    HttpMethods.getInstance().charge_pay(new ProgressSubscriber(ChargePayOnNext,
                            PurseActivity.this), mPreferences.getString("token", ""), PAY_TYPE, chargeListAdapter.ID);
                }

                break;
        }
    }

}
