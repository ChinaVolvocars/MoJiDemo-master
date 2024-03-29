package com.emmanuel.mojidemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by user on 2016/10/19.
 */
public class IndexHorizontalScrollView1 extends HorizontalScrollView {

    private static final String TAG = "IndexHorizontal";
    private Paint textPaint;
    private Today24HourView today24HourView;

    public IndexHorizontalScrollView1(Context context) {
        this(context, null);
    }

    public IndexHorizontalScrollView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexHorizontalScrollView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        textPaint = new Paint();
        textPaint.setTextSize(DisplayUtil.sp2px(getContext(), 12));
        textPaint.setAntiAlias(true);
        textPaint.setColor(new Color().WHITE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        int offset = computeHorizontalScrollOffset();
//        int maxOffset = computeHorizontalScrollRange() - DisplayUtil.getScreenWidth(getContext());
//        if(today24HourView != null){
////            today24HourView.drawLeftTempText(canvas, offset);
//            today24HourView.setScrollOffset(offset, maxOffset);
//        }
    }

//    public void setToday24HourView(Today24HourView today24HourView){
//        this.today24HourView = today24HourView;
//    }
}
