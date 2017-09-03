package com.ff.customview02;

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
 * 时间: 2017/8/31
 * 类作用:
 */

public class MyView extends View {
    private float textSize;
    private int textColor;
    private String text;

    public MyView(Context context) {
        super(context);
        initView(null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    private void initView(@Nullable AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyView);
        text = typedArray.getString(R.styleable.MyView_text);
        textColor = typedArray.getColor(R.styleable.MyView_textColor, Color.YELLOW);
        textSize = typedArray.getDimension(R.styleable.MyView_textSize, 16);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setAntiAlias(true);
        canvas.drawText(text, 20, 50, paint);
    }
}
