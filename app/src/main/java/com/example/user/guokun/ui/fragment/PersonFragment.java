package com.example.user.guokun.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.UserInfoBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;
import com.example.user.guokun.ui.activity.CouponActivity;
import com.example.user.guokun.ui.activity.MyOrderActivity;
import com.example.user.guokun.ui.activity.PurseActivity;
import com.example.user.guokun.ui.activity.QuestionActivity;
import com.example.user.guokun.ui.activity.SettingActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static com.example.user.guokun.Utils.qiangzhi_logout;

/**
 * Created by 译 on 2017/8/4.
 */

public class PersonFragment extends Fragment implements EasyPermissions.PermissionCallbacks {

    private static final String EXTRA_CONTENT = "content";
    private static final int SHOW_SUBACTIVITY = 1;
    @BindView(R.id.tv_person_name)
    TextView mTvPersonName;
    @BindView(R.id.tv_person_yue)
    TextView mTvPersonYue;
    private SubscriberOnNextListener<UserInfoBean> UserOnNext;
    private SharedPreferences mPreferences;
    private UserInfoBean mUserInfoBean;
    private static final int RC_CAMERA_PERM = 123;

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
        mPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        UserOnNext = userInfoBean -> {
            if (userInfoBean.getCode() == 1) {
                mUserInfoBean = userInfoBean;
                mTvPersonName.setText(userInfoBean.getData().getNick_name());
                mTvPersonYue.setText(userInfoBean.getData().getUser_deposit() + "");
            } else if (userInfoBean.getCode() == -9) {
                qiangzhi_logout(getActivity());
            } else {
                Toast.makeText(getActivity(), userInfoBean.getMag(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        HttpMethods.getInstance().user_info(new ProgressSubscriber(UserOnNext,
                getActivity()), mPreferences.getString("token", ""));
    }

    public void initView() {

    }


    @OnClick({R.id.rl_person_yue, R.id.rl_person_coupon, R.id.rl_person_purse, R.id.rl_person_order, R.id.rl_person_invite, R.id.rl_person_question, R.id.rl_person_setting, R.id.rl_person_kefu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_person_yue:
                startActivity(new Intent(getActivity(), PurseActivity.class));
                break;
            case R.id.rl_person_coupon:
                startActivity(new Intent(getActivity(), CouponActivity.class));
                break;
            case R.id.rl_person_purse:
                startActivity(new Intent(getActivity(), PurseActivity.class));
                break;
            case R.id.rl_person_order:
                startActivity(new Intent(getActivity(), MyOrderActivity.class));
                break;
            case R.id.rl_person_invite:
                showShare();
                break;
            case R.id.rl_person_question:
                startActivity(new Intent(getActivity(), QuestionActivity.class));
                break;
            case R.id.rl_person_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.rl_person_kefu:
                call();
                break;
        }
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void call() {
        if (EasyPermissions.hasPermissions(getActivity(), Manifest.permission.CALL_PHONE)) {
            // Have permission, do the thing!
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + "4008557878"));
            startActivity(intent);
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_camera),
                    RC_CAMERA_PERM, Manifest.permission.CALL_PHONE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.d("chenyi", "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("chenyi", "onPermissionsDenied:" + requestCode + ":" + perms.size());

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("日报邀请函");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(getActivity());
    }
}