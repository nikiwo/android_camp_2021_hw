package com.example.myactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("TAG", "MainActivity onCreate");
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG", "MainActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG", "MainActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("TAG", "MainActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TAG", "MainActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAG", "MainActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAG", "MainActivity onDestroy");
    }

    private void initView() {
        findViewById(R.id.btn).setOnClickListener(this);
        findViewById(R.id.simpleuibtn).setOnClickListener(this);
        findViewById(R.id.recyclerviewbtn).setOnClickListener(this);
        findViewById(R.id.baidubtn).setOnClickListener(this);
        findViewById(R.id.callbtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                Toast.makeText(MainActivity.this,"button click",Toast.LENGTH_SHORT).show();
                break;
            case R.id.simpleuibtn:
                Intent ui = new Intent(this,simpleui.class);
                startActivity(ui);
                break;
            case R.id.recyclerviewbtn:
                Intent recycle = new Intent(this,recyclerview.class);
                startActivity(recycle);
                break;
            case R.id.baidubtn:
                Intent baidu = new Intent(Intent.ACTION_VIEW);
                baidu.setData(Uri.parse("http://www.baidu.com"));
                startActivity(baidu);
                break;
            case R.id.callbtn:
                Intent call=new Intent(Intent.ACTION_DIAL);
                startActivity(call);
                break;
        }
    }


}
