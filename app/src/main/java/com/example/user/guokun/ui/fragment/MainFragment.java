package com.example.user.guokun.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.ui.activity.CaptureActivity;
import com.example.user.guokun.ui.activity.CouponActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：JTR on 2016/8/29 10:35
 * 邮箱：2091320109@qq.com
 */
public class MainFragment extends Fragment {

    private static final String EXTRA_CONTENT = "content";
    private static final int SHOW_SUBACTIVITY = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        initData();
        initView();
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static MainFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);

        MainFragment mallFragment = new MainFragment();
        mallFragment.setArguments(arguments);
        return mallFragment;
    }


    public void initData() {

    }

    public void initView() {

    }


    @OnClick({R.id.iv_saoyisao, R.id.iv_youhuiquan, R.id.iv_fujin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_saoyisao:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, SHOW_SUBACTIVITY);
                break;
            case R.id.iv_youhuiquan:
                startActivity(new Intent(getActivity(), CouponActivity.class));
                break;
            case R.id.iv_fujin:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != data) {
            Toast.makeText(getActivity(), data.getStringExtra("code"), Toast.LENGTH_SHORT).show();
        }
    }
}
