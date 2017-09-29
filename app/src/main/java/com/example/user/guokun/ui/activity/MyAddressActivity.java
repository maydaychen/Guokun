package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.adapter.AllAddressAdapter;
import com.example.user.guokun.bean.AddressBean;
import com.example.user.guokun.http.HttpShopMethod;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;
import com.google.gson.Gson;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyAddressActivity extends InitActivity {
    private SubscriberOnNextListener<JSONObject> addressOnNext;
    private Gson gson = new Gson();
    private SharedPreferences preferences;

    @BindView(R.id.rv_item_address)
    RecyclerView mRvItemAddress;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_address);
        ButterKnife.bind(this);
        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        addressOnNext = jsonObject -> {
            if (jsonObject.getInt("statusCode") == 1) {
                Log.i("chenyi", "initView: " + jsonObject.toString());
                AddressBean addressBean = gson.fromJson(jsonObject.toString(), AddressBean.class);
                mRvItemAddress.setLayoutManager(new LinearLayoutManager(MyAddressActivity.this));
                AllAddressAdapter allAddressAdapter = new AllAddressAdapter(addressBean.getResult().getList());
                mRvItemAddress.setAdapter(allAddressAdapter);
                allAddressAdapter.setOnItemClickListener((view, data) -> {
                    if (getIntent().getBooleanExtra("ISCONFIRM", false)) {
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("address", addressBean.getResult().getList().get(data));
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
//                            Intent intent = new Intent(MyAddressActivity.this, ChangeAddressActivity.class);
//                            Bundle bundle = new Bundle();
//                            bundle.putSerializable("address", mAddressBean.getData().get(data1));
//                            intent.putExtras(bundle);
//                            startActivity(intent);
                    }
                });
            } else {
                Toast.makeText(MyAddressActivity.this, jsonObject.getString("result"), Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        HttpShopMethod.getInstance().get_address(
                new ProgressSubscriber(addressOnNext, MyAddressActivity.this),
                preferences.getString("access_token", ""), preferences.getString("sessionkey", ""));
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.iv_choose_doc_back, R.id.rl_my_address_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_choose_doc_back:
                finish();
                break;
            case R.id.rl_my_address_add:
                Intent intent = new Intent(MyAddressActivity.this, AddAddressActivity.class);
                startActivity(intent);
                break;
        }
    }
}
