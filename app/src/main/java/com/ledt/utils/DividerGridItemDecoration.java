package com.ledt.utils;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Lenovo on 2018/5/16.
 */

public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    public DividerGridItemDecoration() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        // TODO Auto-generated method stub
        super.getItemOffsets(outRect, view, parent, state);
    }

    @Override
    @Deprecated
    public void onDraw(Canvas c, RecyclerView parent) {
        // TODO Auto-generated method stub
        super.onDraw(c, parent);
    }

}
