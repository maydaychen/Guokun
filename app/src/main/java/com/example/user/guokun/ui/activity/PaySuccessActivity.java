package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.CheckInfoBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;
import com.example.user.guokun.ui.widget.CountDownView;

import butterknife.BindView;

public class PaySuccessActivity extends InitActivity {
    @BindView(R.id.count)
    CountDownView mCount;
    private SubscriberOnNextListener<CheckInfoBean> ChairInfoOnNext;
    private SharedPreferences mPreferences;
    private String order_num;
    private ProgressSubscriber mProgressSubscriber;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pay_success);
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
                    mCount.initTime(0, resultBean.getData().getTime_len(), 0);
                    mCount.setOnTimeCompleteListener(() -> Toast.makeText(PaySuccessActivity.this, "计时完成!", Toast.LENGTH_LONG).show());
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
        if(!mProgressSubscriber.isUnsubscribed()){
            mProgressSubscriber.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public void initData() {
        order_num = getIntent().getStringExtra("order_num");
        HttpMethods.getInstance().check_info(mProgressSubscriber, mPreferences.getString("token", ""), order_num);
    }

}
