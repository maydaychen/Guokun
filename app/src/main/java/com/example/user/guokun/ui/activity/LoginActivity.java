package com.example.user.guokun.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guokun.MainActivity;
import com.example.user.guokun.R;
import com.example.user.guokun.Utils;
import com.example.user.guokun.bean.LoginBean;
import com.example.user.guokun.bean.ResultBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;
import com.jakewharton.rxbinding.widget.RxCompoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends InitActivity {

    @BindView(R.id.cb_read)
    CheckBox mCbRead;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_get_ems)
    TextView mTvGetEms;
    @BindView(R.id.et_login_tele)
    EditText mEtLoginTele;
    @BindView(R.id.et_yanzhengma)
    EditText mEtYanzhengma;
    private int recLen = 10;
    private boolean flag = true;
    private SubscriberOnNextListener<LoginBean> LoginOnNext;
    private SubscriberOnNextListener<ResultBean> ResultOnNext;

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

        LoginOnNext = resultBean -> {
            Toast.makeText(this, resultBean.getMag(), Toast.LENGTH_SHORT).show();
//            if (resultBean.getCode() == 1) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            }
        };

        ResultOnNext = resultBean -> Toast.makeText(this, resultBean.getMag(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void initData() {

    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (recLen >= 1) {
                recLen--;
                mTvGetEms.setText(recLen + "");
                handler.postDelayed(this, 1000);
            } else {
                flag = true;
                recLen = 10;
                mTvGetEms.setClickable(true);
                mTvGetEms.setText("获取验证码");
            }
        }
    };

    @OnClick({R.id.tv_get_ems, R.id.tv_login, R.id.tv_signin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_ems:
                String tele = mEtLoginTele.getText().toString();
                if (flag && Utils.isChinaPhoneLegal(tele)) {
                    flag = false;
                    mTvGetEms.setClickable(false);
                    handler.post(runnable);
                    HttpMethods.getInstance().send_code(
                            new ProgressSubscriber(ResultOnNext, LoginActivity.this), tele);
                } else {
                    Toast.makeText(LoginActivity.this, "请填写手机号！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_login:
                String tele1 = mEtLoginTele.getText().toString();
                HttpMethods.getInstance().login(
                        new ProgressSubscriber(LoginOnNext, LoginActivity.this), tele1, mEtYanzhengma.getText().toString());
                break;
            case R.id.tv_signin:
                startActivity(new Intent(LoginActivity.this, SigninActivity.class));
                break;
        }
    }

}
