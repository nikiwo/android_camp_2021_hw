package com.bytedance.practice5;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bytedance.practice5.model.Message;
import com.bytedance.practice5.model.MessageListResponse;
import com.bytedance.practice5.socket.SocketActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "chapter5";
    private FeedAdapter adapter = new FeedAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        findViewById(R.id.btn_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UploadActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_mine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(Constants.STUDENT_ID);
            }
        });

        findViewById(R.id.btn_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(null);
            }
        });
        findViewById(R.id.btn_socket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SocketActivity.class);
                startActivity(intent);
            }
        });



    }

    //TODO 2
    // 用HttpUrlConnection实现获取留言列表数据，用Gson解析数据，更新UI（调用adapter.setData()方法）
    // 注意网络请求和UI更新分别应该放在哪个线程中
    private void getData(String studentId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("GetMessage","preparing");
                List<Message> result = baseGetMessageFromRemote(studentId);
                Log.i("GetMassage","finish");
                if (result != null && !result.isEmpty()) {

                    new Handler(getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setData(result);
                        }
                    });

                }
            }
        }).start();
    }

    public List<Message> baseGetMessageFromRemote(String studentId){

        String urlStr = null;
        if (studentId != null)
        {
            urlStr = String.format("https://api-android-camp.bytedance.com/zju/invoke/messages?student_id=%s",studentId);
        }
        else{
            urlStr = "https://api-android-camp.bytedance.com/zju/invoke/messages?student_id=";
        }
        List<Message> result = null;

        try{
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            Log.i("GETMASSAGE","connecting");

            conn.setConnectTimeout(6000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("token",Constants.token);
            MessageListResponse feed;
            int  i = conn.getResponseCode();
            if (i == 200){
                Log.i("GETMASSAGE","connectsuccess");
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                Log.i("GETMASSAGE","readsuccess");
                feed = new Gson().fromJson(reader,new TypeToken<MessageListResponse>() {}.getType());
                result = feed.getFeeds();
                Log.i("GETMASSAGE","turnsuccess");
                reader.close();
                in.close();
                conn.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Looper.prepare();
            Toast.makeText(this,"網絡異常"+ e.toString(),Toast.LENGTH_SHORT).show();
            Looper.loop();

        }
        return result;
    }


}