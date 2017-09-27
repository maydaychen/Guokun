package com.example.user.guokun.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.example.user.guokun.R;
import com.example.user.guokun.ui.fragment.AllGoodsFragment;
import com.example.user.guokun.ui.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsOrdersActivity extends AppCompatActivity {
    @BindView(R.id.tb_order)
    TabLayout mTbOrder;
    @BindView(R.id.vp_content)
    CustomViewPager mVpContent;


    private List<Fragment> tabFragments;
    private List<String> tabIndicators;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_orders);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        tabIndicators = new ArrayList<>();
        tabIndicators.add("全部");
        tabIndicators.add("待支付");
        tabIndicators.add("待发货");
        tabIndicators.add("已发货");
        tabIndicators.add("已完成");
//        tabIndicators.add("商品订单");

        tabFragments = new ArrayList<>();
        tabFragments.add(AllGoodsFragment.newInstance(tabIndicators.get(0)));
        tabFragments.add(AllGoodsFragment.newInstance(tabIndicators.get(1)));
        tabFragments.add(AllGoodsFragment.newInstance(tabIndicators.get(2)));
        tabFragments.add(AllGoodsFragment.newInstance(tabIndicators.get(3)));
        tabFragments.add(AllGoodsFragment.newInstance(tabIndicators.get(4)));
//        tabFragments.add(GoodsFragment.newInstance(tabIndicators.get(1)));
        ContentPagerAdapter contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mVpContent.setAdapter(contentAdapter);

        mTbOrder.setTabMode(TabLayout.MODE_FIXED);
        mTbOrder.setupWithViewPager(mVpContent);
    }

    @OnClick(R.id.iv_coupon_detail_back)
    public void onViewClicked() {
        finish();
    }

    class ContentPagerAdapter extends FragmentPagerAdapter {

        ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return tabFragments.get(position);
        }

        @Override
        public int getCount() {
            return tabIndicators.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabIndicators.get(position);
        }
    }
}
