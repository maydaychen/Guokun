package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.CheckInfoBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;
import com.example.user.guokun.ui.widget.CountDownView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaySuccessActivity extends InitActivity {
    @BindView(R.id.count)
    CountDownView mCount;
    private SubscriberOnNextListener<CheckInfoBean> ChairInfoOnNext;
    private SharedPreferences mPreferences;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pay_success);
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        ChairInfoOnNext = resultBean -> {
            switch (resultBean.getData().getIs_used()) {
                case 0:
                    HttpMethods.getInstance().check_info(new ProgressSubscriber(ChairInfoOnNext,
                            PaySuccessActivity.this), mPreferences.getString("token", ""), getIntent().getStringExtra("code"));
                    break;
                case 1:
                    mCount.initTime(0, 6, 0);
                    mCount.setOnTimeCompleteListener(() -> Toast.makeText(PaySuccessActivity.this, "计时完成!", Toast.LENGTH_LONG).show());
                    break;
                case 2:
                    Toast.makeText(this, "设备已离线！", Toast.LENGTH_SHORT).show();
                    break;
            }
        };
    }

    @Override
    public void initData() {
        HttpMethods.getInstance().check_info(new ProgressSubscriber(ChairInfoOnNext,
                PaySuccessActivity.this), mPreferences.getString("token", ""), getIntent().getStringExtra("code"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
