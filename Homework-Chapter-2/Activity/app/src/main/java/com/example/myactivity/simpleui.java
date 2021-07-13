package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class simpleui extends AppCompatActivity {

    private static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpleui);
        Log.i(TAG, "simpleui onCreate");

        View v= findViewById(R.id.background);//设置背景透明度
        v.getBackground().setAlpha(100);
        v= findViewById(R.id.goal1);//设置圖片透明度
        v.getBackground().setAlpha(100);
    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG, "simpleui onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "simpleui onResume");
    }
}