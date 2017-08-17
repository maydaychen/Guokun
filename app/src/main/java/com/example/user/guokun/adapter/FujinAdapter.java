package com.example.user.guokun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.ChairNearbyBean;
import com.example.user.guokun.bean.ChargeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 译 on 2017/8/18.
 */

public class FujinAdapter extends RecyclerView.Adapter<FujinAdapter.ViewHolder> implements View.OnClickListener {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private List<ChairNearbyBean.DataBean.ListBean> mData;
    private Context mContext;
    public int ID;

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int data);
    }

    public FujinAdapter(List<ChairNearbyBean.DataBean.ListBean> mData, Context context) {
        this.mData = mData;
        this.mContext = context;
        ID = mData.get(0).getId();
    }


    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_fujin, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }


    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.mTvAddress.setText(mData.get(position).getName());
        viewHolder.mTvAddressDetail.setText(mData.get(position).getName());
        viewHolder.mTvDistance.setText(mData.get(position).getDistance_um() + "km");
        viewHolder.itemView.setTag(position);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return mData.size();
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


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_address)
        TextView mTvAddress;
        @BindView(R.id.tv_address_detail)
        TextView mTvAddressDetail;
        @BindView(R.id.tv_distance)
        TextView mTvDistance;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}