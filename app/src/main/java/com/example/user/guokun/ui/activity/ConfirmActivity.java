package com.example.user.guokun.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.guokun.R;
import com.example.user.guokun.adapter.ConfirmPicAdapter;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ConfirmActivity extends InitActivity {

    private List<Map<String, Object>> pic_list = new ArrayList<>();
    private static final int SHOW_SUBACTIVITY = 1;

    @BindView(R.id.bt_confirm_commit)
    Button btConfirmCommit;
    @BindView(R.id.tv_confirm_name)
    TextView mTvConfirmName;
    @BindView(R.id.tv_confirm_telephone)
    TextView mTvConfirmTelephone;
    @BindView(R.id.tv_confirm_address)
    TextView mTvConfirmAddress;
    @BindView(R.id.tv_order_price)
    TextView mTvOrderPrice;
    @BindView(R.id.tv_ship_price)
    TextView mTvShipPrice;
    @BindView(R.id.tv_confirm_price)
    TextView mTvConfirmPrice;
    @BindView(R.id.tv_confirm_num)
    TextView mTvConfirmNum;
    @BindView(R.id.rv_confirm_showimg)
    RecyclerView mRvConfirmShowimg;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_confirm);
        ButterKnife.bind(this);

        RxView.clicks(btConfirmCommit)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(o -> {
                    startActivity(new Intent(ConfirmActivity.this, PayActivity.class));
                });
    }

    @Override
    public void initData() {
        mTvOrderPrice.setText(String.format(getResources().getString(R.string.price), "998"));
        mTvShipPrice.setText(String.format(getResources().getString(R.string.tv_tijian_price1), "0"));
        mTvConfirmPrice.setText(String.format(getResources().getString(R.string.price), "998"));
        mTvConfirmNum.setText(String.format(getResources().getString(R.string.tv_order_num), "1"));

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ConfirmActivity.this, 3, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRvConfirmShowimg.setLayoutManager(layoutManager);
        Map<String, Object> map = new HashMap<>();
        map.put("logo", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503506356816&di=5dadbd01e162deb6601a801dc6258361&imgtype=0&src=http%3A%2F%2Fimg1.bitautoimg.com%2Fautoalbum%2Ffiles%2F20170407%2F958%2F16325395873602_5454777_3.jpg%3Fr%3D20170703");
        pic_list.add(map);
        ConfirmPicAdapter confirmPicAdapter = new ConfirmPicAdapter(pic_list);
        mRvConfirmShowimg.setAdapter(confirmPicAdapter);
    }


    @OnClick({R.id.iv_comfirm_back, R.id.rl_confirm_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_comfirm_back:
                finish();
                break;
            case R.id.rl_confirm_address:
                Intent intent = new Intent(ConfirmActivity.this, MyAddressActivity.class);
                intent.putExtra("ISCONFIRM", true);
                startActivityForResult(intent, SHOW_SUBACTIVITY);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:

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
