package com.example.user.guokun.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.example.user.guokun.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionActivity extends InitActivity {

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.iv_coupon_detail_back,  R.id.rl_sao_question, R.id.rl_pay_question, R.id.rl_order_question, R.id.rl_account_question})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_coupon_detail_back:
                finish();
                break;
            case R.id.rl_sao_question:
                break;
            case R.id.rl_pay_question:
                break;
            case R.id.rl_order_question:
                break;
            case R.id.rl_account_question:
                break;
        }
    }
}
