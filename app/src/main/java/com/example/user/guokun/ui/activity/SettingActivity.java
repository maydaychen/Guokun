package com.example.user.guokun.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.user.guokun.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends InitActivity {
    private SharedPreferences mPreferences;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
    }

    @OnClick({R.id.iv_setting_back, R.id.rl_setting_about, R.id.bt_logout,R.id.rl_set_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting_back:
                finish();
                break;
            case R.id.rl_set_pwd:
                if (mPreferences.getInt("pay_pwd", 0) == 1) {
                    Toast.makeText(this, "您已设置密码，暂时无法修改！", Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(SettingActivity.this, SetPayPassActivity.class));
                }
                break;
            case R.id.rl_setting_about:
                break;
            case R.id.bt_logout:
                logout(this);
                break;
        }
    }

    public static void logout(Activity context) {
        new AlertDialog.Builder(context)
                .setTitle("国坤健康")
                .setMessage("确定要退出么？")
                .setPositiveButton("确定", (dialog, which) -> {
                    SharedPreferences mySharedPreferences = context.getSharedPreferences("user",
                            Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = mySharedPreferences.edit();
                    editor.putBoolean("autoLog", false);
                    if (editor.commit()) {
                        context.finish();
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                })
                .show();
    }
}
