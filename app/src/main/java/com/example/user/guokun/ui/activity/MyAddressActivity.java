package com.example.user.guokun.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.user.guokun.R;
import com.example.user.guokun.adapter.AllAddressAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyAddressActivity extends InitActivity {

    @BindView(R.id.rv_item_address)
    RecyclerView mRvItemAddress;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_address);
        ButterKnife.bind(this);


    }

    @Override
    public void initData() {
        List<Map<String, Object>> listmaps = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "陈译");
        map.put("mobile", "13665137658");
        map.put("address", "无锡市滨湖区锦溪路100号");
        map.put("default", "11");
        listmaps.add(map);

        mRvItemAddress.setLayoutManager(new LinearLayoutManager(this));
        AllAddressAdapter allAddressAdapter = new AllAddressAdapter(listmaps);
        mRvItemAddress.setAdapter(allAddressAdapter);
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
