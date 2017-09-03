package com.ff.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.ff.recyclerview.Adapter.MyAdapte;

/**
 * 主界面布局上面几个按钮,下面就是RecyclerView,通过点击按钮实现相关效果
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private MyAdapte adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        //线性布局,和listview显示的效果一样
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //和gridView显示效果一样,第二个参数是显示几列
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        //设置gridview显示的时候每行显示的个数
       /* gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 3 - position % 3;
            }
        });*/
        //瀑布的布局.第一个参数是显示几列,第二个参数是排列的方向
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);

        //设置布局的类型,不设置的话界面不会有任何显示
        recyclerview.setLayoutManager(linearLayoutManager);
        //设置适配器
        adapter = new MyAdapte();
        recyclerview.setAdapter(adapter);
        //自定义的条目点击事件,因为recyclerview没有自带条目点击事件
        adapter.setOnItemClickListener(new MyAdapte.OnItemClickListener() {
            @Override
            public void ItemClick(View v, int position) {
                adapter.add(position);
                Toast.makeText(MainActivity.this, "条目被点击了", Toast.LENGTH_SHORT).show();
            }
        });
        //自定义的条目点击事件,因为recyclerview没有自带条目点击事件
        adapter.setOnItemLongClickListener(new MyAdapte.OnItemLongClickListener() {
            @Override
            public void ItemLongClick(View v, int position) {
                adapter.delete(position);
                Toast.makeText(MainActivity.this, "条目被长按了", Toast.LENGTH_SHORT).show();
            }
        });

        //设置item中间的分割线
        recyclerview.addItemDecoration(new MyDecoration(this, LinearLayoutManager.VERTICAL));

    }

    //点击事件,切换单排显示,或者多排显示,
    //也就是切换ListVIew显示或GridView显示
    public void onClick(View view) {
        //得到当前布局
        RecyclerView.LayoutManager layoutManager = recyclerview.getLayoutManager();
        //因为GridLayoutManager继承自LinearLayoutManager,
        //判断的时候先判断小范围的,再判断大范围,所以if先判断GridLayoutManager,
        // else if再判断LinearLayoutManager,这样才能切换,如果顺序反之则切换布局失败
        if (layoutManager == null)
            return;
        if (layoutManager instanceof GridLayoutManager) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerview.setLayoutManager(linearLayoutManager);
        } else if (layoutManager instanceof LinearLayoutManager) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            recyclerview.setLayoutManager(gridLayoutManager);
        }
        //此处必须重新设置适配器,刷新适配器也无效,否则布局会错乱
        recyclerview.setAdapter(adapter);
    }

    //全选按钮点击事件,如果全选,点击设置成全不选中,如果有未选中的则设置成全部选中
    public void checkAll(View view) {
        adapter.checkedAll();
    }

    //反选,如果选中,设置不选中,如果未选中,设置成选中
    public void reverseCheck(View view) {
        adapter.reverseChecked();
    }
}

