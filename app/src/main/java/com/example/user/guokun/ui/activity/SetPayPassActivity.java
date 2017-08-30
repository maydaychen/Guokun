package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.user.guokun.MainActivity;
import com.example.user.guokun.R;
import com.example.user.guokun.bean.ResultBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;
import com.example.user.guokun.ui.widget.PayPwdEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetPayPassActivity extends InitActivity {

    @BindView(R.id.pay_pass)
    PayPwdEditText payPass;
    private SubscriberOnNextListener<ResultBean> LoginOnNext;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private String pwd;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_set_pay_pass);
        ButterKnife.bind(this);
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
        LoginOnNext = resultBean -> {
            if (resultBean.getCode() == 1) {
                Toast.makeText(this, "设置成功！", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(SetPayPassActivity.this, MainActivity.class));
                Intent intent = new Intent(SetPayPassActivity.this, MainActivity.class);
                mEditor.putInt("pay_pwd", 1);
                mEditor.apply();
                startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            } else {
                Toast.makeText(this, resultBean.getMag(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void initData() {
        payPass.initStyle(R.drawable.edit_num_bg, 6, 0.33f, R.color.font_colour_99, R.color.font_colour_99, 20);
        payPass.setOnTextFinishListener(str -> {//密码输入完后的回调
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
            pwd = str;
        });
    }

    @OnClick({R.id.tv_set_pwd_skip, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_set_pwd_skip:
                startActivity(new Intent(SetPayPassActivity.this, MainActivity.class));
                break;
            case R.id.tv_confirm:
                if (pwd.length() == 6) {
                    HttpMethods.getInstance().set_pass(
                            new ProgressSubscriber(LoginOnNext, SetPayPassActivity.this), mPreferences.getString("token", ""), pwd);
                } else {
                    Toast.makeText(this, "请输入完整支付密码！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
