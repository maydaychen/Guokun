package com.example.user.guokun.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.guokun.R;

import butterknife.ButterKnife;

/**
 * Created by user on 2017/8/4.
 */

public class DiscoveryFragment extends Fragment {

    private static final String EXTRA_CONTENT = "content";
    private static final int SHOW_SUBACTIVITY = 1;

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

    }

    public void initView() {

    }

}