package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
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
import butterknife.OnClick;

public class MyOrderActivity extends InitActivity implements PullLoadMoreRecyclerView.PullLoadMoreListener {

    //    @BindView(R.id.tb_order)
//    TabLayout mTbOrder;
//    @BindView(R.id.vp_content)
//    CustomViewPager mVpContent;
    @BindView(R.id.rv_vspa_order)
    PullLoadMoreRecyclerView mRvVspaOrder;
    private SubscriberOnNextAndErrorListener<VspaBean> VspaOnNext;
    private static final String EXTRA_CONTENT = "content";
    private static final int SHOW_SUBACTIVITY = 1;
    private SharedPreferences mPreferences;
    private VspaAdapter mRecyclerViewAdapter;
    private int page = 1;
//    private List<DaifahuoFragment> tabFragments;
//    private List<String> tabIndicators;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);

//        tabIndicators = new ArrayList<>();
//        tabIndicators.add("Vspa订单");
////        tabIndicators.add("商品订单");
//
//        tabFragments = new ArrayList<>();
//        tabFragments.add(VspaFragment.newInstance(tabIndicators.get(0)));
////        tabFragments.add(GoodsFragment.newInstance(tabIndicators.get(1)));
//        ContentPagerAdapter contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
//        mVpContent.setAdapter(contentAdapter);
//
//        mTbOrder.setTabMode(TabLayout.MODE_FIXED);
//        mTbOrder.setupWithViewPager(mVpContent);
    }

    @Override
    public void initData() {
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

        VspaOnNext = new SubscriberOnNextAndErrorListener<VspaBean>() {
            @Override
            public void onNext(VspaBean vspaBean) {
                mRvVspaOrder.setVisibility(View.VISIBLE);
                try {
                    mRvVspaOrder.setPullLoadMoreCompleted();
                    mRecyclerViewAdapter.addAllData(vspaBean.getData().getList());
                    mRecyclerViewAdapter.notifyDataSetChanged();
                } catch (NullPointerException e) {
                    if (page != 0) {
                        Toast.makeText(MyOrderActivity.this, "已经加载完毕！", Toast.LENGTH_LONG).show();
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
        mRvVspaOrder.setEmptyView(LayoutInflater.from(this).inflate(R.layout.empty_order, null));
        mRecyclerViewAdapter = new VspaAdapter(this);
        mRvVspaOrder.setAdapter(mRecyclerViewAdapter);

        getData1();

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
        HttpMethods.getInstance().vspa_order(new ProgressErrorSubscriber<>(VspaOnNext,
                this), mPreferences.getString("token", ""), page);
    }

    @OnClick(R.id.iv_coupon_detail_back)
    public void onViewClicked() {
        finish();
    }


//    class ContentPagerAdapter extends FragmentPagerAdapter {
//
//        ContentPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public DaifahuoFragment getItem(int position) {
//            return tabFragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return tabIndicators.size();
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return tabIndicators.get(position);
//        }
//    }

}
