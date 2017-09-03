package com.ff.recyclerview03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ff.recyclerview03.adapter.MyRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerview.setLayoutManager(linearLayoutManager);
        adapter = new MyRecyclerViewAdapter();
        recyclerview.setAdapter(adapter);
//        条目的点击事件.recycler没有,所以需我们自定义
        adapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void ItemClick(View v, int position) {
                adapter.add(position);
                Toast.makeText(MainActivity.this, position + "被点击了", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        adapter.setOnItemLongClickListener(new MyRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void ItemLongClick(View v, int position) {
//                adapter.update(position);
                adapter.delete(position);
                Toast.makeText(MainActivity.this, position + "长按了", Toast.LENGTH_SHORT)
                        .show();
            }
        });


        //设置每一个item中间的红线
        recyclerview.addItemDecoration(new MyDecoration(this, LinearLayoutManager.VERTICAL));
    }

    public void onClick(View view) {
        RecyclerView.LayoutManager layoutManager = recyclerview.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerview.setLayoutManager(linearLayoutManager);
        } else if (layoutManager instanceof LinearLayoutManager) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 3);
            recyclerview.setLayoutManager(gridLayoutManager);
        }

    }
}
