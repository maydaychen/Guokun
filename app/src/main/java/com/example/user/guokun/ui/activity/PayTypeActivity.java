package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.alipay.AliPayManager;
import com.example.user.guokun.alipay.AliPayMessage;
import com.example.user.guokun.http.HttpJsonMethod;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;
import com.example.user.guokun.wxapi.pay.WXPayEntry;
import com.example.user.guokun.wxapi.pay.WXPayMessage;
import com.example.user.guokun.wxapi.pay.WXUtils;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
//    @BindView(R.id.cb_pay_yue)
//    CheckBox mCbPayYue;
//    @BindView(R.id.rl_yue)
//    RelativeLayout mRlYue;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;
    @BindView(R.id.tv_price)
    TextView mTvPrice;

    private String PAY_TYPE = "";
    private SubscriberOnNextListener<JSONObject> payOnNext;
    private SharedPreferences mPreferences;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pay_type);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mBtSubmit.setText(String.format(getResources().getString(R.string.submit), getIntent().getDoubleExtra("fee", 0.00) + ""));
        mTvPrice.setText(String.format(getResources().getString(R.string.price), getIntent().getDoubleExtra("fee", 0.00) + ""));
    }

    @Override
    public void initData() {
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        RxCompoundButton.checkedChanges(mCbPayAli).subscribe(aBoolean -> {
            if (aBoolean) {
                PAY_TYPE = "2";
                mCbPayWechat.setChecked(false);
            }
        });
        RxCompoundButton.checkedChanges(mCbPayWechat).subscribe(aBoolean -> {
            if (aBoolean) {
                PAY_TYPE = "1";
                mCbPayAli.setChecked(false);
            }
        });

        mRlAli.setOnClickListener(v -> mCbPayAli.setChecked(true));
        mRlWechat.setOnClickListener(v -> mCbPayWechat.setChecked(true));
        payOnNext = jsonObject -> {
            if (jsonObject.getInt("status") == 1) {
                switch (PAY_TYPE) {
//                    case "0":
//                        if (jsonObject.getString("message").equals("支付成功")) {
//                            Toast.makeText(PayTypeActivity.this, "支付成功！", Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
//                        break;
                    case "1":
                        WXPayEntry entry = WXUtils.parseWXData(jsonObject.getString("data"));
                        WXUtils.startWeChat(PayTypeActivity.this, entry);
                        break;
                    case "2":
                        AliPayManager.getInstance().payV2(PayTypeActivity.this, jsonObject.getJSONObject("data").getString("result"));
                        break;

                }
            } else {
                Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            }
        };
//        payOnNext = new SubscriberOnNextListener<ResultBean>() {
//            @Override
//            public void onNext(ResultBean resultBean) throws JSONException {
//                switch (resultBean.getCode()) {
//                    case 1:
//                        switch (PAY_TYPE) {
//                            case "0":
//                                if (resultBean.getMessage().equals("支付成功")) {
//                                    Toast.makeText(PayTypeActivity.this, "支付成功！", Toast.LENGTH_SHORT).show();
//                                    finish();
//                                }
//                                break;
//                            case "1":
//                                WXPayEntry entry = WXUtils.parseWXData(resultBean.get);
//                                WXUtils.startWeChat(PayTypeActivity.this, entry);
//                                break;
//                            case "2":
//                                AliPayManager.getInstance().payV2(PayTypeActivity.this, jsonObject.getJSONObject("data").getString("result"));
//                                break;
//
//                        }
//                        break;
//                    case -9:
//                        qiangzhi_logout(PayTypeActivity.this);
//                        break;
//                    default:
//                        Toast.makeText(PayTypeActivity.this, resultBean.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        };

    }


    @OnClick({R.id.iv_guige_back, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_guige_back:
                finish();
                break;
            case R.id.bt_submit:
                if (PAY_TYPE.equals("")) {
                    Toast.makeText(this, "请选择支付方式！", Toast.LENGTH_SHORT).show();
                } else {
                    HttpJsonMethod.getInstance().lease_pay(new ProgressSubscriber(payOnNext,
                                    PayTypeActivity.this), mPreferences.getString("token", ""),
                            getIntent().getIntExtra("id", 0), PAY_TYPE);
                }
                break;
        }
    }

    //EventBus阿里支付结果回调事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(AliPayMessage payMessage) {
        if (payMessage.errorCode == 0) {
            Toast.makeText(PayTypeActivity.this, "支付成功！", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, payMessage.result, Toast.LENGTH_SHORT).show();
        }
    }

    //EventBus微信支付结果回调事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(WXPayMessage payMessage) {
        if (payMessage.errorCode == 0) {
            Toast.makeText(PayTypeActivity.this, "支付成功！", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, payMessage.errorStr, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
