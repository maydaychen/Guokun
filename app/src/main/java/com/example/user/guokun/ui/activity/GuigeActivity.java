package com.example.user.guokun.ui.activity;

import android.app.AlertDialog;
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
import com.example.user.guokun.ui.widget.PayDialog;
import com.example.user.guokun.wxapi.pay.WXPayEntry;
import com.example.user.guokun.wxapi.pay.WXPayMessage;
import com.example.user.guokun.wxapi.pay.WXUtils;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.text.DecimalFormat;

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

    private DecimalFormat df = new DecimalFormat("######0.00");
    private SubscriberOnNextListener<ChairInfoBean> ChairInfoOnNext;
    private SubscriberOnNextListener<JSONObject> PayOnNext;
    private SharedPreferences mPreferences;
    private ChairInfoBean mChairInfoBean;
    private int ID;
    //优惠券减免费用
    private double price;
    //实际费用
    private double real_price;
    //当前套餐价格
    private double current_price;
    private double temp_price;
    private String PAY_TYPE = "";
    private String order_num;
    private String coupon_id;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guige);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        ChairInfoOnNext = resultBean -> {
            ID = resultBean.getData().getCosts().get(0).getId();
            mChairInfoBean = resultBean;
            mTvProject1Name.setText(resultBean.getData().getCosts().get(0).getName());
            mTvProject2Name.setText(resultBean.getData().getCosts().get(1).getName());
            mTvProject3Name.setText(resultBean.getData().getCosts().get(2).getName());
            mTvProject1.setText(String.format(getResources().getString(R.string.project_price),
                    resultBean.getData().getCosts().get(0).getPrice() + "", resultBean.getData().getCosts().get(0).getTime_len() + ""));
            mTvProject2.setText(String.format(getResources().getString(R.string.project_price),
                    resultBean.getData().getCosts().get(1).getPrice() + "", resultBean.getData().getCosts().get(1).getTime_len() + ""));
            mTvProject3.setText(String.format(getResources().getString(R.string.project_price),
                    resultBean.getData().getCosts().get(2).getPrice() + "", resultBean.getData().getCosts().get(2).getTime_len() + ""));
            mTvRealPrice.setText(String.format(getResources().getString(R.string.price), resultBean.getData().getCosts().get(0).getPrice() + ""));
            mBtSubmit.setText(String.format(getResources().getString(R.string.submit), resultBean.getData().getCosts().get(0).getPrice() + ""));

            mLlProject1.setOnFocusChangeListener((view, b) -> {
                if (b) {
                    ID = mChairInfoBean.getData().getCosts().get(0).getId();
                    current_price = mChairInfoBean.getData().getCosts().get(0).getPrice();
                    real_price = current_price - price;
                    mTvRealPrice.setText(String.format(getResources().getString(R.string.price), real_price > 0 ? real_price + "" : "0"));
                    mBtSubmit.setText(String.format(getResources().getString(R.string.submit), real_price > 0 ? real_price + "" : "0"));
                }
            });
            mLlProject2.setOnFocusChangeListener((view, b) -> {
                if (b) {
                    ID = mChairInfoBean.getData().getCosts().get(1).getId();
                    current_price = mChairInfoBean.getData().getCosts().get(1).getPrice();
                    real_price = current_price - price;
                    mTvRealPrice.setText(String.format(getResources().getString(R.string.price), real_price > 0 ? real_price + "" : "0"));
                    mBtSubmit.setText(String.format(getResources().getString(R.string.submit), real_price > 0 ? real_price + "" : "0"));
                }
            });
            mLlProject3.setOnFocusChangeListener((view, b) -> {
                if (b) {
                    ID = mChairInfoBean.getData().getCosts().get(2).getId();
                    current_price = mChairInfoBean.getData().getCosts().get(2).getPrice();
                    real_price = current_price - price;
                    mTvRealPrice.setText(String.format(getResources().getString(R.string.price), real_price > 0 ? real_price + "" : "0"));
                    mBtSubmit.setText(String.format(getResources().getString(R.string.submit), real_price > 0 ? real_price + "" : "0"));
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
            if (jsonObject.getInt("status") == 1) {
                order_num = jsonObject.getJSONObject("data").getString("orderNo");
                if (jsonObject.getJSONObject("data").getDouble("payment") == 0) {
                    Intent intent = new Intent(GuigeActivity.this, PaySuccessActivity.class);
                    intent.putExtra("order_num", order_num);
                    startActivity(intent);
                } else {
                    switch (PAY_TYPE) {
                        case "0":
                            if (jsonObject.getString("message").equals("支付成功")) {
                                Intent intent = new Intent(GuigeActivity.this, PaySuccessActivity.class);
                                intent.putExtra("order_num", order_num);
                                startActivity(intent);
                            } else {
                                Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
                    if (PAY_TYPE.equals("0")) {
                        if (mPreferences.getInt("pay_pwd", 0) == 1) {
                            PayDialog payDialog = new PayDialog(GuigeActivity.this);
                            payDialog.show();
                            payDialog.setOnItemClickListener(data -> HttpJsonMethod.getInstance().pay(new ProgressSubscriber(PayOnNext,
                                            GuigeActivity.this), mPreferences.getString("token", ""),
                                    ID, getIntent().getStringExtra("code"), PAY_TYPE, coupon_id, data));
                        } else {
                            new AlertDialog.Builder(GuigeActivity.this).setTitle("国坤健康")
                                    .setMessage("未设置支付密码！请设置")
                                    .setPositiveButton("确定", (dialog, which) -> startActivity(new Intent(GuigeActivity.this, SetPayPassActivity.class))).show();
                        }
                    } else {
                        HttpJsonMethod.getInstance().pay(new ProgressSubscriber(PayOnNext,
                                        GuigeActivity.this), mPreferences.getString("token", ""),
                                ID, getIntent().getStringExtra("code"), PAY_TYPE, coupon_id, "");
                    }
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
            if (data.getBooleanExtra("use", true)) {
                CouponBean.DataBean.ListBean dataBean = (CouponBean.DataBean.ListBean) data.getSerializableExtra("objs");
                tvCoupon.setText("-" + dataBean.getMoney() + "");
                tvCoupon.setTextColor(getResources().getColor(R.color.font_red));
                coupon_id = dataBean.getId() + "";
                price = dataBean.getMoney();
                real_price = current_price - price;
                mTvRealPrice.setText(String.format(getResources().getString(R.string.price), real_price > 0 ? df.format(real_price): "0"));
                mBtSubmit.setText(String.format(getResources().getString(R.string.submit), real_price > 0 ? real_price + "" : "0"));
            } else {
                coupon_id = "";
                tvCoupon.setText("使用优惠券");
                tvCoupon.setTextColor(getResources().getColor(R.color.second_font));
                price = 0.00;
                real_price = current_price - price;
                mTvRealPrice.setText(String.format(getResources().getString(R.string.price), real_price > 0 ? real_price + "" : "0"));
                mBtSubmit.setText(String.format(getResources().getString(R.string.submit), real_price > 0 ? real_price + "" : "0"));
            }
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
