package com.example.user.guokun.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.user.guokun.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PurseActivity extends InitActivity {

    @BindView(R.id.cb_purse_ali)
    CheckBox mCbPurseAli;
    @BindView(R.id.cb_purse_wechat)
    CheckBox mCbPurseWechat;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_purse);
        ButterKnife.bind(this);

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.iv_purse_back, R.id.rl_ali, R.id.rl_wechat, R.id.bt_chongzhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_purse_back:
                finish();
                break;
            case R.id.rl_ali:
                break;
            case R.id.rl_wechat:
                break;
            case R.id.bt_logout:
                break;
        }
    }
}
