package com.example.recyclerview02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.recyclerview02.Adapter.MyRecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(new MyRecyclerView());
        recyclerview.addItemDecoration(new MyItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    public void onClick(View view){
        RecyclerView.LayoutManager layoutManager = recyclerview.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerview.setLayoutManager(linearLayoutManager);
        }else if (layoutManager instanceof  LinearLayoutManager){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerview.setLayoutManager(gridLayoutManager);
        }
    }
}
