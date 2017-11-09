package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.adapter.ConfirmPicAdapter;
import com.example.user.guokun.bean.AddressBean;
import com.example.user.guokun.bean.BuyNowBean;
import com.example.user.guokun.bean.ConfirmBean;
import com.example.user.guokun.http.HttpShopMethod;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;
import com.google.gson.Gson;
import com.jakewharton.rxbinding.view.RxView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ConfirmActivity extends InitActivity {
    private Gson gson = new Gson();
    private List<Map<String, Object>> pic_list = new ArrayList<>();
    private SubscriberOnNextListener<JSONObject> confirmOnNext;
    private SharedPreferences preferences;
    private static final int SHOW_SUBACTIVITY = 1;
    private BuyNowBean mBuyNowBean;
    private String addressId;
    private String dispatchid;

    @BindView(R.id.tv_confirm_sname)
    TextView mTvConfirmSname;
    @BindView(R.id.tv_add_address)
    TextView mTvAddAddress;
    @BindView(R.id.rl_confirm_address)
    RelativeLayout mRlConfirmAddress;
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

        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        Log.i("chenyi", "initData: " + getIntent().getStringExtra("objs"));
        mBuyNowBean = gson.fromJson(getIntent().getStringExtra("objs"), BuyNowBean.class);
        confirmOnNext = jsonObject -> {
            if (jsonObject.getInt("statusCode") == 1) {
                Log.i("chenyi", "initView: " + jsonObject.toString());
                ConfirmBean confirmBean = gson.fromJson(jsonObject.toString(), ConfirmBean.class);
                Intent intent = new Intent(ConfirmActivity.this, PayActivity.class);
                intent.putExtra("price", mBuyNowBean.getResult().getMemberDiscount().getRealprice() + "");
                intent.putExtra("order_num", confirmBean.getResult().getOrdersn());
                startActivity(intent);
            } else {
                Toast.makeText(ConfirmActivity.this, jsonObject.getString("result"), Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void initData() {
        RxView.clicks(btConfirmCommit)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(o -> {
                    HttpShopMethod.getInstance().confirm_order(
                            new ProgressSubscriber(confirmOnNext, ConfirmActivity.this),
                            preferences.getString("access_token", ""), preferences.getString("sessionkey", ""), mBuyNowBean.getResult().getOrderGoods().get(0).getGoodsid(),
                            addressId, mBuyNowBean.getResult().getOrderGoods().get(0).getTotal() + "");
                });

        if (null != mBuyNowBean.getResult().getDefaultAddress()) {
            mRlConfirmAddress.setVisibility(View.VISIBLE);
            mTvAddAddress.setVisibility(View.GONE);
            addressId = mBuyNowBean.getResult().getDispatches().get(0).getId();
            dispatchid = mBuyNowBean.getResult().getDispatches().get(0).getId();
            mTvConfirmName.setText(mBuyNowBean.getResult().getDefaultAddress().getRealname());
            mTvConfirmTelephone.setText(mBuyNowBean.getResult().getDefaultAddress().getMobile());
            mTvConfirmAddress.setText(mBuyNowBean.getResult().getDefaultAddress().getProvince() + mBuyNowBean.getResult().getDefaultAddress().getCity() +
                    mBuyNowBean.getResult().getDefaultAddress().getArea() + mBuyNowBean.getResult().getDefaultAddress().getAddress());
        } else {
            mTvAddAddress.setVisibility(View.VISIBLE);
            mRlConfirmAddress.setVisibility(View.GONE);
        }
        mTvConfirmSname.setText(mBuyNowBean.getResult().getOrderGoods().get(0).getTitle());
        mTvOrderPrice.setText(String.format(getResources().getString(R.string.price), mBuyNowBean.getResult().getMemberDiscount().getRealprice() + ""));
        mTvConfirmPrice.setText(String.format(getResources().getString(R.string.price), mBuyNowBean.getResult().getMemberDiscount().getRealprice() + ""));
        mTvConfirmNum.setText(String.format(getResources().getString(R.string.tv_order_num), mBuyNowBean.getResult().getOrderGoods().get(0).getTotal() + ""));

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ConfirmActivity.this, 3, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRvConfirmShowimg.setLayoutManager(layoutManager);
        Map<String, Object> map = new HashMap<>();
        map.put("logo", mBuyNowBean.getResult().getOrderGoods().get(0).getThumb());
        pic_list.add(map);
        ConfirmPicAdapter confirmPicAdapter = new ConfirmPicAdapter(pic_list);
        mRvConfirmShowimg.setAdapter(confirmPicAdapter);

        mTvAddAddress.setOnClickListener(v -> {
            Intent intent = new Intent(ConfirmActivity.this, MyAddressActivity.class);
            intent.putExtra("ISCONFIRM", true);
            startActivityForResult(intent, SHOW_SUBACTIVITY);
        });
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
//                HttpShopMethod.getInstance().get_address_price(
//                        new ProgressSubscriber(addressOnNext, ConfirmActivity.this),
//                        preferences.getString("access_token", ""), preferences.getString("sessionkey", ""), mBuyNowBean.getResult().getOrderGoods().get(0).getGoodsid(),
//                        data.getStringExtra("said"), mBuyNowBean.getResult().getOrderGoods().get(0).getTotal() + "");
                if (data != null) {
                    mRlConfirmAddress.setVisibility(View.VISIBLE);
                    mTvAddAddress.setVisibility(View.GONE);
                    AddressBean.ResultBean.ListBean addressBean = (AddressBean.ResultBean.ListBean) data.getSerializableExtra("address");
                    addressId = addressBean.getId();
                    mTvConfirmName.setText(addressBean.getRealname());
                    mTvConfirmTelephone.setText(addressBean.getMobile());
                    mTvConfirmAddress.setText(addressBean.getProvince() + addressBean.getCity() +
                            addressBean.getArea() + addressBean.getAddress());
                }
                break;
        }
    }
}
