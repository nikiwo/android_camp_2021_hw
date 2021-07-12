package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("test", "test start");
        Button btn = findViewById(R.id.button1);
        final TextView tv = findViewById(R.id.tv_title);
        EditText user_name = findViewById(R.id.editText);

        if (TextUtils.isEmpty(user_name.getText().toString())){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv.setText("Sign in success!");
                }
            });
            Log.i("Button_Sign", "sign in success");
        }
        else{
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv.setText("Enter the password!");
                }
            });
            Log.i("Button_Sign", "password is empty");
        };
    }
}
