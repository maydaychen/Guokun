package com.example.user.guokun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.CouponBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/8/11.
 */

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.ViewHolder> implements View.OnClickListener {
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private List<CouponBean.DataBean.ListBean> mData = new ArrayList<>();
    private int VIEW_BOTTOM = 1;
    private Context mContext;

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int data);
    }

    public void addAllData(List<CouponBean.DataBean.ListBean> dataList) {
        this.mData.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.mData.clear();
    }

    public CouponsAdapter(Context context) {
        mContext = context;
    }


    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_coupons, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }


    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
//        viewHolder.project.setText(mData.get(position).get("name"));
//        viewHolder.peijian.setText(null == mData.get(position).get("peijian") || mData.get(position).get("peijian").equals("") ? "--" : mData.get(position).get("peijian"));
//        viewHolder.num.setText(null == mData.get(position).get("num") || mData.get(position).get("num").equals("") ? "--" : mData.get(position).get("num"));
//        viewHolder.price.setText(null == mData.get(position).get("price") || mData.get(position).get("price").equals("") ? "--" : mData.get(position).get("price"));
        viewHolder.mTvCouponName.setText(mData.get(position).getName());
        viewHolder.mTvCouponExpiry.setText(String.format(mContext.getResources().getString(R.string.tv_coupon_expiry), mData.get(position).getEndtime() + ""));
        viewHolder.mTvCouponPrice.setText(String.format(mContext.getResources().getString(R.string.price), mData.get(position).getMoney() + ""));
        viewHolder.mTvCouponName.setText(mData.get(position).getName());
        viewHolder.itemView.setTag(position);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_coupon_price)
        TextView mTvCouponPrice;
        @BindView(R.id.tv_coupon_name)
        TextView mTvCouponName;
        @BindView(R.id.tv_coupon_range)
        TextView mTvCouponRange;
        @BindView(R.id.tv_coupon_store)
        TextView mTvCouponStore;
        @BindView(R.id.tv_coupon_expiry)
        TextView mTvCouponExpiry;
        @BindView(R.id.tv_coupon_time)
        TextView mTvCouponTime;
        @BindView(R.id.ll_coupon)
        LinearLayout mLlCoupon;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}