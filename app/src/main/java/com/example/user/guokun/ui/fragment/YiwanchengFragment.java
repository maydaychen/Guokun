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
import com.example.user.guokun.adapter.YiwanchengAdapter;
import com.example.user.guokun.bean.GoodsOrderBean;
import com.example.user.guokun.http.HttpShopMethod;
import com.example.user.guokun.http.ProgressErrorSubscriber;
import com.example.user.guokun.http.SubscriberOnNextAndErrorListener;
import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/10/9.
 */

public class YiwanchengFragment extends Fragment implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    @BindView(R.id.rv_vspa_order)
    PullLoadMoreRecyclerView mRvVspaOrder;
    private SubscriberOnNextAndErrorListener<JSONObject> VspaOnNext;
    private static final String EXTRA_CONTENT = "content";
    private static final int SHOW_SUBACTIVITY = 1;
    private SharedPreferences mPreferences;
    private YiwanchengAdapter mRecyclerViewAdapter;
    private int page = 1;
    private GoodsOrderBean mGoodsOrderBean;
    private Gson mGson = new Gson();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_allgoods, container, false);
        ButterKnife.bind(this, root);
        initData();
        initView();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData1();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static YiwanchengFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);

        YiwanchengFragment mallFragment = new YiwanchengFragment();
        mallFragment.setArguments(arguments);

        return mallFragment;
    }


    public void initData() {
        mPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        VspaOnNext = new SubscriberOnNextAndErrorListener<JSONObject>() {
            @Override
            public void onNext(JSONObject jsonObject) {
                mRvVspaOrder.setVisibility(View.VISIBLE);
                mRvVspaOrder.setPullLoadMoreCompleted();

                try {
                    if (jsonObject.getInt("statusCode") == 1) {
                        mGoodsOrderBean = mGson.fromJson(jsonObject.toString(), GoodsOrderBean.class);
                        mRecyclerViewAdapter.addAllData(mGoodsOrderBean.getResult());
                        mRecyclerViewAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), jsonObject.getString("result"), Toast.LENGTH_SHORT).show();
                    }

                } catch (NullPointerException e) {
                    if (page != 0) {
                        Toast.makeText(getActivity(), "已经加载完毕！", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
        mRvVspaOrder.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.empty_order, null));
        mRecyclerViewAdapter = new YiwanchengAdapter(getActivity());
        mRvVspaOrder.setAdapter(mRecyclerViewAdapter);
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
        page = 1;
        mRvVspaOrder.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onLoadMore() {
        page = page + 1;
        getData1();
    }

    private void getData1() {
        HttpShopMethod.getInstance().orders(new ProgressErrorSubscriber<>(VspaOnNext,
                getActivity()), mPreferences.getString("access_token", ""), mPreferences.getString("sessionkey", ""), page, "3");
    }
}
