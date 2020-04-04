package dev.tonini.trentinoconsegna.helpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
    private int mColor;
    private int mHeight; // in px format
    private Paint paint;

    public SimpleDividerItemDecoration(Context context, int color, int height) {
        this.mColor = color;
        this.mHeight = height;
        this.paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mHeight;

            paint.setColor(mColor);
            c.drawRect(left, top, right, bottom, paint);
        }
    }
}
