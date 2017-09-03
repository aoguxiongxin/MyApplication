package com.ff.customtitle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ScendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scendactivity);
        MyTitleView viewById = (MyTitleView) findViewById(R.id.mytitleview);
        viewById.setOnMyTitleViewClickListener(new MyTitleView.OnMyTitleViewClickListener() {
            @Override
            public void OnClick(View view) {
                finish();
            }
        });

    }
}
