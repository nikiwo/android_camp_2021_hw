package com.example.proj;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpacesItemDecoration(int space) {
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=0;
        outRect.right=0;
        outRect.bottom=space;
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=100;
        }
    }
}