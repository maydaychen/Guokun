package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.ChairInfoBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuigeActivity extends InitActivity {
    @BindView(R.id.ll_project1)
    LinearLayout mLlProject1;
    @BindView(R.id.ll_project2)
    LinearLayout mLlProject2;
    @BindView(R.id.ll_project3)
    LinearLayout mLlProject3;
    @BindView(R.id.tv_project1_name)
    TextView mTvProject1Name;
    @BindView(R.id.tv_project1)
    TextView mTvProject1;
    @BindView(R.id.tv_project2_name)
    TextView mTvProject2Name;
    @BindView(R.id.tv_project2)
    TextView mTvProject2;
    @BindView(R.id.tv_project3_name)
    TextView mTvProject3Name;
    @BindView(R.id.tv_project3)
    TextView mTvProject3;
    @BindView(R.id.tv_real_price)
    TextView mTvRealPrice;
    @BindView(R.id.bt_submit)
    Button mBtSubmit;
    private SubscriberOnNextListener<ChairInfoBean> ChairInfoOnNext;
    private SharedPreferences mPreferences;
    private ChairInfoBean mChairInfoBean;
    private int ID;
    private String price;
    private String type;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_guige);
        ButterKnife.bind(this);
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        ChairInfoOnNext = resultBean -> {
            ID = resultBean.getData().getAccountingList().get(0).getId();
            price = resultBean.getData().getAccountingList().get(0).getPrices() + "";
            type = resultBean.getData().getAccountingList().get(0).getName() + "";
            mChairInfoBean = resultBean;
            mTvProject1Name.setText(resultBean.getData().getAccountingList().get(0).getName());
            mTvProject2Name.setText(resultBean.getData().getAccountingList().get(1).getName());
            mTvProject3Name.setText(resultBean.getData().getAccountingList().get(2).getName());
            mTvProject1.setText(String.format(getResources().getString(R.string.project_price),
                    resultBean.getData().getAccountingList().get(0).getPrices() + "", resultBean.getData().getAccountingList().get(0).getTime_len() + ""));
            mTvProject2.setText(String.format(getResources().getString(R.string.project_price),
                    resultBean.getData().getAccountingList().get(1).getPrices() + "", resultBean.getData().getAccountingList().get(1).getTime_len() + ""));
            mTvProject3.setText(String.format(getResources().getString(R.string.project_price),
                    resultBean.getData().getAccountingList().get(2).getPrices() + "", resultBean.getData().getAccountingList().get(2).getTime_len() + ""));
            mTvRealPrice.setText(String.format(getResources().getString(R.string.price), resultBean.getData().getAccountingList().get(0).getPrices() + ""));
            mBtSubmit.setText(String.format(getResources().getString(R.string.submit), resultBean.getData().getAccountingList().get(0).getPrices() + ""));
        };
        mLlProject1.setOnClickListener(v -> {
            ID = mChairInfoBean.getData().getAccountingList().get(0).getId();
            type = mChairInfoBean.getData().getAccountingList().get(0).getName();
            price = mChairInfoBean.getData().getAccountingList().get(0).getPrices() + "";
            mTvRealPrice.setText(String.format(getResources().getString(R.string.price), mChairInfoBean.getData().getAccountingList().get(0).getPrices() + ""));
            mBtSubmit.setText(String.format(getResources().getString(R.string.submit), mChairInfoBean.getData().getAccountingList().get(0).getPrices() + ""));
        });
        mLlProject2.setOnClickListener(v -> {
            ID = mChairInfoBean.getData().getAccountingList().get(1).getId();
            type = mChairInfoBean.getData().getAccountingList().get(1).getName();
            price = mChairInfoBean.getData().getAccountingList().get(1).getPrices() + "";
            mTvRealPrice.setText(String.format(getResources().getString(R.string.price), mChairInfoBean.getData().getAccountingList().get(1).getPrices() + ""));
            mBtSubmit.setText(String.format(getResources().getString(R.string.submit), mChairInfoBean.getData().getAccountingList().get(1).getPrices() + ""));
        });
        mLlProject3.setOnClickListener(v -> {
            ID = mChairInfoBean.getData().getAccountingList().get(2).getId();
            type = mChairInfoBean.getData().getAccountingList().get(2).getName();
            price = mChairInfoBean.getData().getAccountingList().get(2).getPrices() + "";
            mTvRealPrice.setText(String.format(getResources().getString(R.string.price), mChairInfoBean.getData().getAccountingList().get(2).getPrices() + ""));
            mBtSubmit.setText(String.format(getResources().getString(R.string.submit), mChairInfoBean.getData().getAccountingList().get(2).getPrices() + ""));
        });
        HttpMethods.getInstance().char_info(new ProgressSubscriber(ChairInfoOnNext,
                GuigeActivity.this), mPreferences.getString("token", ""), getIntent().getStringExtra("code"));
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.iv_guige_back, R.id.bt_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_guige_back:
                finish();
                break;
            case R.id.bt_submit:
                Intent intent = new Intent(GuigeActivity.this, PayTypeActivity.class);
                intent.putExtra("id", ID);
                intent.putExtra("price", price);
                intent.putExtra("code", getIntent().getStringExtra("code"));
                intent.putExtra("type", type);
                startActivity(intent);
                break;
        }
    }
}
