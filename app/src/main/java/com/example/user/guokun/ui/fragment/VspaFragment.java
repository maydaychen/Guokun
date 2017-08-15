package com.example.user.guokun.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.guokun.R;
import com.example.user.guokun.adapter.ChargeListAdapter;
import com.example.user.guokun.bean.VspaBean;
import com.example.user.guokun.http.SubscriberOnNextListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/8/12.
 */

public class VspaFragment extends Fragment {
    @BindView(R.id.rv_vspa_order)
    RecyclerView mRvVspaOrder;
    private SubscriberOnNextListener<VspaBean> VspaOnNext;
    private static final String EXTRA_CONTENT = "content";
    private static final int SHOW_SUBACTIVITY = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vspa, container, false);
        ButterKnife.bind(this, root);
        initData();
        initView();
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static VspaFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);

        VspaFragment mallFragment = new VspaFragment();
        mallFragment.setArguments(arguments);

        return mallFragment;
    }


    public void initData() {
        VspaOnNext = resultBean -> {
            if (resultBean.getCode() == 1) {
//                mRvVspaOrder.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//                ChargeListAdapter chargeListAdapter = new ChargeListAdapter(resultBean.getData());
//                mRvVspaOrder.setAdapter(chargeListAdapter);
            }
        };
    }

    public void initView() {

    }
}