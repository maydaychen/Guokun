package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guokun.MainActivity;
import com.example.user.guokun.R;
import com.example.user.guokun.bean.CheckInfoBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaySuccessActivity extends InitActivity {
    //    @BindView(R.id.count)
//    CountDownView mCount;
    @BindView(R.id.tv_paysuccess_money)
    TextView mTvPaysuccessMoney;
    @BindView(R.id.tv_minute)
    TextView tvMinute;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    private SubscriberOnNextListener<CheckInfoBean> ChairInfoOnNext;
    private SharedPreferences mPreferences;
    private String order_num;
    private ProgressSubscriber mProgressSubscriber;
    private int recLen = 0;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pay_success);
        ButterKnife.bind(this);
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        ChairInfoOnNext = resultBean -> {
            Toast.makeText(this, resultBean.getMag(), Toast.LENGTH_SHORT).show();
            switch (resultBean.getCode()) {
                case 0:
                    new Handler().postDelayed(() -> {
                        HttpMethods.getInstance().check_info(new ProgressSubscriber(ChairInfoOnNext,
                                PaySuccessActivity.this), mPreferences.getString("token", ""), order_num);
                    }, 1500);
                    break;
                case 1:
                    recLen = resultBean.getData().getTime_len() * 60;
//                    mCount.initTime(0, resultBean.getData().getTime_len(), 0);
                    mTvPaysuccessMoney.setText(String.format(getResources().getString(R.string.price), resultBean.getData().getPrices() + ""));
                    handler.post(runnable);
//                    mCount.setOnTimeCompleteListener(() -> startActivity(new Intent(PaySuccessActivity.this, MainActivity.class)));
//                    mCount.start();
                    break;
                case 2:
                    Toast.makeText(this, "设备已离线！", Toast.LENGTH_SHORT).show();
                    break;
            }
        };
        mProgressSubscriber = new ProgressSubscriber(ChairInfoOnNext, PaySuccessActivity.this);
    }

    @Override
    protected void onDestroy() {
        if (!mProgressSubscriber.isUnsubscribed()) {
            mProgressSubscriber.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public void initData() {
        order_num = getIntent().getStringExtra("order_num");
        HttpMethods.getInstance().check_info(mProgressSubscriber, mPreferences.getString("token", ""), order_num);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(PaySuccessActivity.this, MainActivity.class));
        }
        return false;
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (recLen >= 1) {
                tvMinute.setText(recLen / 60 < 10 ? "0" + recLen / 60 : recLen / 60 + "");
                tvSecond.setText(recLen % 60 < 10 ? "0" + recLen % 60 : recLen % 60 + "");
                recLen--;
                handler.postDelayed(this, 1000);
            } else {
                startActivity(new Intent(PaySuccessActivity.this, MainActivity.class));
            }
        }
    };

    @OnClick(R.id.iv_pay_back)
    public void onViewClicked() {
        startActivity(new Intent(PaySuccessActivity.this, MainActivity.class));
    }


}
