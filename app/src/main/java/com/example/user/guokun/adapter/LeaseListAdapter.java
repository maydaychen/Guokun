package com.example.user.guokun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.LeaseBean;
import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/9/26.
 */

public class LeaseListAdapter extends RecyclerView.Adapter<LeaseListAdapter.ViewHolder> implements View.OnClickListener {
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private List<LeaseBean.DataBean.ListBean> mData = new ArrayList<>();
    private int VIEW_BOTTOM = 1;
    private Context mContext;

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int data);
    }

    public void addAllData(List<LeaseBean.DataBean.ListBean> dataList) {
        this.mData.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.mData.clear();
    }

    public LeaseListAdapter(Context context) {
        mContext = context;
    }


    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lease_list, viewGroup, false);
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
//        viewHolder.mIvTerminal.setImageUrl(mData.get(position).getPic());
        viewHolder.mTvTerminalNum.setText(String.format(mContext.getResources().getString(R.string.terminal_num), mData.get(position).getMobile() + ""));
        viewHolder.mLeaseStartDate.setText(String.format(mContext.getResources().getString(R.string.lease_start_date), mData.get(position).getAdd_time()));
        viewHolder.mLeaseEndDate.setText(String.format(mContext.getResources().getString(R.string.lease_end_date), mData.get(position).getEnd_time() + ""));
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


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_terminal)
        SmartImageView mIvTerminal;
        @BindView(R.id.tv_terminal_num)
        TextView mTvTerminalNum;
        @BindView(R.id.lease_start_date)
        TextView mLeaseStartDate;
        @BindView(R.id.lease_end_date)
        TextView mLeaseEndDate;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}