package com.example.user.guokun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.VspaBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/9/7.
 */

public class GoodsOrderAdapter extends RecyclerView.Adapter<GoodsOrderAdapter.ViewHolder> implements View.OnClickListener {
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;
    private List<VspaBean.DataBean.ListBean> mData = new ArrayList<>();


    public void addAllData(List<VspaBean.DataBean.ListBean> dataList) {
        this.mData.addAll(dataList);
        notifyDataSetChanged();
    }

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int data);
    }

    public void clearData() {
        this.mData.clear();
    }

    public GoodsOrderAdapter(Context context) {
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_orders, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvOrderTime.setText(mData.get(position).getPay_time());
        holder.mOrderName.setText(String.format(mContext.getResources().getString(R.string.item_coupons_project), mData.get(position).getCost_name()));
        holder.mOrderTime.setText(String.format(mContext.getResources().getString(R.string.order_time), mData.get(position).getTime_len() + ""));
        holder.mOrderPrice.setText(String.format(mContext.getResources().getString(R.string.order_price), mData.get(position).getFee() + ""));

        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_order_time)
        TextView mTvOrderTime;
        @BindView(R.id.order_name)
        TextView mOrderName;
        @BindView(R.id.order_price)
        TextView mOrderPrice;
        @BindView(R.id.order_time)
        TextView mOrderTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}