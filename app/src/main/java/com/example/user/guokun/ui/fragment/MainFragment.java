package com.example.user.guokun.ui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MyLocationData;
import com.example.user.guokun.R;
import com.example.user.guokun.bean.UserInfoBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;
import com.example.user.guokun.ui.activity.CouponActivity;
import com.example.user.guokun.ui.activity.FujinActivity;
import com.example.user.guokun.ui.activity.GuigeActivity;
import com.example.user.guokun.ui.activity.PurseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

import static android.Manifest.permission.CAMERA;
import static com.example.user.guokun.Utils.qiangzhi_logout;

/**
 * 作者：JTR on 2016/8/29 10:35
 * 邮箱：2091320109@qq.com
 */
public class MainFragment extends Fragment implements EasyPermissions.PermissionCallbacks, BaiduMap.OnMapLoadedCallback, BDLocationListener {

    private static final String EXTRA_CONTENT = "content";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private static final int RC_CAMERA_PERM = 123;
    private LocationClient mLocationClient;
    private double lat;
    private double lon;
    private String address;
    private boolean isFirstLocate = true;
    private SubscriberOnNextListener<UserInfoBean> UserOnNext;
    private UserInfoBean mUserInfoBean;
    private SharedPreferences mPreferences;

    @BindView(R.id.iv_main_pic)
    ImageView mIvMainPic;
    @BindView(R.id.tv_main_address)
    TextView mTvMainAddress;
//    private List<String> url_list;
//
//    @BindView(R.id.banner)
//    Banner mBanner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        initData();
        initView();
        initMap();
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
        mPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
//        url_list = new ArrayList<>();
//        url_list.add("http://www.paochefang.com/wp-content/uploads/paoimage/2013/06/033242x8o.gif");
//        url_list.add("http://www.paochefang.com/wp-content/uploads/paoimage/2013/06/033242x8o.gif");
//        url_list.add("http://www.paochefang.com/wp-content/uploads/paoimage/2013/06/033242x8o.gif");
        UserOnNext = userInfoBean -> {
            if (userInfoBean.getCode() == 1) {
                mUserInfoBean = userInfoBean;
            } else if (userInfoBean.getCode() == -9) {
                qiangzhi_logout(getActivity());
            } else {
                Toast.makeText(getActivity(), userInfoBean.getMag(), Toast.LENGTH_SHORT).show();
            }

        };
    }

    public void initView() {
//        mBanner.setImages(url_list).setImageLoader(new GlideImageLoader()).start();
//        mBanner.setOnBannerListener(position -> Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show());
        mIvMainPic.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PurseActivity.class);
            intent.putExtra("yue", mUserInfoBean.getData().getUser_deposit());
            startActivity(intent);
        });
        HttpMethods.getInstance().user_info(new ProgressSubscriber(UserOnNext,
                getActivity()), mPreferences.getString("token", ""));
    }

    private void initMap() {
        mLocationClient = new LocationClient(getActivity()); //声明LocationClient类
        mLocationClient.registerLocationListener(this);//注册监听函数
        initLocation();
        mLocationClient.start();//开启定位
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高 精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
//        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当                                                                                                                                                                                                                                gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
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
                Intent intent = new Intent(getActivity(), FujinActivity.class);
                intent.putExtra("lat", lat);
                intent.putExtra("lon", lon);
                intent.putExtra("address", address);
                startActivity(intent);
                break;
        }
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void init() {
        if (EasyPermissions.hasPermissions(getActivity(), Manifest.permission.CAMERA)) {
//            Intent intent = new Intent(getActivity(), CaptureActivity.class);
//            startActivityForResult(intent, SHOW_SUBACTIVITY);
            Intent intent = new Intent(getActivity(), GuigeActivity.class);
            intent.putExtra("code", "898602b6101740177983");
            startActivity(intent);
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_camera), RC_CAMERA_PERM, Manifest.permission.CAMERA);
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
        Intent intent = new Intent(getActivity(), GuigeActivity.class);
        intent.putExtra("code", "898602b6101740177935");
        startActivity(intent);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d("chenyi", "onPermissionsDenied:" + requestCode + ":" + perms.size());
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != data) {
            Toast.makeText(getActivity(), data.getStringExtra("code"), Toast.LENGTH_SHORT).show();
            String code = data.getStringExtra("code").substring(data.getStringExtra("code").indexOf("=") + 1);
            Log.i("chenyi", "onActivityResult: " + code);
            Intent intent = new Intent(getActivity(), GuigeActivity.class);
            intent.putExtra("code", code);
            startActivity(intent);
        }
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        // 设置定位数据
        //mBaiduMap.setMyLocationData(locData);
        lat = bdLocation.getLatitude();
        lon = bdLocation.getLongitude();
        address = bdLocation.getAddress().address;
        mTvMainAddress.setText(bdLocation.getAddress().address);
        if (isFirstLocate) {
            isFirstLocate = false;
            Log.i("chenyi", "lat == " + lat);
            Log.i("chenyi", "lon == " + lon);
            String request = "{\"lat\":\"" + lat + "\",\"lng\":\"" + lon + "\"}";
            Log.d("chenyi", request);
          /*  webView.callHandler("frame", request, new CallBackFunction() {
                @Override
                public void onCallBack(String jsResponseData) {
                    Log.d("chenyi", "getLat " + jsResponseData);
                }
            });*/
            mLocationClient.stop();
        }
    }

    @Override
    public void onMapLoaded() {

    }
}
