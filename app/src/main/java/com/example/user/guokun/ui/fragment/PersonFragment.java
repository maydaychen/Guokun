package com.example.user.guokun.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
    @BindView(R.id.coupon_num)
    TextView mCouponNum;
    private SubscriberOnNextListener<UserInfoBean> UserOnNext;
    private SharedPreferences mPreferences;
    private UserInfoBean mUserInfoBean;
    private static final int RC_CAMERA_PERM = 123;
    private static final int RC_LOCATION_CONTACTS_PERM = 124;
    private String fileName = Environment.getExternalStorageDirectory() + "/logo.png";

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
                mCouponNum.setText(userInfoBean.getData().getCouponNum() + "");
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
        saveImg();
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
        if (requestCode == RC_LOCATION_CONTACTS_PERM) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
            File file = new File(fileName);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, fos);
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + "4008557878"));
            startActivity(intent);
        }
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
        oks.setTitle("国坤健康邀请函");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("快来下载吧！");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(fileName);//确保SDcard下面存在此张图片
//        oks.setImagePath("file:///android_asset/logo.jpg");//确保SDcard下面存在此张图片
//        oks.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503506356816&di=5dadbd01e162deb6601a801dc6258361&imgtype=0&src=http%3A%2F%2Fimg1.bitautoimg.com%2Fautoalbum%2Ffiles%2F20170407%2F958%2F16325395873602_5454777_3.jpg%3Fr%3D20170703");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://a.app.qq.com/o/simple.jsp?pkgname=com.example.user.guokun");
//        oks.setUrl("http://www.baidu.com");
        // 启动分享GUI
        oks.show(getActivity());
    }

//    protected void saveImg() {
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
//        File file = new File(fileName);
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//                FileOutputStream fos = new FileOutputStream(file);
//                bitmap.compress(Bitmap.CompressFormat.PNG, 50, fos);
//                fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

    @AfterPermissionGranted(RC_LOCATION_CONTACTS_PERM)
    public void saveImg() {
        String[] perms = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(getActivity(), perms)) {
            // Have permissions, do the thing!
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
            File file = new File(fileName);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    FileOutputStream fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, fos);
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Ask for both permissions
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_locate),
                    RC_LOCATION_CONTACTS_PERM, perms);
        }
    }
}