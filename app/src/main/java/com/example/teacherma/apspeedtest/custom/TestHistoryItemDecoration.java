package com.example.teacherma.apspeedtest.custom;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author teacherMa on 2017/11/2.
 */

public class TestHistoryItemDecoration extends RecyclerView.ItemDecoration {
    private static final int BOTTOM_PIXEL = 24;

    public TestHistoryItemDecoration() {
        super();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) != parent.getChildCount() - 1) {
            outRect.bottom = BOTTOM_PIXEL;
        }
    }
}
