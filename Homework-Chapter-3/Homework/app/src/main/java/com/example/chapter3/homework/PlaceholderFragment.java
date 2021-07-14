package com.example.chapter3.homework;



import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlaceholderFragment extends Fragment {

    ListView listView;
    LottieAnimationView lottie;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view =inflater.inflate(R.layout.fragment_placeholder, container, false);
        lottie = view.findViewById(R.id.Lottie);
        listView = view.findViewById(R.id.listview);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("List"+i);
        }

        lottie.setAlpha(1f);
        listView.setAlpha(0f);
        return view;

    }
    private String data[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"};
    protected void setData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator animatorout= ObjectAnimator.ofFloat(lottie, "alpha",1.0f,0.0f);
                animatorout.setDuration(1000);
                animatorout.start();
                ObjectAnimator List = ObjectAnimator.ofFloat(listView, "alpha",0.0f,1.0f);
                List.setDuration(1000);
                setData();
                List.start();

                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入

            }
        }, 2000);
    }
}
