package com.example.user.guokun.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.adapter.VspaAdapter;
import com.example.user.guokun.bean.VspaBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressErrorSubscriber;
import com.example.user.guokun.http.SubscriberOnNextAndErrorListener;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/8/12.
 */

public class VspaFragment extends Fragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    @BindView(R.id.rv_vspa_order)
    PullLoadMoreRecyclerView mRvVspaOrder;
    private SubscriberOnNextAndErrorListener<VspaBean> VspaOnNext;
    private static final String EXTRA_CONTENT = "content";
    private static final int SHOW_SUBACTIVITY = 1;
    private SharedPreferences mPreferences;
    private VspaAdapter mRecyclerViewAdapter;
    private int page = 0;
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
        mPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        VspaOnNext = new SubscriberOnNextAndErrorListener<VspaBean>() {
            @Override
            public void onNext(VspaBean vspaBean) {
                mRvVspaOrder.setVisibility(View.VISIBLE);
                try {
                        mRvVspaOrder.setPullLoadMoreCompleted();
                        mRecyclerViewAdapter.clearData();
                        mRecyclerViewAdapter.notifyDataSetChanged();
                        mRvVspaOrder.scrollToTop();
                        mRecyclerViewAdapter.addAllData(vspaBean.getData().getList());

                } catch (NullPointerException e) {
                    if (page != 0) {
                        Toast.makeText(getActivity(), "已经加载完毕！", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                mRvVspaOrder.setVisibility(View.VISIBLE);
                mRvVspaOrder.setPullLoadMoreCompleted();
            }
        };

        RecyclerView recyclerView = mRvVspaOrder.getRecyclerView();
        recyclerView.setVerticalScrollBarEnabled(true);
        mRvVspaOrder.setRefreshing(false);
        mRvVspaOrder.setPullRefreshEnable(true);
        mRvVspaOrder.setPushRefreshEnable(true);
        mRvVspaOrder.setFooterViewText("正在加载，请稍后");
        mRvVspaOrder.setFooterViewTextColor(R.color.second_font);
        mRvVspaOrder.setFooterViewBackgroundColor(R.color.order_e7);
        mRvVspaOrder.setLinearLayout();

        mRvVspaOrder.setOnPullLoadMoreListener(this);
        mRvVspaOrder.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.empty_view, null));
        mRecyclerViewAdapter = new VspaAdapter(getActivity());
        mRvVspaOrder.setAdapter(mRecyclerViewAdapter);

        getData1();

    }

    public void initView() {

    }

    @Override
    public void onRefresh() {
        setRefresh();
        getData1();
    }

    private void setRefresh() {
        mRecyclerViewAdapter.clearData();
        mRecyclerViewAdapter.notifyDataSetChanged();
        page = 0;
        mRvVspaOrder.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoadMore() {
        page = page + 1;
        getData1();
    }

    private void getData1() {
        HttpMethods.getInstance().vspa_order(new ProgressErrorSubscriber<>(VspaOnNext,
                getActivity()), mPreferences.getString("token", ""), page);
    }

}