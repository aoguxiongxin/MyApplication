package com.ff.customtitle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by 傲骨雄心 on 2017/9/1.
 */

public class MyTitleView extends LinearLayout {

    private ImageView img;
    private TextView title_text;
    private RelativeLayout layout;
    private int bg_color;
    private int title_textColor;
    private String Text;
    private float title_textSize;
    private Drawable title_image;

    public MyTitleView(Context context) {
        super(context);
        initView(context, null);
    }

    public MyTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, @Nullable AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.mytitle, this);
        img = (ImageView) view.findViewById(R.id.Title_icon);
        title_text = (TextView) view.findViewById(R.id.Title_ext);
        layout = (RelativeLayout) view.findViewById(R.id.layout);
        if (attrs == null) {
            return;
        }
        //得到attrs布局文件
        initAttrs(context, attrs);
        //把得到的atrrs属性设置到控件上
        setViewContent();
//        创建点击事件
        img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                myTitleViewClickListener.OnClick(v);
            }
        });

    }

    private OnMyTitleViewClickListener myTitleViewClickListener;

    public interface OnMyTitleViewClickListener {
        void OnClick(View view);
    }

    public void setOnMyTitleViewClickListener(OnMyTitleViewClickListener myTitleViewClickListener) {
        this.myTitleViewClickListener = myTitleViewClickListener;
    }

    //把得到的atrrs属性设置到控件上
    private void setViewContent() {
        img.setImageDrawable(title_image);
        layout.setBackgroundColor(bg_color);
        title_text.setText(Text);
        title_text.setTextColor(title_textColor);
        title_text.setTextSize(title_textSize);
    }

    //得到attrs布局文件
    private void initAttrs(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTitleView);
        bg_color = typedArray.getColor(R.styleable.MyTitleView_Layout_bg_Color, Color.RED);
        title_textColor = typedArray.getColor(R.styleable.MyTitleView_Title_TextColor, Color.BLACK);
        Text = typedArray.getString(R.styleable.MyTitleView_Title_Text);
        title_textSize = typedArray.getDimension(R.styleable.MyTitleView_Title_TextSize, 15);
        title_image = typedArray.getDrawable(R.styleable.MyTitleView_Title_Image);
    }

}
