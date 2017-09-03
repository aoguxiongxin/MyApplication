package com.example.animator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);
        layout = (LinearLayout) findViewById(R.id.layout);
    }

    public void onClick(View view) {
        //属性动画  ValueAnimator 的用法  ,属性动画本质是值的动画

        //用valueAnimator实现组合动画
       /* ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, 3f);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //得到属性动画变化的值设置给img
                float animatedValue = (float) animation.getAnimatedValue();
                //图片缩放
                ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
                layoutParams.width = (int) (animatedValue * 100);//数字根据自己需求来设置
                layoutParams.height = (int) (animatedValue * 100);
                img.setLayoutParams(layoutParams);
                //透明
                img.setAlpha(animatedValue);
            }
        });
        valueAnimator.start();*/


        //ObjectAnimator的用法,他是ValueAnimator的子类

        //透明动画
        /**
         * 参数  target 指的是 view,这里使用图片  img
         *  PropertyName 要执行的动画的名字,
         *  float...values 是多参数,要执行的动画的数值
         */
       /* ObjectAnimator alpha = ObjectAnimator.ofFloat(img, "alpha", 0f, 1f);
        alpha.setDuration(2000).setRepeatCount(2);
        //设置透明重复的样式,
        alpha.setRepeatMode(ValueAnimator.REVERSE);
        alpha.start();*/

        //缩放动画
       /* ObjectAnimator scaleX = ObjectAnimator.ofFloat(img, "scaleX", 1f, 3f);
        scaleX.setDuration(2000).setRepeatCount(2);
        //设置透明重复的样式,
        scaleX.setRepeatMode(ValueAnimator.REVERSE);
        scaleX.start();*/

      /*  //平移动画
        ObjectAnimator translationX = ObjectAnimator.ofFloat(img, "translationX", 0, 1000);
        translationX.setDuration(2000).setRepeatCount(2);
        translationX.start();*/

        // 旋转动画,以自己为中心旋转
       /* ObjectAnimator rotation = ObjectAnimator.ofFloat(img, "rotation", -50f, 50f);
        rotation.setDuration(1000).setRepeatCount(999);
        rotation.setRepeatMode(ValueAnimator.REVERSE);
        rotation.start();*/

        //直接使用view的animate()来设置动画,可以设置组合动画
        /*img.animate().alpha(0.5f)//设置透明度
                .setDuration(2000)//设置执行动画的时间
                .setStartDelay(1000)//设置延迟多久执行
                .rotation(360f)//设置旋转多少度
                .translationX(100f)//设置X轴移动距离
                .translationY(100f)//设置Y轴移动距离
                .scaleX(3f)//设置缩放倍数
                .scaleY(3f)//设置缩放倍数
                .start();*/

        //AnimatorSet 组合动画
       /* AnimatorSet animatorSet = new AnimatorSet();
        //设置两种以上的动画,组合到一起执行
        ObjectAnimator alpha = ObjectAnimator.ofFloat(img, "alpha", 0f, 1f, 0.5f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(img, "scaleX", 0f, 1f, 2f, 3f, 2f, 1f, 0f);
//        animatorSet.play(alpha).with(scaleX);//动画同时执行
//        animatorSet.play(alpha).before(scaleX);//先执行前面的动画,再执行后面的动画

//        animatorSet.playSequentially(alpha,scaleX);//先执行前面的动画,再执行后面的动画
        animatorSet.playTogether(alpha, scaleX);//同时执行
        animatorSet.setDuration(4000).start();*/

        //ObjectAnimator 来实现组合动画
     /*   PropertyValuesHolder alphaProperty = PropertyValuesHolder.ofFloat("alpha", 1f, 0.5f, 0f, 0.5f);
        PropertyValuesHolder scaleXProperty = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.5f, 2f, 1f);
        *//**
         * target 指的是要执行动画的控件,
         * PropertyValuesHolder  里面存放要执行的动画的propertyname,还有多参的float数值
         *//*
        ObjectAnimator.ofPropertyValuesHolder(img, alphaProperty, scaleXProperty).setDuration(3000).start();*/


        //改变颜色的动画,只限制API>21,模拟器版本需要大于21的,安卓5.0以上
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        *//*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)*//* {
            ValueAnimator valueAnimator = ValueAnimator.ofArgb(Color.YELLOW, Color.BLUE, Color.GRAY, Color.RED);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int animatedValue = (int) animation.getAnimatedValue();
                    layout.setBackgroundColor(animatedValue);
                }
            });
            valueAnimator.setDuration(3000).start();
        }
*/

        //调用xml文件中的objectAnimator动画,透明 alpha动画
      /*  Animator animator = AnimatorInflater.loadAnimator(this, R.animator.objectanimator);
        animator.setTarget(img);
        animator.setDuration(2000);
        animator.start();*/

        //调用xml文件中的valueAnimator动画,旋转动画
        /*ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(this, R.animator.valueanimator);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                img.setRotation(animatedValue);
            }
        });
        animator.start();*/

        //调用xml文件中的set组合动画,透明和旋转
        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.animator_set);
        animator.setTarget(img);
        animator.start();
    }
}
