package com.example.user.guokun.ui.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.user.guokun.R;


/**
 * Created by ywl on 2017/2/28.
 *
 */

public class PayDialog extends BaseDialog {
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private PayPwdEditText payPwdEditText;
    public TextView confirm;
    private String pwd = "";

    public PayDialog(Context context) {
        super(context);
    }

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(String data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_dialog_lyaout);
        payPwdEditText = findViewById(R.id.ppet);
        confirm = findViewById(R.id.tv_dialog_confirm);

        payPwdEditText.initStyle(R.drawable.edit_num_bg_red, 6, 0.33f, R.color.colorAccent, R.color.colorAccent, 20);
        payPwdEditText.setOnTextFinishListener(str -> pwd = str);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mOnItemClickListener.onItemClick(pwd);
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                payPwdEditText.setFocus();
            }
        }, 100);

    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
