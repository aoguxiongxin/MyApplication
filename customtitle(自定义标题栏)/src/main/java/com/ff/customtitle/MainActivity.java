package com.ff.customtitle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyTitleView viewById = (MyTitleView) findViewById(R.id.mytitleview);
        viewById.setOnMyTitleViewClickListener(new MyTitleView.OnMyTitleViewClickListener() {
            @Override
            public void OnClick(View view) {
                startActivity(new Intent(MainActivity.this,ScendActivity.class));
            }
        });

    }
}
