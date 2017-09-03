package com.ff.customview02;

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
 * 时间: 2017/8/31
 * 类作用:
 */

public class MyCustomView extends LinearLayout {
    public MyCustomView(Context context) {
        super(context);
        initView(context);
    }


    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(final Context context) {
        inflate(context, R.layout.customview, this);
        ImageView custom_img = (ImageView) findViewById(R.id.custom_img);
        TextView custom_tv = (TextView) findViewById(R.id.custom_tv);
        custom_img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "图片被点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
