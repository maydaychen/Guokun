package com.example.user.guokun.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.guokun.R;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SigninActivity extends InitActivity {

    @BindView(R.id.et_sign_telephone)
    EditText mEtSignTelephone;
    @BindView(R.id.cb_sign_read)
    CheckBox mCbSignRead;
    @BindView(R.id.tv_signin)
    TextView mTvSignin;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);
        RxCompoundButton.checkedChanges(mCbSignRead).subscribe(aBoolean -> {
            if (!aBoolean) {
                mTvSignin.setClickable(false);
                mTvSignin.setBackgroundColor(getResources().getColor(R.color.second_font));
            } else {
                mTvSignin.setClickable(true);
                mTvSignin.setBackgroundColor(getResources().getColor(R.color.font_red));
            }
        });
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_sign_cancel, R.id.tv_signin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sign_cancel:
                finish();
                break;
            case R.id.tv_signin:
                break;
        }
    }
}
