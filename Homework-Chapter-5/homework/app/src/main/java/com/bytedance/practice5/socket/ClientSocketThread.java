package com.bytedance.practice5.socket;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;

public class ClientSocketThread extends Thread {

    public ClientSocketThread(SocketActivity.SocketCallback callback) {
        this.callback = callback;
    }

    private SocketActivity.SocketCallback callback;
    //head请求内容
    private static String content = "HEAD / HTTP/1.1\r\nHost:www.zju.edu.cn\r\n\r\n";

    @Override
    public void run() {
        // TODO 6 用socket实现简单的HEAD请求（发送content）
        //  将返回结果用callback.onresponse(result)进行展示
        String result;
        int receivedLen;
        byte[] data=new byte[1024*5];
        try{
            Socket socket=new Socket("www.zju.edu.cn",80);
            Log.i("Socket","connect");
            BufferedInputStream is = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream os= new BufferedOutputStream(socket.getOutputStream());
            os.write(content.getBytes());
            os.flush();
            receivedLen=is.read(data);
            result=new String(data,0,receivedLen);
            Log.d("HEAD","reveive"+result);
            callback.onResponse(result);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}