package com.example.user.guokun;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.widget.TextView;

import com.example.user.guokun.ui.activity.InitActivity;
import com.example.user.guokun.ui.fragment.DiscoveryFragment;
import com.example.user.guokun.ui.fragment.MainFragment;
import com.example.user.guokun.ui.fragment.PersonFragment;
import com.example.user.guokun.ui.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends InitActivity {

    @BindView(R.id.vp_content)
    CustomViewPager mContentVp;
    @BindView(R.id.tl_tab)
    TabLayout mTabTl;

    private List<Fragment> tabFragments;
    private List<String> tabIndicators;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initContent();
        initTab();
    }

    @Override
    public void initData() {

    }


    private void initContent() {
        tabIndicators = new ArrayList<>();
        tabIndicators.add("按摩");
        tabIndicators.add("发现");
        tabIndicators.add("个人中心");

        tabFragments = new ArrayList<>();
        tabFragments.add(MainFragment.newInstance(tabIndicators.get(0)));
        tabFragments.add(DiscoveryFragment.newInstance(tabIndicators.get(1)));
        tabFragments.add(PersonFragment.newInstance(tabIndicators.get(2)));

        ContentPagerAdapter contentAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        mContentVp.setAdapter(contentAdapter);
    }


    private void initTab() {
        mTabTl.setTabMode(TabLayout.MODE_FIXED);
        mTabTl.setSelectedTabIndicatorHeight(0);
        ViewCompat.setElevation(mTabTl, 10);
        mTabTl.setupWithViewPager(mContentVp);
        for (int i = 0; i < tabIndicators.size(); i++) {
            TabLayout.Tab itemTab = mTabTl.getTabAt(i);
            if (itemTab != null) {
                switch (i) {
                    case 0:
                        itemTab.setCustomView(R.layout.item_left);
                        TextView itemTv = itemTab.getCustomView().findViewById(R.id
                                .tv_menu_item);
                        itemTv.setText(tabIndicators.get(i));
                        break;
                    case 1:
                        itemTab.setCustomView(R.layout.item_middle);
                        TextView itemTv1 = itemTab.getCustomView().findViewById(R.id
                                .tv_menu_item1);
                        itemTv1.setText(tabIndicators.get(i));
                        break;
                    case 2:
                        itemTab.setCustomView(R.layout.item_right);
                        TextView itemTv2 = itemTab.getCustomView().findViewById(R.id
                                .tv_menu_item2);
                        itemTv2.setText(tabIndicators.get(i));
                        break;
                }
//                itemTab.setCustomView(R.layout.item_left);
//                TextView itemTv = (TextView) itemTab.getCustomView().findViewById(R.id
// .tv_menu_item);
//                itemTv.setText(tabIndicators.get(i));
            }
        }
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
