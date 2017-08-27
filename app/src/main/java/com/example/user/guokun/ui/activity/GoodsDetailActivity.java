package com.example.user.guokun.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.ImageInfo;
import com.example.user.guokun.ui.widget.GlideImageLoader;
import com.loopj.android.image.SmartImageView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsDetailActivity extends InitActivity {
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
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
    @BindView(R.id.wv_goods_detail)
    WebView wvGoodsDetail;
    @BindView(R.id.textView)
    TextView textView;
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
    }

    @OnClick({R.id.tv_buynow, R.id.iv_choose_doc_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_buynow:
                showPopupWindow(GoodsDetailActivity.this);
                break;
            case R.id.iv_choose_doc_back:
                finish();
                break;
        }
    }

    private void showPopupWindow(Context context) {
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.popwindow_good_detail, null);
        final SmartImageView smartImageView = (SmartImageView) contentView.findViewById(R.id.iv_pop_goods);
        final TextView tvrepository = (TextView) contentView.findViewById(R.id.tv_repository);
        TextView tvPrice = (TextView) contentView.findViewById(R.id.tv_price);
        TextView tvSubCount = (TextView) contentView.findViewById(R.id.tv_subtraction);
        final TextView tvAddCount = (TextView) contentView.findViewById(R.id.tv_addition);
        final TextView tvGoodCount = (TextView) contentView.findViewById(R.id.tv_good_count);
        TextView tvBuyNow = (TextView) contentView.findViewById(R.id.tv_buy_now);
        final ListView gouwucheAdd = (ListView) contentView.findViewById(R.id.rv_good_detail_add);
        final ImageInfo info = new ImageInfo();

        final PopupWindow popupWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,
                true);

        tvBuyNow.setOnClickListener(v -> {
            popupWindow.dismiss();
            Intent intent = new Intent(GoodsDetailActivity.this, ConfirmActivity.class);
            startActivity(intent);
        });

        smartImageView.setOnClickListener(v -> {
            info.setBigImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1503506356816&di=5dadbd01e162deb6601a801dc6258361&imgtype=0&src=http%3A%2F%2Fimg1.bitautoimg.com%2Fautoalbum%2Ffiles%2F20170407%2F958%2F16325395873602_5454777_3.jpg%3Fr%3D20170703");
            info.imageViewWidth = smartImageView.getWidth();
            info.imageViewHeight = smartImageView.getHeight();
            int[] points = new int[2];
            smartImageView.getLocationInWindow(points);
            info.imageViewX = points[0];
            info.imageViewY = points[1];
            Intent intent = new Intent(context, ImagePreviewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("IMAGE_INFO", info);
            intent.putExtras(bundle);
            startActivity(intent);
            ((Activity) context).overridePendingTransition(0, 0);
        });
        ColorDrawable dw = new ColorDrawable(0x00000000);
        popupWindow.setBackgroundDrawable(dw);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        popupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp1 = getWindow().getAttributes();
            lp1.alpha = 1f;
            getWindow().setAttributes(lp1);
        });

        popupWindow.showAtLocation(GoodsDetailActivity.this.findViewById(R.id.rl_mine_title),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }
}
