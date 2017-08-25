package com.example.user.guokun.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.guokun.R;
import com.example.user.guokun.adapter.DiscovereyAdapter;
import com.example.user.guokun.ui.activity.GoodsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/8/4.
 */

public class DiscoveryFragment extends Fragment {

    private static final String EXTRA_CONTENT = "content";
    private static final int SHOW_SUBACTIVITY = 1;
    private List<String> list = new ArrayList<>();
    @BindView(R.id.rv_discovery)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_discovery, container, false);
        ButterKnife.bind(this, root);
        initData();
        initView();
        return root;
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
        list.add("http://pic.58pic.com/58pic/13/76/61/33N58PICRdp_1024.jpg");
        list.add("http://pic.58pic.com/58pic/13/76/61/33N58PICRdp_1024.jpg");
        DiscovereyAdapter goodsAdapter = new DiscovereyAdapter(list, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(goodsAdapter);
        goodsAdapter.setOnItemClickListener((view, data) -> {
            Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
            startActivity(intent);
        });
    }

    public void initView() {

    }

}