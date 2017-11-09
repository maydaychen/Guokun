package com.example.user.guokun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.GoodsOrderBean;
import com.example.user.guokun.bean.GoodsOrderMessage;
import com.loopj.android.image.SmartImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/10/9.
 */

public class GoodsDaishouhuoAdapter extends RecyclerView.Adapter<GoodsDaishouhuoAdapter.ViewHolder> implements View.OnClickListener {
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private Context mContext;
    private List<GoodsOrderBean.ResultBean> mData = new ArrayList<>();


    public void addAllData(List<GoodsOrderBean.ResultBean> dataList) {
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

    public GoodsDaishouhuoAdapter(Context context) {
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_daishouhuo, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvOrderShopname.setText(mData.get(position).getGoods().get(0).getTitle());
        holder.mIvPic.setImageUrl(mData.get(position).getGoods().get(0).getThumb());
        holder.mTvOrderTotalNum.setText(String.format(mContext.getResources().getString(R.string.tv_order_num), mData.get(position).getGoods().get(0).getTotal()));
        holder.mTvOrderTotalPrice.setText(String.format(mContext.getResources().getString(R.string.tv_order_total_price), mData.get(position).getGoods().get(0).getPrice()));
        holder.mBtDaishouhuoConfirm.setOnClickListener(view -> {
            GoodsOrderMessage msg = new GoodsOrderMessage();
            msg.setId(mData.get(position).getId());
            msg.setStatus("comf");
            EventBus.getDefault().post(msg);
        });
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_order_shopname)
        TextView mTvOrderShopname;
        @BindView(R.id.iv_pic)
        SmartImageView mIvPic;
        @BindView(R.id.tv_order_total_num)
        TextView mTvOrderTotalNum;
        @BindView(R.id.tv_order_total_price)
        TextView mTvOrderTotalPrice;
        @BindView(R.id.bt_daishouhuo_confirm)
        Button mBtDaishouhuoConfirm;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}