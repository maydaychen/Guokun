package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.alipay.AliPayManager;
import com.example.user.guokun.alipay.AliPayMessage;
import com.example.user.guokun.bean.ChairInfoBean;
import com.example.user.guokun.bean.CouponBean;
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

public class GuigeActivity extends InitActivity {
    @BindView(R.id.ll_project1)
    LinearLayout mLlProject1;
    @BindView(R.id.ll_project2)
    LinearLayout mLlProject2;
    @BindView(R.id.ll_project3)
    LinearLayout mLlProject3;
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
    @BindView(R.id.tv_real_price)
    TextView mTvRealPrice;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;

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
    @BindView(R.id.tv_coupon)
    TextView tvCoupon;

    private SubscriberOnNextListener<ChairInfoBean> ChairInfoOnNext;
    private SubscriberOnNextListener<JSONObject> PayOnNext;
    private SharedPreferences mPreferences;
    private ChairInfoBean mChairInfoBean;
    private int ID;
    private String price;
    private String type;
    private String PAY_TYPE = "";
    private String order_num;
    private int coupon_id;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guige);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        ChairInfoOnNext = resultBean -> {
            ID = resultBean.getData().getAccountingList().get(0).getId();
            price = resultBean.getData().getAccountingList().get(0).getPrices() + "";
            type = resultBean.getData().getAccountingList().get(0).getName() + "";
            mChairInfoBean = resultBean;
            mTvProject1Name.setText(resultBean.getData().getAccountingList().get(0).getName());
            mTvProject2Name.setText(resultBean.getData().getAccountingList().get(1).getName());
            mTvProject3Name.setText(resultBean.getData().getAccountingList().get(2).getName());
            mTvProject1.setText(String.format(getResources().getString(R.string.project_price),
                    resultBean.getData().getAccountingList().get(0).getPrices() + "", resultBean.getData().getAccountingList().get(0).getTime_len() + ""));
            mTvProject2.setText(String.format(getResources().getString(R.string.project_price),
                    resultBean.getData().getAccountingList().get(1).getPrices() + "", resultBean.getData().getAccountingList().get(1).getTime_len() + ""));
            mTvProject3.setText(String.format(getResources().getString(R.string.project_price),
                    resultBean.getData().getAccountingList().get(2).getPrices() + "", resultBean.getData().getAccountingList().get(2).getTime_len() + ""));
            mTvRealPrice.setText(String.format(getResources().getString(R.string.price), resultBean.getData().getAccountingList().get(0).getPrices() + ""));
            mBtSubmit.setText(String.format(getResources().getString(R.string.submit), resultBean.getData().getAccountingList().get(0).getPrices() + ""));

            mLlProject1.setOnFocusChangeListener((view, b) -> {
                if (b) {
                    ID = mChairInfoBean.getData().getAccountingList().get(0).getId();
                    type = mChairInfoBean.getData().getAccountingList().get(0).getName();
                    price = mChairInfoBean.getData().getAccountingList().get(0).getPrices() + "";
                    mTvRealPrice.setText(String.format(getResources().getString(R.string.price), mChairInfoBean.getData().getAccountingList().get(0).getPrices() + ""));
                    mBtSubmit.setText(String.format(getResources().getString(R.string.submit), mChairInfoBean.getData().getAccountingList().get(0).getPrices() + ""));
                }
            });
            mLlProject2.setOnFocusChangeListener((view, b) -> {
                if (b) {
                    ID = mChairInfoBean.getData().getAccountingList().get(1).getId();
                    type = mChairInfoBean.getData().getAccountingList().get(1).getName();
                    price = mChairInfoBean.getData().getAccountingList().get(1).getPrices() + "";
                    mTvRealPrice.setText(String.format(getResources().getString(R.string.price), mChairInfoBean.getData().getAccountingList().get(1).getPrices() + ""));
                    mBtSubmit.setText(String.format(getResources().getString(R.string.submit), mChairInfoBean.getData().getAccountingList().get(1).getPrices() + ""));
                }
            });
            mLlProject3.setOnFocusChangeListener((view, b) -> {
                if (b) {
                    ID = mChairInfoBean.getData().getAccountingList().get(2).getId();
                    type = mChairInfoBean.getData().getAccountingList().get(2).getName();
                    price = mChairInfoBean.getData().getAccountingList().get(2).getPrices() + "";
                    mTvRealPrice.setText(String.format(getResources().getString(R.string.price), mChairInfoBean.getData().getAccountingList().get(2).getPrices() + ""));
                    mBtSubmit.setText(String.format(getResources().getString(R.string.submit), mChairInfoBean.getData().getAccountingList().get(2).getPrices() + ""));
                }
            });
        };

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
                order_num = jsonObject.getJSONObject("data").getString("outTradeNo");
                if (jsonObject.getJSONObject("data").getDouble("money")==0) {

                }else {
                    switch (PAY_TYPE) {
                        case "0":
                            if (jsonObject.getString("mag").equals("支付成功")) {
                                Intent intent = new Intent(GuigeActivity.this, PaySuccessActivity.class);
                                intent.putExtra("order_num", order_num);
                                startActivity(intent);
                            } else {

                            }
                            break;
                        case "1":
                            WXPayEntry entry = WXUtils.parseWXData(jsonObject.getString("data"));
                            WXUtils.startWeChat(GuigeActivity.this, entry);
                            break;
                        case "2":
                            AliPayManager.getInstance().payV2(GuigeActivity.this, jsonObject.getJSONObject("data").getString("result"));
                            break;

                    }
                }

            } else {
                Toast.makeText(this, jsonObject.getString("mag"), Toast.LENGTH_SHORT).show();
            }
        };

        HttpMethods.getInstance().char_info(new ProgressSubscriber(ChairInfoOnNext,
                GuigeActivity.this), mPreferences.getString("token", ""), getIntent().getStringExtra("code"));
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.iv_guige_back, R.id.bt_submit, R.id.rl_use_coupon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_guige_back:
                finish();
                break;
            case R.id.bt_submit:
//                Intent intent = new Intent(GuigeActivity.this, PayTypeActivity.class);
//                intent.putExtra("id", ID);
//                intent.putExtra("price", price);
//                intent.putExtra("code", getIntent().getStringExtra("code"));
//                intent.putExtra("type", type);
//                startActivity(intent);
                if (PAY_TYPE.equals("")) {
                    Toast.makeText(this, "请选择支付方式！", Toast.LENGTH_SHORT).show();
                } else {
                    HttpJsonMethod.getInstance().pay(new ProgressSubscriber(PayOnNext,
                                    GuigeActivity.this), mPreferences.getString("token", ""),
                            ID, getIntent().getStringExtra("code"), PAY_TYPE);
                }
                break;
            case R.id.rl_use_coupon:
                Intent intent = new Intent(GuigeActivity.this, ChooseCouponActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            CouponBean.DataBean dataBean = (CouponBean.DataBean) data.getSerializableExtra("objs");
            tvCoupon.setText(dataBean.getMoney() + "");
            tvCoupon.setTextColor(getResources().getColor(R.color.font_red));
        }

    }

    //EventBus阿里支付结果回调事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(AliPayMessage payMessage) {
        if (payMessage.errorCode == 0) {
            Intent intent = new Intent(GuigeActivity.this, PaySuccessActivity.class);
            intent.putExtra("order_num", order_num);
            startActivity(intent);
        } else {
            Toast.makeText(this, payMessage.result, Toast.LENGTH_SHORT).show();
        }
    }

    //EventBus微信支付结果回调事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(WXPayMessage payMessage) {
        if (payMessage.errorCode == 0) {
            Intent intent = new Intent(GuigeActivity.this, PaySuccessActivity.class);
            intent.putExtra("order_num", order_num);
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
