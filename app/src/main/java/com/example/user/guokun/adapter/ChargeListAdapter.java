package com.example.user.guokun.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.ChargeBean;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 译 on 2017/8/15.
 */

public class ChargeListAdapter extends RecyclerView.Adapter<ChargeListAdapter.ViewHolder> implements View.OnClickListener {
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private List<ChargeBean.DataBean> mData;
    private int VIEW_BOTTOM = 1;

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int data);
    }

    public ChargeListAdapter(List<ChargeBean.DataBean> mData) {
        this.mData = mData;
    }


    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_charge_list, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }


    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.mTvName.setText(mData.get(position).getDeposit());
        viewHolder.mTvPrice.setText(mData.get(position).getMoney());
        viewHolder.itemView.setTag(position);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getItemCount();
        if (dataItemCount == 1 || dataItemCount == position) {
            return VIEW_BOTTOM;
        } else {
            return 0;
        }
    }


    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


     class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_price)
        TextView mTvPrice;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}