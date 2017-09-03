package com.ff.recyclerview.Adapter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ff.recyclerview.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 作者: 赵虔
 * 时间: 2017/8/30
 * 类作用:RecyclerView的适配器,后面有泛型,必须是VIewHolder,所以自定义类继承viewholder
 */

public class MyAdapte extends RecyclerView.Adapter<MyAdapte.myViewHolder> {
    private List<String> list;
    private HashMap<Integer, Boolean> isCheckedHashMap;

    //无参构造方法初始化数据
    public MyAdapte() {
        isCheckedHashMap = new HashMap<>();
        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("条目" + i);
            isCheckedHashMap.put(i, false);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0)
            return 0;
        else
            return 1;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical,
                        parent, false);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal,
                        parent, false);
                break;
        }
//        View view = View.inflate(parent.getContext(), R.layout.item_horizontal, null);
        //得到RecyclerView布局,去判断,根据不用的布局,设置不同的item
       /* RecyclerView.LayoutManager layoutManager = ((RecyclerView) parent).getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            //必须使用这种布局,不然条目的宽不能铺满父布局
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical,
                    parent, false);
        } else if (layoutManager instanceof LinearLayoutManager) {
            //必须使用这种布局,不然条目的宽不能铺满父布局
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal,
                    parent, false);
        }*/
        return new myViewHolder(view);
    }


    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {
        holder.text.setText(list.get(position));
        //瀑布布局的时候使用,切换不同的图片
       /* if (position % 2 == 1)
            holder.img.setImageResource(R.mipmap.timg);
        else
            holder.img.setImageResource(R.mipmap.img02);*/

        //item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.ItemClick(v, position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mItemLongClickListener.ItemLongClick(v, position);
                return true;
            }
        });
        //图片的点击事件
        /*holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.ItemClick(v, position);
            }
        });
        holder.img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mItemLongClickListener.ItemLongClick(v, position);
                return true;
            }
        });*/

        //复选框设置点击事件,可以避免焦点的冲突
        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击的时候,得到当前下标key对应的values,如果是true,变false,反之一样
                isCheckedHashMap.put(position, !isCheckedHashMap.get(position));
            }
        });
        //设置他的状态为map集合中的values值
        holder.checkbox.setChecked(isCheckedHashMap.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    //全选
    public void checkedAll() {
        //用来判断是否有未选中的,如果有未选中的,就把未选中的选中,
//        如果全部都选中，则点击的时候设置全部不选中
        boolean isCheckedAll = false;
        //遍历集合,如果有未选中的,就停止循环遍历,isCheckedAll=true,
        // 如果全部选中,isCheckedAll=false,点击的时候会设置成全部未选中
        Set<Map.Entry<Integer, Boolean>> entries = isCheckedHashMap.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (!entry.getValue()) {
                isCheckedAll = true;
                break;
            }
        }
        //这里遍历的时候设置成isCheckedAll,上面判断如果未选中的就设置true,就设置成全选中
        //如果上面没有未选中的,意思就是都选中,isCheckedAll = false,checkbox就全部设置成false未选中
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(isCheckedAll);
        }
        notifyDataSetChanged();
    }

    //反选,如果选中,设置不选中,如果未选中,设置成选中
    public void reverseChecked() {
        Set<Map.Entry<Integer, Boolean>> entries = isCheckedHashMap.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(!entry.getValue());
        }
        notifyDataSetChanged();
    }

    //    自定义类继承viewholder
    public class myViewHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private TextView text;
        private final ImageView img;
        private final CheckBox checkbox;

        //上面再使用布局的时候实例化对象,会传进来一个自定义的布局View
        public myViewHolder(View itemView) {
            super(itemView);
            //这个就是item的一个条目
            this.itemView = itemView;
            //寻找控件记得使用itemView.findViewById()
            //相当于listview的convertView.findViewById();
            text = (TextView) itemView.findViewById(R.id.item_text);
            img = (ImageView) itemView.findViewById(R.id.item_img);
            checkbox = (CheckBox) itemView.findViewById(R.id.item_checkbox);
        }
    }

    //自定义接口,实现条目的单击和长按事件
    public OnItemClickListener mItemClickListener;
    public OnItemLongClickListener mItemLongClickListener;

    //自定义的点击事件接口
    public interface OnItemClickListener {
        void ItemClick(View v, int position);
    }

    //自定义的长按点击事件接口
    public interface OnItemLongClickListener {
        void ItemLongClick(View v, int position);
    }

    //定义这个方法,让适配器可以调用这个点击事件
    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    //定义这个方法,让适配器可以调用这个长按点击事件
    public void setOnItemLongClickListener(OnItemLongClickListener mItemLongClickListener) {
        this.mItemLongClickListener = mItemLongClickListener;
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
}
