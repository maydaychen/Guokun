package com.example.user.guokun.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.user.guokun.R;
import com.example.user.guokun.bean.ChairNearbyBean;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends InitActivity implements BaiduMap.OnMapLoadedCallback, BDLocationListener {
    private ChairNearbyBean.DataBean.ListBean mListBean;
    private DecimalFormat df = new DecimalFormat("######0.00");
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private boolean ifFrist = true;

    @BindView(R.id.tv_map_distance)
    TextView mTvMapDistance;
    @BindView(R.id.tv_map_name)
    TextView mTvMapName;
    @BindView(R.id.tv_map_detail)
    TextView mTvMapDetail;
    @BindView(R.id.mv_map)
    MapView mMvMap;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);

    }

    @Override
    public void initData() {
        mListBean = (ChairNearbyBean.DataBean.ListBean) getIntent().getSerializableExtra("objs");
        mTvMapDistance.setText(mListBean.getDistance_um() / 1000.00 + "km");
        mTvMapName.setText(mListBean.getName());
        mTvMapDetail.setText(mListBean.getAddress());

        mBaiduMap = mMvMap.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        mBaiduMap.setMyLocationEnabled(true);

        LatLng point = new LatLng(Double.valueOf(mListBean.getLatitude()), Double.valueOf(mListBean.getLongitude()));
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.ding_wei_tubiao);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);


        if (ifFrist) {
            LatLng ll = new LatLng(Double.valueOf(mListBean.getLatitude()), Double.valueOf(mListBean.getLongitude()));
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            // 移动到某经纬度
            mBaiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomBy(2f);
            // 放大
            mBaiduMap.animateMapStatus(update);
            ifFrist = false;
        }
        initMap();
    }

    private void initMap() {
        mLocationClient = new LocationClient(MapActivity.this); //声明LocationClient类
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

    @OnClick({R.id.iv_map_back, R.id.bt_daohang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_map_back:
                finish();
                break;
            case R.id.bt_daohang:
                String location = "geo:" + mListBean.getLatitude() + "," + mListBean.getLongitude();
                Uri mUri = Uri.parse(location + "?q="+mListBean.getName());
                Intent mIntent = new Intent(Intent.ACTION_VIEW, mUri);
                startActivity(mIntent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMvMap.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMvMap.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMvMap.onPause();
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);


    }

    @Override
    public void onMapLoaded() {

    }
}
