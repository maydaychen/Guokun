package com.example.user.guokun.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.user.guokun.MainActivity;
import com.example.user.guokun.R;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends InitActivity {

    @BindView(R.id.cb_read)
    CheckBox mCbRead;
    @BindView(R.id.tv_login)
    TextView mTvLogin;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        RxCompoundButton.checkedChanges(mCbRead).subscribe(aBoolean -> {
            if (!aBoolean) {
                mTvLogin.setClickable(false);
                mTvLogin.setBackgroundColor(getResources().getColor(R.color.second_font));
            } else {
                mTvLogin.setClickable(true);
                mTvLogin.setBackgroundColor(getResources().getColor(R.color.font_red));
            }
        });

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.tv_get_ems, R.id.tv_login, R.id.tv_signin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_ems:
                break;
            case R.id.tv_login:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;
            case R.id.tv_signin:
                startActivity(new Intent(LoginActivity.this, SigninActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
