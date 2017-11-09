package com.example.user.guokun.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.guokun.R;
import com.example.user.guokun.bean.GoodsListBean;
import com.loopj.android.image.SmartImageView;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2017/8/23.
 */

public class DiscovereyAdapter extends RecyclerView.Adapter<DiscovereyAdapter.ViewHolder> implements View.OnClickListener {

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private List<GoodsListBean.ResultBean> mData;
    private Context mContext;
    private DecimalFormat df = new DecimalFormat("######0.00");
    public int ID;

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int data);
    }

    public DiscovereyAdapter(List<GoodsListBean.ResultBean> mData, Context context) {
        this.mData = mData;
        this.mContext = context;
    }


    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_discoverey, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        view.setOnClickListener(this);
        return vh;
    }


    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.ivGoods.setImageUrl(mData.get(position).getThumb());
        viewHolder.tvGoodName.setText(mData.get(position).getTitle());
//        viewHolder.tvGoodDes.setText(mData.get(position).getTitle());
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
        @BindView(R.id.iv_goods)
        SmartImageView ivGoods;
        @BindView(R.id.tv_good_name)
        TextView tvGoodName;
        @BindView(R.id.tv_good_des)
        TextView tvGoodDes;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}