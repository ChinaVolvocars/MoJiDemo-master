package com.emmanuel.mojidemo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class Rectangle30View extends View {

    public static final int MARGIN_LEFT_ITEM = (int) dp2px(14);
    public static final int MARGIN_RIGHT_ITEM = (int) dp2px(14);
    public static final int BOTTOM_TEXT_WIDTH = (int) dp2px(55);
    public static final int BOTTOM_TEXT_HEIGHT = (int) dp2px(29);
    public static final int BOTTOM_TEXT_MARGIN_TOP = (int) dp2px(7);
    public static final int BOTTOM_TEXT_MARGIN_LIFT = (int) dp2px(10);
    public static final int BOTTOM_TEXT_MARGIN_RIGHT = (int) dp2px(10);
    public static final int RECTANGLE_WIDTH = (int) dp2px(23);
    public static final int RECTANGLE_MARGIN = (int) dp2px(28);
    public static final int DOTTED_LINE_MARGIN = (int) dp2px(40);


    private int rectangleColor = Color.parseColor("#e9e9e9");
    private int dottedLineColor = Color.parseColor("#50abff");
    private int dashedColor = Color.parseColor("#e9e9e9");

    private int bottomLineColor = Color.parseColor("#969FA9");
    private int textColor = Color.parseColor("#333333");
    private int dottedLineHeight = (int) dp2px(1);
    private int bottomLineHeight = (int) dp2px(1);
    private float textSize = sp2px(11);

    Paint dashedPaint = new Paint();
    Paint bottomLinePaint = new Paint();

    private int mHeight, mWidth;
    private int itemCount = 30;

    public Rectangle30View(Context context) {
        this(context, null);
    }

    public Rectangle30View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Rectangle30View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mWidth = MARGIN_LEFT_ITEM + itemCount * BOTTOM_TEXT_WIDTH + MARGIN_RIGHT_ITEM;
        mHeight = MARGIN_LEFT_ITEM + DOTTED_LINE_MARGIN * 4 + BOTTOM_TEXT_HEIGHT;

        //虚线
        dashedPaint.setAntiAlias(true);
        dashedPaint.setStyle(Paint.Style.STROKE);
        dashedPaint.setStrokeWidth(dp2px(1));
        dashedPaint.setColor(dashedColor);
        dashedPaint.setPathEffect(new DashPathEffect(new float[]{6, 6}, 0));

        //底部的线
        bottomLinePaint.setAntiAlias(true);
        bottomLinePaint.setStrokeWidth(dp2px(1));
        bottomLinePaint.setColor(bottomLineColor);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画虚线

        for (int i = 0; i < 4; i++) {
            canvas.drawLine(
                    MARGIN_LEFT_ITEM,
                    MARGIN_LEFT_ITEM + DOTTED_LINE_MARGIN * i,
                    MARGIN_LEFT_ITEM + itemCount * BOTTOM_TEXT_WIDTH,
                    MARGIN_LEFT_ITEM + DOTTED_LINE_MARGIN * i,
                    dashedPaint
            );
        }
        canvas.drawLine(
                MARGIN_LEFT_ITEM,
                MARGIN_LEFT_ITEM + DOTTED_LINE_MARGIN * 4,
                MARGIN_LEFT_ITEM + itemCount * BOTTOM_TEXT_WIDTH,
                MARGIN_LEFT_ITEM + DOTTED_LINE_MARGIN * 4,
                bottomLinePaint
        );


        //画底部的线
        //画文字
        //画柱子
        //画 浮动的框
        //画 浮动框中的文字

    }

    /**
     * dp单位转换成 px
     *
     * @param dp
     * @return
     */
    public static float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * sp 转换成 px
     *
     * @param sp
     * @return
     */
    public float sp2px(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * 获取屏幕宽度
     *
     * @return width of the screen.
     */
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return heiht of the screen.
     */
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
