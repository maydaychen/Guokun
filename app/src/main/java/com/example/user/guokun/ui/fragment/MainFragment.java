package com.example.user.guokun.ui.fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
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
import com.example.user.guokun.ui.activity.FujinActivity;
import com.example.user.guokun.ui.widget.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;

/**
 * 作者：JTR on 2016/8/29 10:35
 * 邮箱：2091320109@qq.com
 */
public class MainFragment extends Fragment {

    private static final String EXTRA_CONTENT = "content";
    private static final int SHOW_SUBACTIVITY = 1;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private List<String> url_list;


    @BindView(R.id.banner)
    Banner mBanner;

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
        url_list = new ArrayList<>();
        url_list.add("http://www.paochefang.com/wp-content/uploads/paoimage/2013/06/033242x8o.gif");
        url_list.add("http://www.paochefang.com/wp-content/uploads/paoimage/2013/06/033242x8o.gif");
        url_list.add("http://www.paochefang.com/wp-content/uploads/paoimage/2013/06/033242x8o.gif");
    }

    public void initView() {
        mBanner.setImages(url_list).setImageLoader(new GlideImageLoader()).start();
        mBanner.setOnBannerListener(position -> Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show());
    }


    @OnClick({R.id.iv_saoyisao, R.id.iv_youhuiquan, R.id.iv_fujin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_saoyisao:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (getActivity().checkSelfPermission(CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{CAMERA},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    } else {
                        init();
                    }
                } else init();
                break;
            case R.id.iv_youhuiquan:
                startActivity(new Intent(getActivity(), CouponActivity.class));
                break;
            case R.id.iv_fujin:
                startActivity(new Intent(getActivity(), FujinActivity.class));
                break;
        }
    }

    private void init() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, SHOW_SUBACTIVITY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (permissions[0].equals(CALL_PHONE)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意使用write
                init();
            } else {
                //用户不同意，自行处理即可
                Toast.makeText(getActivity(), "没有相机相关权限，请设置！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != data) {
            Toast.makeText(getActivity(), data.getStringExtra("code"), Toast.LENGTH_SHORT).show();
        }
    }

}
