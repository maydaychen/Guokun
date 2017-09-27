package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.adapter.ChargeListAdapter;
import com.example.user.guokun.alipay.AliPayManager;
import com.example.user.guokun.alipay.AliPayMessage;
import com.example.user.guokun.bean.ChargeBean;
import com.example.user.guokun.bean.UserInfoBean;
import com.example.user.guokun.http.HttpJsonMethod;
import com.example.user.guokun.http.HttpMethods;
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

import static com.example.user.guokun.Utils.qiangzhi_logout;

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
    @BindView(R.id.tv_purse_yue)
    TextView mTvPurseYue;
    @BindView(R.id.tv_purse_xieyi)
    TextView tvPurseXieyi;

    private SubscriberOnNextListener<UserInfoBean> UserOnNext;
    private SubscriberOnNextListener<ChargeBean> ChargeOnNext;
    private SubscriberOnNextListener<JSONObject> ChargePayOnNext;
    private SharedPreferences mPreferences;
    private String PAY_TYPE = "";
    private ChargeListAdapter chargeListAdapter;


    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_purse);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        SpannableStringBuilder builder1 = new SpannableStringBuilder(tvPurseXieyi.getText().toString());
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
        builder1.setSpan(redSpan, tvPurseXieyi.getText().length() - 6, tvPurseXieyi.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvPurseXieyi.setText(builder1);

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
                mBtChongzhi.setBackgroundResource(R.drawable.boder_grey);
            } else {
                mBtChongzhi.setClickable(true);
                mBtChongzhi.setBackgroundResource(R.drawable.boder_red);
            }
        });
        tvPurseXieyi.setOnClickListener(view -> {});
        mCbChongzhi.setChecked(true);
    }

    @Override
    public void initData() {
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        ChargeOnNext = resultBean -> {
            if (resultBean.getStatus() == 1) {
//                mRvChargeList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                mRvChargeList.setLayoutManager(new GridLayoutManager(this, 2));
                chargeListAdapter = new ChargeListAdapter(resultBean.getData().getList(), getApplicationContext());
                mRvChargeList.setAdapter(chargeListAdapter);
            } else if (resultBean.getStatus() == -9) {
                qiangzhi_logout(PurseActivity.this);
            }
        };
        ChargePayOnNext = jsonObject -> {
            if (jsonObject.getInt("status") == 1) {
                switch (PAY_TYPE) {
                    case "1":
                        WXPayEntry entry = WXUtils.parseWXData(jsonObject.getString("data"));
                        WXUtils.startWeChat(PurseActivity.this, entry);
                        break;
                    case "2":
                        AliPayManager.getInstance().payV2(PurseActivity.this, jsonObject.getJSONObject("data").getString("result"));
                        break;

                }
            } else {
                Toast.makeText(PurseActivity.this, jsonObject.getString("mag"), Toast.LENGTH_SHORT).show();
            }
        };
        UserOnNext = userInfoBean -> {
            if (userInfoBean.getStatus() == 1) {
                mTvPurseYue.setText(userInfoBean.getData().getDeposit() + "");
            } else if (userInfoBean.getStatus() == -9) {
                qiangzhi_logout(PurseActivity.this);
            } else {
                Toast.makeText(PurseActivity.this, userInfoBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        HttpMethods.getInstance().charge(
                new ProgressSubscriber(ChargeOnNext, PurseActivity.this), mPreferences.getString("token", ""));
        HttpMethods.getInstance().user_info(new ProgressSubscriber(UserOnNext,
                PurseActivity.this), mPreferences.getString("token", ""));
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
                    HttpJsonMethod.getInstance().charge_pay(new ProgressSubscriber(ChargePayOnNext,
                            PurseActivity.this), mPreferences.getString("token", ""), PAY_TYPE, chargeListAdapter.ID);
                }

                break;
        }
    }

    //EventBus阿里支付结果回调事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(AliPayMessage payMessage) {
        if (payMessage.errorCode == 0) {
            Intent intent = new Intent(PurseActivity.this, CountingActivity.class);
            intent.putExtra("money",chargeListAdapter.money);
            startActivity(intent);
        } else {
            Toast.makeText(this, payMessage.result, Toast.LENGTH_SHORT).show();
        }
    }

    //EventBus微信支付结果回调事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(WXPayMessage payMessage) {
        if (payMessage.errorCode == 0) {
            Intent intent = new Intent(PurseActivity.this, CountingActivity.class);
            intent.putExtra("money",chargeListAdapter.money);
            startActivity(intent);
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
