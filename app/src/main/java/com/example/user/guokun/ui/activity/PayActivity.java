package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.alipay.AliPayManager;
import com.example.user.guokun.alipay.AliPayMessage;
import com.example.user.guokun.http.HttpShopMethod;
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

import static com.example.user.guokun.R.id.tv_pay_solder_detail;


public class PayActivity extends InitActivity {
    @BindView(R.id.cb_pay_ali)
    CheckBox mCbPayAli;
    @BindView(R.id.cb_pay_wechat)
    CheckBox mCbPayWechat;
    @BindView(R.id.rl_ali)
    RelativeLayout mRlAli;
    @BindView(R.id.rl_wechat)
    RelativeLayout mRlWechat;
    @BindView(tv_pay_solder_detail)
    TextView mTvPaySolderDetail;
    @BindView(R.id.tv_pay_money_detail)
    TextView mTvPayMoneyDetail;
    private String PAY_TYPE = "wechat_app";
    private SubscriberOnNextListener<JSONObject> payOnNext;
    private SharedPreferences preferences;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mTvPaySolderDetail.setText(getIntent().getStringExtra("order_num"));
        mTvPayMoneyDetail.setText(String.format(getResources().getString(R.string.price), getIntent().getStringExtra("price")));
        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        payOnNext = jsonObject -> {
            if (jsonObject.getInt("statusCode") == 1) {
                Log.i("chenyi", "pay: " + jsonObject.toString());
                switch (PAY_TYPE) {
                    case "wechat_app":
                        WXPayEntry entry = WXUtils.parseWXData(jsonObject.get("result") + "");
                        WXUtils.startWeChat(PayActivity.this, entry);
                        break;
                    case "alipay_app":
                        AliPayManager.getInstance().payV2(PayActivity.this, jsonObject.getString("result"));
                        break;
                }
            } else {
                Toast.makeText(PayActivity.this, jsonObject.getString("result"), Toast.LENGTH_SHORT).show();
            }

        };
    }

    @Override
    public void initData() {
        RxCompoundButton.checkedChanges(mCbPayAli).subscribe(aBoolean -> {
            if (aBoolean) {
                PAY_TYPE = "alipay_app";
                mCbPayWechat.setChecked(false);
            }
        });
        RxCompoundButton.checkedChanges(mCbPayWechat).subscribe(aBoolean -> {
            if (aBoolean) {
                PAY_TYPE = "wechat_app";
                mCbPayAli.setChecked(false);
            }
        });
        mRlAli.setOnClickListener(v -> mCbPayAli.setChecked(true));
        mRlWechat.setOnClickListener(v -> mCbPayWechat.setChecked(true));
    }


    @OnClick({R.id.iv_guige_back, R.id.bt_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_guige_back:
                finish();
                break;
            case R.id.bt_pay:
                if (PAY_TYPE.equals("")) {
                    Toast.makeText(this, "请选择支付方式！", Toast.LENGTH_SHORT).show();
                } else {
                    HttpShopMethod.getInstance().pay(
                            new ProgressSubscriber(payOnNext, PayActivity.this),
                            preferences.getString("access_token", ""), preferences.getString("sessionkey", ""),
                            getIntent().getStringExtra("order_num"), PAY_TYPE);
                }
                break;
            default:
                break;
        }
    }

    //EventBus阿里支付结果回调事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(AliPayMessage payMessage) {
        if (payMessage.errorCode == 0) {
            Intent yifahuo = new Intent(PayActivity.this, GoodsOrdersActivity.class);
            yifahuo.putExtra("id", 1);
            startActivity(yifahuo);
        } else {
            Toast.makeText(this, payMessage.result, Toast.LENGTH_SHORT).show();
        }
    }

    //EventBus微信支付结果回调事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(WXPayMessage payMessage) {
        if (payMessage.errorCode == 0) {
            Intent yifahuo = new Intent(PayActivity.this, GoodsOrdersActivity.class);
            yifahuo.putExtra("id", 1);
            startActivity(yifahuo);
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
