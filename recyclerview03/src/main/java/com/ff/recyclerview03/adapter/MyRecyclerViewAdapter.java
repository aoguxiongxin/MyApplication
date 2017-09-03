package com.ff.recyclerview03.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ff.recyclerview03.R;

import java.util.ArrayList;

/**
 * 作者: 赵虔
 * 时间: 2017/8/31
 * 类作用:
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter
        .MyViewHolder> {

    private ArrayList<String> list;

    public MyRecyclerViewAdapter() {
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("item" + i);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //这里使用传入三个参数的,不然item的宽不能占满全屏
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent,
                false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(list.get(position));
        //item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null)
                    mItemClickListener.ItemClick(v, position);
            }
        });
        //item的长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemLongClickListener != null)
                    mItemLongClickListener.ItemLongClick(v, position);
                return true;
            }
        });
        /*//img点击事件,他和item的条目点击事件,只能存在一个
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null)
                    mItemClickListener.ItemClick(v, position);
            }
        });
        //img长按事件,他和item的条目长按事件,只能存在一个
        holder.img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemLongClickListener != null)
                    mItemLongClickListener.ItemLongClick(v, position);
                return true;
            }
        });*/
    }

    public OnItemClickListener mItemClickListener;
    public OnItemLongClickListener mItemLongClickListener;

    //定义这个方法,让适配器可以调用这个点击事件
    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    //定义这个方法,让适配器可以调用这个长按点击事件
    public void setOnItemLongClickListener(OnItemLongClickListener mItemLongClickListener) {
        this.mItemLongClickListener = mItemLongClickListener;
    }


    //自定义的点击事件接口
    public interface OnItemClickListener {
        void ItemClick(View v, int position);
    }

    //自定义的长按点击事件接口
    public interface OnItemLongClickListener {
        void ItemLongClick(View v, int position);
    }

    //增加
    public void add(int position) {
        list.add(position + 1, "这是新家的条目");
        //刷新,这种是从你修改的条目开始,到条目总数结束,当前position之前的条目不刷新
        notifyItemRangeChanged(position + 1, getItemCount());
    }

    //更新
    public void update(int position) {
        list.remove(position);
        list.add(position, "这是更新后的条目信息");
        //刷新,这种是从你修改的条目开始,到条目总数结束,当前position之前的条目不刷新
        notifyItemRangeChanged(position, getItemCount());
    }

    //删除
    public void delete(int position) {
        list.remove(position);
        //刷新,这种是从你删除的条目开始,到条目总数结束,当前position之前的条目不刷新
        notifyItemRangeRemoved(position, getItemCount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;
        private final ImageView img;
        private View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tv = (TextView) itemView.findViewById(R.id.item_tv);
            img = (ImageView) itemView.findViewById(R.id.item_img);
        }
    }
}
