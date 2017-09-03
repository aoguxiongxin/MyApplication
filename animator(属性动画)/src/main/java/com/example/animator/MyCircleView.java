package com.example.animator;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by hasee on 2017/9/1.
 */

public class MyCircleView extends View {

    Paint paint;//画笔
    float radius = 100;//圆心

    Animator_object currentAnimatorobject;

    public MyCircleView(Context context) {
        super(context);
        init();
    }

    public MyCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //设置画笔的颜色
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);

    }

    //根据currentPoint的x和y确定圆心的位置,去画圆
    private void drawCirle(Canvas canvas) {

        //只执行一次动画
        if (currentAnimatorobject == null) {
            startPointAnimation();
        }
        canvas.drawCircle(currentAnimatorobject.getX(), currentAnimatorobject.getY(), radius, paint);
    }


    //开始执行对Point对象操作的动画
    private void startPointAnimation() {
        //这个对象开始的位置,屏幕左上角
        Animator_object startAnimatorobject = new Animator_object(radius, radius);
        //对象结束的位置,屏幕右下角
        Animator_object endAnimatorobject = new Animator_object(getWidth() - radius, getHeight() - radius);
        currentAnimatorobject = startAnimatorobject;

        //fraction是一个从0到1变化的值
        //设置动画
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new TypeEvaluator<Animator_object>() {
            @Override
            public Animator_object evaluate(float fraction, Animator_object startValue, Animator_object endValue) {
                float startX = startValue.getX();
                float endX = endValue.getX();
                float currentX = startX + fraction * (endX - startX);

                float startY = startValue.getY();
                float endY = endValue.getY();
                float currentY = startY + fraction * (endY - startY);

                return new Animator_object(currentX, currentY);
            }
        }, startAnimatorobject, endAnimatorobject);

        //设置监听,获取当前的监听对象
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentAnimatorobject = (Animator_object) animation.getAnimatedValue();

                //重新绘制,   (改变了currentPoint之后去重新绘制圆)
                invalidate();
            }
        });

        //设置动画插值器,改变动画变化速率 , 加速,先加速后减速,弹跳效果
        //这行加速器可以删除
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //去画圆
        drawCirle(canvas);
    }
}
