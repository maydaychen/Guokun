package com.example.user.guokun.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.adapter.DiscovereyAdapter;
import com.example.user.guokun.bean.AccessTokenBean;
import com.example.user.guokun.bean.GoodsListBean;
import com.example.user.guokun.bean.ShopLoginBean;
import com.example.user.guokun.http.HttpShopMethod;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;
import com.example.user.guokun.ui.activity.GoodsDetailActivity;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/8/4.
 */

public class DiscoveryFragment extends Fragment {

    private SubscriberOnNextListener<JSONObject> getTokenOnNext;
    private SubscriberOnNextListener<JSONObject> loginOnNext;
    private SubscriberOnNextListener<JSONObject> goodsOnNext;
    private static final String EXTRA_CONTENT = "content";
    private static final int SHOW_SUBACTIVITY = 1;
    private List<String> list = new ArrayList<>();
    private Gson gson = new Gson();
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @BindView(R.id.rv_discovery)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_discovery, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static DiscoveryFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        DiscoveryFragment mallFragment = new DiscoveryFragment();
        mallFragment.setArguments(arguments);
        return mallFragment;
    }


    public void initData() {
        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = preferences.edit();

        goodsOnNext = jsonObject -> {
            if (jsonObject.getInt("statusCode") == 1) {
                GoodsListBean indexBean = gson.fromJson(jsonObject.toString(), GoodsListBean.class);
                DiscovereyAdapter goodsAdapter = new DiscovereyAdapter(indexBean.getResult(), getActivity());
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(goodsAdapter);
                goodsAdapter.setOnItemClickListener((view, data) -> {
                    Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                    intent.putExtra("id", indexBean.getResult().get(data).getId());
                    startActivity(intent);
                });
            } else {
                Toast.makeText(getActivity(), jsonObject.getString("result"), Toast.LENGTH_SHORT).show();
            }
        };
        loginOnNext = resultBean -> {
            if (resultBean.getInt("statusCode") == 1) {
                ShopLoginBean indexBean = gson.fromJson(resultBean.toString(), ShopLoginBean.class);
                editor.putString("sessionkey", indexBean.getResult().getSessionkey());
                editor.apply();
                HttpShopMethod.getInstance().good_list(
                        new ProgressSubscriber(goodsOnNext, getActivity()),
                        preferences.getString("access_token", ""), indexBean.getResult().getSessionkey());
            } else {
                Toast.makeText(getActivity(), resultBean.getString("result"), Toast.LENGTH_SHORT).show();
            }
        };
        getTokenOnNext = resultBean -> {
            switch (resultBean.getInt("statusCode")) {
                case 1:
                    AccessTokenBean indexBean = gson.fromJson(resultBean.toString(), AccessTokenBean.class);
                    editor.putString("access_token", indexBean.getResult().getAccess_token());
                    editor.putString("auth_key", indexBean.getResult().getAuth_key());
                    if (editor.commit()) {
                        HttpShopMethod.getInstance().login(
                                new ProgressSubscriber(loginOnNext, getActivity()),
                                preferences.getString("access_token", ""), preferences.getString("tele", ""));
                    }
                    break;
                case 10003:
                    Toast.makeText(getActivity(), "服务器错误，请稍后再试...", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getActivity(), resultBean.getString("result"), Toast.LENGTH_SHORT).show();
                    break;
            }
        };


    }

    public void initView() {
        HttpShopMethod.getInstance().get_token(
                new ProgressSubscriber(getTokenOnNext, getActivity()),
                "guokunjiankangkeji", "69534b32ab51f8cb802720d30fedbxxx");
    }

}