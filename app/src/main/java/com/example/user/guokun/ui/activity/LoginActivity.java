package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guokun.MainActivity;
import com.example.user.guokun.R;
import com.example.user.guokun.bean.LoginBean;
import com.example.user.guokun.bean.ResultBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.user.guokun.StringUtils.isChinaPhoneLegal;

public class LoginActivity extends InitActivity {

    @BindView(R.id.cb_read)
    CheckBox mCbRead;
    @BindView(R.id.tv_login)
    Button mTvLogin;
    @BindView(R.id.tv_get_ems)
    TextView mTvGetEms;
    @BindView(R.id.et_login_tele)
    EditText mEtLoginTele;
    @BindView(R.id.et_yanzhengma)
    EditText mEtYanzhengma;
    private int recLen = 60;
    private boolean flag = true;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private SubscriberOnNextListener<LoginBean> LoginOnNext;
    private SubscriberOnNextListener<ResultBean> ResultOnNext;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        RxCompoundButton.checkedChanges(mCbRead).subscribe(aBoolean -> {
            if (!aBoolean) {
                mTvLogin.setClickable(false);
                mTvLogin.setBackgroundResource(R.drawable.boder_grey);
            } else {
                mTvLogin.setClickable(true);
                mTvLogin.setBackgroundResource(R.drawable.boder_red);
            }
        });

        LoginOnNext = resultBean -> {
            if (resultBean.getStatus() == 1) {
                Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();

                //0未设置 1已设密码
                mEditor.putInt("pay_pwd", resultBean.getData().getTraded());
                if (resultBean.getData().getTraded() == 0) {
                    mEditor.putString("token", resultBean.getData().getAccessToken().getAccess_token());
                    mEditor.putBoolean("autoLog", true);
                    if (mEditor.commit()) {
                        startActivity(new Intent(LoginActivity.this, SetPayPassActivity.class));
                    }
                } else {
                    mEditor.putString("token", resultBean.getData().getAccessToken().getAccess_token());
                    mEditor.putBoolean("autoLog", true);
                    mEditor.apply();
                    finish();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            } else {
                Toast.makeText(this, resultBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        ResultOnNext = resultBean -> Toast.makeText(this, resultBean.getMessage(), Toast.LENGTH_SHORT).show();

        RxTextView.textChanges(mEtLoginTele).subscribe(charSequence -> {
            if (charSequence.length() == 11) {
                mEtYanzhengma.requestFocus();
            }
        });
        RxTextView.textChanges(mEtYanzhengma).subscribe(charSequence -> {
            if (charSequence.length() == 6) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
            }
        });

    }

    @Override
    public void initData() {
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
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
                recLen = 60;
                mTvGetEms.setClickable(true);
                mTvGetEms.setText("获取验证码");
            }
        }
    };

    @OnClick({R.id.tv_get_ems, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_ems:
                String tele = mEtLoginTele.getText().toString();
                if (flag && isChinaPhoneLegal(tele)) {
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
//            case R.id.tv_signin:
//                startActivity(new Intent(LoginActivity.this, SigninActivity.class));
//                break;
        }
    }

}
