package com.example.user.guokun.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.GoodsDetailBean;
import com.example.user.guokun.bean.ImageInfo;
import com.example.user.guokun.http.HttpShopMethod;
import com.example.user.guokun.http.ProgressSubscriber;
import com.example.user.guokun.http.SubscriberOnNextListener;
import com.example.user.guokun.ui.widget.GlideImageLoader;
import com.google.gson.Gson;
import com.loopj.android.image.SmartImageView;
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsDetailActivity extends InitActivity {
    private SubscriberOnNextListener<JSONObject> goodsOnNext;
    private SubscriberOnNextListener<JSONObject> buyNowOnNext;
    private Gson gson = new Gson();
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private GoodsDetailBean indexBean;
    int total = 1;


    @BindView(R.id.scrollView)
    ScrollView mScrollView;
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

    @Override
    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        tvGoodsDetailShop.setText("京东商城");
    }

    @Override
    public void initData() {
        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = preferences.edit();
        buyNowOnNext = new SubscriberOnNextListener<JSONObject>() {
            @Override
            public void onNext(JSONObject jsonObject) throws JSONException {
                if (jsonObject.getInt("statusCode") == 1) {
                    Log.i("chenyi", "onNext: " + jsonObject.toString());

//                    BuyNowBean indexBean = gson.fromJson(jsonObject.toString(), BuyNowBean.class);
                    Intent intent = new Intent(GoodsDetailActivity.this, ConfirmActivity.class);
                    intent.putExtra("objs", jsonObject.toString());
//                    intent.putExtra("oid", getIntent().getStringExtra("oid"));
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("objs", indexBean);
//                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(GoodsDetailActivity.this, jsonObject.getString("result"), Toast.LENGTH_SHORT).show();
                }
            }
        };
        goodsOnNext = jsonObject -> {
            if (jsonObject.getInt("statusCode") == 1) {
                indexBean = gson.fromJson(jsonObject.toString(), GoodsDetailBean.class);
                mBanner.setImages(indexBean.getResult().getPics()).setImageLoader(new GlideImageLoader()).start();
                mBanner.isAutoPlay(false);
                mBanner.setOnBannerListener(position -> Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show());
                tvGoodsDetailName.setText(indexBean.getResult().getGoods().getTitle());
                tvGoodsDetailPrice.setText(String.format(getResources().getString(R.string.price), indexBean.getResult().getGoods().getProductprice()));
                tvGoodsDetailInventory.setText(String.format(getResources().getString(R.string.goods_detail_inventory), indexBean.getResult().getGoods().getTotal()));
                tvGoodsDetailSold.setText(String.format(getResources().getString(R.string.goods_detail_sold), indexBean.getResult().getGoods().getSales()));


            } else {
                Toast.makeText(GoodsDetailActivity.this, jsonObject.getString("result"), Toast.LENGTH_SHORT).show();
            }
        };

        HttpShopMethod.getInstance().good_detail(
                new ProgressSubscriber(goodsOnNext, GoodsDetailActivity.this),
                preferences.getString("access_token", ""), preferences.getString("sessionkey", ""), getIntent().getStringExtra("id"));
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
        final SmartImageView smartImageView = contentView.findViewById(R.id.iv_pop_goods);
        final TextView tvrepository = contentView.findViewById(R.id.tv_repository);
        TextView tvPrice = contentView.findViewById(R.id.tv_price);
        TextView tvSubCount = contentView.findViewById(R.id.tv_subtraction);
        final TextView tvAddCount = contentView.findViewById(R.id.tv_addition);
        final TextView tvGoodCount = contentView.findViewById(R.id.tv_good_count);
        TextView tvBuyNow = (TextView) contentView.findViewById(R.id.tv_buy_now);
        final ImageInfo info = new ImageInfo();

        final PopupWindow popupWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,
                true);

        tvAddCount.setOnClickListener(v -> {

            total++;
            tvGoodCount.setText(total + "");
        });
        tvSubCount.setOnClickListener(v -> {
            if (total > 1) {
                total--;
                tvGoodCount.setText(total + "");
            }
        });
        tvBuyNow.setOnClickListener(v -> {
            popupWindow.dismiss();
            HttpShopMethod.getInstance().buy_now(
                    new ProgressSubscriber(buyNowOnNext, GoodsDetailActivity.this),
                    preferences.getString("access_token", ""), preferences.getString("sessionkey", ""), getIntent().getStringExtra("id"), tvGoodCount.getText().toString());
        });
        tvPrice.setText(String.format(getResources().getString(R.string.price), indexBean.getResult().getGoods().getProductprice()));
        tvrepository.setText(String.format(getResources().getString(R.string.goods_detail_inventory), indexBean.getResult().getGoods().getTotal()));
        smartImageView.setImageUrl("http://" + indexBean.getResult().getGoods().getThumb());
        smartImageView.setOnClickListener(v -> {
            info.setBigImageUrl("http://" + indexBean.getResult().getGoods().getThumb());
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
//            ((Activity) context).overridePendingTransition(0, 0);
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
