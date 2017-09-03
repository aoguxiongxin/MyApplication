package com.ff.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者: 赵虔
 * 时间: 2017/8/30
 * 类作用:
 */

public class MyTextView extends View {
    private int TextColor;
    private String Text;
    private float TextSize;

    public MyTextView(Context context) {
        super(context);
        initView(null);

    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    private void initView(@Nullable AttributeSet attrs) {
        //得到咱们自定义的attrs中的数据
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyTextView);
        Text = typedArray.getString(R.styleable.MyTextView_Text);
        TextSize = typedArray.getDimension(R.styleable.MyTextView_TextSize, 15);
        TextColor = typedArray.getColor(R.styleable.MyTextView_TextColor, Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔的颜色,和大小
        Paint paint = new Paint();
        paint.setColor(TextColor);
        paint.setTextSize(TextSize);
        paint.setAntiAlias(true);
        /**
         * 画出来一个view
         * 由于画的是一个textview,第一个参数是就是text内容
         * 第一个参数距离 x坐标
         * 第二个参数是 距离 y坐标
         * 第三个参数是画笔
         */
        canvas.drawText(Text, 20, 50, paint);
    }
}
