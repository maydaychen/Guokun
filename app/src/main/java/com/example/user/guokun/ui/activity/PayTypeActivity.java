package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.guokun.R;
import com.example.user.guokun.alipay.AliPayManager;
import com.example.user.guokun.http.HttpJsonMethod;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;
import com.example.user.guokun.wxapi.pay.WXPayEntry;
import com.example.user.guokun.wxapi.pay.WXUtils;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayTypeActivity extends InitActivity {

    @BindView(R.id.cb_pay_ali)
    CheckBox mCbPayAli;
    @BindView(R.id.rl_ali)
    RelativeLayout mRlAli;
    @BindView(R.id.cb_pay_wechat)
    CheckBox mCbPayWechat;
    @BindView(R.id.rl_wechat)
    RelativeLayout mRlWechat;
    @BindView(R.id.cb_pay_yue)
    CheckBox mCbPayYue;
    @BindView(R.id.rl_yue)
    RelativeLayout mRlYue;
    @BindView(R.id.tv_pay_price)
    TextView mTvPayPrice;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;
    @BindView(R.id.tv_pay_project)
    TextView mTvPayProject;
    private String PAY_TYPE = "";
    private SubscriberOnNextListener<JSONObject> PayOnNext;
    private SharedPreferences mPreferences;
    private String order_num;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pay_type);
        ButterKnife.bind(this);
        mBtSubmit.setText(String.format(getResources().getString(R.string.submit), getIntent().getStringExtra("price")));
        mTvPayPrice.setText(String.format(getResources().getString(R.string.price), getIntent().getStringExtra("price")));
        mTvPayProject.setText(getIntent().getStringExtra("type"));
    }

    @Override
    public void initData() {
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
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
        PayOnNext = jsonObject -> {
            if (jsonObject.getInt("code") == 1) {
                order_num =  jsonObject.getJSONObject("data").getString("outTradeNo");
                switch (PAY_TYPE) {
                    case "0":
                        break;
                    case "1":
                        WXPayEntry entry = WXUtils.parseWXData(jsonObject.getString("result"));
                        WXUtils.startWeChat(PayTypeActivity.this, entry);
                        break;
                    case "2":
                        AliPayManager.getInstance().payV2(PayTypeActivity.this, jsonObject.getJSONObject("data").getString("result"));
                        break;

                }
            }
        };
    }


    @OnClick({R.id.iv_guige_back, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_guige_back:
                finish();
                break;
            case R.id.bt_submit:
                HttpJsonMethod.getInstance().pay(new ProgressSubscriber(PayOnNext,
                                PayTypeActivity.this), mPreferences.getString("token", ""),
                        getIntent().getIntExtra("id", 0), getIntent().getStringExtra("code"), PAY_TYPE);
                break;
        }
    }
}