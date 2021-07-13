package com.example.myactivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class recyclerview extends AppCompatActivity implements MyAdapter.IOnItemClickListener {

    private static final String TAG = "TAG";

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        Log.i(TAG, "recyclerview onCreate");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        TextView time =(TextView)this.findViewById(R.id.time);
        time.setText("更新于"+simpleDateFormat.format(date));

        initView();
    }

    private void initView() {
        //获取实例
        recyclerView = findViewById(R.id.recycler);
        //更改数据时不会变更宽高
        recyclerView.setHasFixedSize(true);
        //创建线性布局管理器
        layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //创建Adapter
        mAdapter = new MyAdapter(TestDataSet_Right.getData());
        //设置Adapter每个item的点击事件
        mAdapter.setOnItemClickListener(this);
        //设置Adapter
        recyclerView.setAdapter(mAdapter);
        //动画
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(3000);
        recyclerView.setItemAnimator(animator);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "recyclerview onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "recyclerview onResume");
    }


    @Override
    public void onItemCLick(int position, TestData data) {
        Toast.makeText(recyclerview.this, "点击了第" + position + "条", Toast.LENGTH_SHORT).show();
        mAdapter.addData(position + 1, new TestData("在打了在打了", "0w"));
    }

    @Override
    public void onItemLongCLick(int position, TestData data) {
        Toast.makeText(recyclerview.this, "长按了第" + position + "条", Toast.LENGTH_SHORT).show();
        mAdapter.removeData(position);
    }
}