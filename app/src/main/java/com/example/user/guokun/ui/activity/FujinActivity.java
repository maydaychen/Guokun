package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MyLocationData;
import com.example.user.guokun.R;
import com.example.user.guokun.adapter.FujinAdapter;
import com.example.user.guokun.bean.ChairNearbyBean;
import com.example.user.guokun.http.HttpMethods;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FujinActivity extends InitActivity implements BaiduMap.OnMapLoadedCallback, BDLocationListener {
    @BindView(R.id.rv_fujin)
    RecyclerView mRvFujin;
    @BindView(R.id.tv_fujin_address)
    TextView mTvFujinAddress;
    @BindView(R.id.iv_refresh)
    ImageView ivRefresh;
    private SubscriberOnNextListener<ChairNearbyBean> VspaOnNext;
    private double lat;
    private double lon;
    private SharedPreferences mPreferences;
    private LocationClient mLocationClient;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fujin);
        ButterKnife.bind(this);
        initMap();
        mTvFujinAddress.setText(getIntent().getStringExtra("address"));
        lat = getIntent().getDoubleExtra("lat", 0.00);
        lon = getIntent().getDoubleExtra("lon", 0.00);
    }

    @Override
    public void initData() {
        mPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        VspaOnNext = chairNearbyBean -> {
            mRvFujin.setLayoutManager(new LinearLayoutManager(this));
            FujinAdapter fujinAdapter = new FujinAdapter(chairNearbyBean.getData().getList(), getApplicationContext());
            mRvFujin.setAdapter(fujinAdapter);
            fujinAdapter.setOnItemClickListener((view, data) -> {
                Intent intent = new Intent(FujinActivity.this, MapActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("objs", chairNearbyBean.getData().getList().get(data));
                intent.putExtras(bundle);
                intent.putExtra("lat", lat);
                intent.putExtra("lon", lon);
                startActivity(intent);
            });
        };
        Log.i("chenyi", "initData: " + getIntent().getDoubleExtra("lat", 0.00) + "+" + getIntent().getDoubleExtra("lon", 0.00));
        getStore();
    }


    @OnClick({R.id.iv_coupon_back, R.id.tv_coupon_detail, R.id.tv_relocate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_coupon_back:
                finish();
                break;
            case R.id.tv_coupon_detail:
                startActivity(new Intent(FujinActivity.this, CouponDetailActivity.class));
                break;
            case R.id.tv_relocate:
                startRotate();
                mLocationClient.start();//开启定位
                break;
        }
    }

    public void startRotate() {
        Animation operatingAnim = AnimationUtils.loadAnimation(FujinActivity.this, R.anim.version_image_rotate);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        if (operatingAnim != null) {
            ivRefresh.startAnimation(operatingAnim);
        }
    }

    /**
     * 关闭动画
     */
    public void stopRotate() {
        ivRefresh.clearAnimation();
    }

    private void initMap() {
        mLocationClient = new LocationClient(FujinActivity.this); //声明LocationClient类
        mLocationClient.registerLocationListener(this);//注册监听函数
        initLocation();
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
        mTvFujinAddress.setText(bdLocation.getAddress().address);

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
        stopRotate();
        getStore();
        mLocationClient.stop();
    }

    private void getStore() {
        HttpMethods.getInstance().char_nearby(new ProgressSubscriber<>(VspaOnNext, FujinActivity.this),
                mPreferences.getString("token", ""), lon + "", lat + "");
    }

    @Override
    public void onMapLoaded() {

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
}
