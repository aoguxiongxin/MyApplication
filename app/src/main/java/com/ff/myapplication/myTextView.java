package com.ff.myapplication;

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
 * 类作用:自定义一个简单的TextView,设置他的Text和TextSize和TextColor
 */

public class myTextView extends View {
    private String text;
    private float textSize;
    private int textColor;

    public myTextView(Context context) {
        super(context);
        initView(null);
    }


    public myTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    private void initView(@Nullable AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.myTextView);
        text = typedArray.getString(R.styleable.myTextView_Text);
        textSize = typedArray.getDimension(R.styleable.myTextView_TextSize, 15);
        textColor = typedArray.getColor(R.styleable.myTextView_TextColor, Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        paint.setAntiAlias(true);
        canvas.drawText(text, 20, 100, paint);

    }
}
