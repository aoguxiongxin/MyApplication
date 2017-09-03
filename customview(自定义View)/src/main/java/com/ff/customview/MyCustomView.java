package com.ff.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 作者: 赵虔
 * 时间: 2017/8/30
 * 类作用:
 */

public class MyCustomView extends LinearLayout {
    public MyCustomView(Context context) {
        super(context);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(final Context context) {
        inflate(context, R.layout.layout, this);
        ImageView img = (ImageView) findViewById(R.id.layout_image);
        TextView text = (TextView) img.findViewById(R.id.item_textview);
        img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "我被点击了", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
