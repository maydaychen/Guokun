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

    @OnClick({R.id.iv_coupon_detail_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_coupon_detail_back:
                finish();
                break;
        }
    }
}
