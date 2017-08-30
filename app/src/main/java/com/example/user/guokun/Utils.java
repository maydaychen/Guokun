package com.example.user.guokun;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.user.guokun.ui.activity.LoginActivity;

/**
 * Created by user on 2017/8/4.
 */

public class Utils {
    public static void logout(Activity context) {
        new AlertDialog.Builder(context)
                .setTitle("国坤健康")
                .setMessage("确定要推出么？")
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

    public static void qiangzhi_logout(Activity context) {
        new AlertDialog.Builder(context)
                .setTitle("警告")
                .setMessage("您的账号已在别处登录，请重新登录！")
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

    public static  int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
