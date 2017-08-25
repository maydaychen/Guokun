package com.example.user.guokun.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.ui.widget.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsDetailActivity extends InitActivity {
    private List<String> url_list;

    @BindView(R.id.banner)
    Banner mBanner;

    @BindView(R.id.tv_buynow)
    TextView tvBuynow;
    @BindView(R.id.ll_detail_botton)
    LinearLayout llDetailBotton;
    @BindView(R.id.goods_detail_line)
    TextView goodsDetailLine;
    @BindView(R.id.iv_choose_doc_back)
    ImageView ivChooseDocBack;
    @BindView(R.id.rl_mine_title)
    RelativeLayout rlMineTitle;

    @BindView(R.id.tv_goods_detail_name)
    TextView tvGoodsDetailName;
    @BindView(R.id.tv_goods_detail_price)
    TextView tvGoodsDetailPrice;

    @BindView(R.id.tv_goods_detail_shop)
    TextView tvGoodsDetailShop;
    @BindView(R.id.tv_goods_detail_sold)
    TextView tvGoodsDetailSold;
    @BindView(R.id.tv_goods_detail_inventory)
    TextView tvGoodsDetailInventory;
    @BindView(R.id.iv_detail_pic1)
    ImageView ivDetailPic1;
    @BindView(R.id.iv_detail_pic2)
    ImageView ivDetailPic2;
    @BindView(R.id.wv_goods_detail)
    WebView wvGoodsDetail;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.iv_detail_pic3)
    ImageView ivDetailPic3;
    @BindView(R.id.iv_detail_pic4)
    ImageView ivDetailPic4;
    @BindView(R.id.rv_goods_comment)
    RecyclerView rvGoodsComment;

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        tvGoodsDetailName.setText("iPhone 10");
        tvGoodsDetailPrice.setText(String.format(getResources().getString(R.string.price), 100 + ""));
        tvGoodsDetailSold.setText(String.format(getResources().getString(R.string.goods_detail_sold), 888 + ""));
        tvGoodsDetailShop.setText("京东商城");
        url_list = new ArrayList<>();
        url_list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503506356816&di=5dadbd01e162deb6601a801dc6258361&imgtype=0&src=http%3A%2F%2Fimg1.bitautoimg.com%2Fautoalbum%2Ffiles%2F20170407%2F958%2F16325395873602_5454777_3.jpg%3Fr%3D20170703");
        url_list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503506356816&di=5dadbd01e162deb6601a801dc6258361&imgtype=0&src=http%3A%2F%2Fimg1.bitautoimg.com%2Fautoalbum%2Ffiles%2F20170407%2F958%2F16325395873602_5454777_3.jpg%3Fr%3D20170703");
        url_list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503506356816&di=5dadbd01e162deb6601a801dc6258361&imgtype=0&src=http%3A%2F%2Fimg1.bitautoimg.com%2Fautoalbum%2Ffiles%2F20170407%2F958%2F16325395873602_5454777_3.jpg%3Fr%3D20170703");
    }

    @Override
    public void initData() {
        mBanner.setImages(url_list).setImageLoader(new GlideImageLoader()).start();
        mBanner.isAutoPlay(false);
        mBanner.setOnBannerListener(position -> Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show());

        tvBuynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsDetailActivity.this, ConfirmActivity.class);
//                intent.putExtra("array", array1.toString());
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("preorder", dataBeen);
//                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}
