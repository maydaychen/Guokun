package com.example.user.guokun.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.guokun.R;
import com.example.user.guokun.ui.activity.MyOrderActivity;
import com.example.user.guokun.ui.activity.QuestionActivity;
import com.example.user.guokun.ui.activity.SettingActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 译 on 2017/8/4.
 */

public class PersonFragment extends Fragment {

    private static final String EXTRA_CONTENT = "content";
    private static final int SHOW_SUBACTIVITY = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, root);
        initData();
        initView();
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static PersonFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);

        PersonFragment mallFragment = new PersonFragment();
        mallFragment.setArguments(arguments);
        return mallFragment;
    }


    public void initData() {

    }

    public void initView() {

    }



    @OnClick({R.id.rl_person_yue, R.id.rl_person_coupon, R.id.rl_person_purse, R.id.rl_person_order, R.id.rl_person_invite, R.id.rl_person_question, R.id.rl_person_setting, R.id.rl_person_kefu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_person_yue:
                break;
            case R.id.rl_person_coupon:
                break;
            case R.id.rl_person_purse:
                break;
            case R.id.rl_person_order:
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
                break;
            case R.id.rl_person_invite:
                break;
            case R.id.rl_person_question:
                startActivity(new Intent(getActivity(), QuestionActivity.class));
                break;
            case R.id.rl_person_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.rl_person_kefu:
                break;
        }
    }
}