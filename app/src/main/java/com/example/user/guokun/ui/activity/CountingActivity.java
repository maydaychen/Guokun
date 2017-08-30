package com.example.user.guokun.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.user.guokun.MainActivity;
import com.example.user.guokun.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CountingActivity extends InitActivity {

    @BindView(R.id.tv_counting_money)
    TextView tvCountingMoney;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_counting);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        tvCountingMoney.setText(String.format(getResources().getString(R.string.price), getIntent().getIntExtra("money", 0) + ".00"));
        new Handler().postDelayed(() -> startActivity(new Intent(CountingActivity.this, MainActivity.class)), 3000);
    }

    @OnClick(R.id.iv_pay_back)
    public void onViewClicked() {
        startActivity(new Intent(CountingActivity.this, MainActivity.class));
    }
}
