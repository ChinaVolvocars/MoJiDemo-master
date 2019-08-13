package com.emmanuel.mojidemo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Rectangle30View extends View {

    public static final int MARGIN_LEFT_ITEM = (int) dp2px(14);
    public static final int RECTANGLE_MARGIN_LEFT = (int) dp2px(21);
    public static final int MARGIN_RIGHT_ITEM = (int) dp2px(14);
    public static final int BOTTOM_TEXT_WIDTH = (int) dp2px(33);
    public static final int BOTTOM_TEXT_HEIGHT = (int) dp2px(29);
    public static final int BOTTOM_TEXT_MARGIN_TOP = (int) dp2px(7);
    public static final int BOTTOM_TEXT_MARGIN_LIFT = (int) dp2px(10);
    public static final int BOTTOM_TEXT_MARGIN_RIGHT = (int) dp2px(10);
    public static final int RECTANGLE_WIDTH = (int) dp2px(23);
    public static final int RECTANGLE_MARGIN = (int) dp2px(28);
    public static final int DOTTED_LINE_MARGIN = (int) dp2px(40);


    private int rectangleColor = Color.parseColor("#50ABFF");
    private int dottedLineColor = Color.parseColor("#50abff");
    private int dashedColor = Color.parseColor("#e9e9e9");
    private int bottomTextColor = Color.parseColor("#333333");

    private int bottomLineColor = Color.parseColor("#969FA9");
    private int textColor = Color.parseColor("#333333");
    private int dottedLineHeight = (int) dp2px(1);
    private int bottomLineHeight = (int) dp2px(1);
    private float textSize = sp2px(11);

    Paint dashedPaint = new Paint();
    Paint bottomLinePaint = new Paint();
    private Paint textPaint = new Paint();
    private Paint rectanglePaint = new Paint();

    private int mHeight, mWidth;
    private int itemCount = 10;
    private int tempBaseTop;  //折线的上边Y坐标
    private int tempBaseBottom; //折线的下边Y坐标


    private double maxTemp = 26;
    private double minTemp = 5;
    private List<Rectangle30Item> itemList = new ArrayList<>();

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

        itemList = new ArrayList<>();
        Rectangle30Item materials30Item;
        Random random = new Random();

        for (int i = 0; i < itemCount; i++) {
            materials30Item = new Rectangle30Item();
            double s = random.nextInt(26) % (maxTemp - minTemp + 1) + minTemp;
            materials30Item.setTon(s);
            materials30Item.setTime("07-" + i);
            itemList.add(materials30Item);
        }

        mWidth = MARGIN_LEFT_ITEM + MARGIN_RIGHT_ITEM + itemCount * (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT);
        mHeight = MARGIN_LEFT_ITEM + DOTTED_LINE_MARGIN * 4 + BOTTOM_TEXT_HEIGHT;

        tempBaseTop = MARGIN_LEFT_ITEM;
        tempBaseBottom = MARGIN_LEFT_ITEM + DOTTED_LINE_MARGIN * 4;

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

        //底部的文字
        textPaint.setTextSize(sp2px(12));
        textPaint.setColor(bottomTextColor);
        textPaint.setAntiAlias(true);
        //柱子
        rectanglePaint.setStyle(Paint.Style.FILL);
        rectanglePaint.setColor(rectangleColor);
        rectanglePaint.setAntiAlias(true);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MARGIN_LEFT_ITEM + MARGIN_RIGHT_ITEM + itemCount * (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画虚线和底部的线
        //画文字
        onDrawBottomText(canvas);
        //画柱子
        onDrawRectangle(canvas);
        //画 浮动的框
        //画 浮动框中的文字

    }

    private void onDrawLine(Canvas canvas) {
        onDrawLine(canvas);
        for (int i = 0; i < 4; i++) {
            canvas.drawLine(
                    MARGIN_LEFT_ITEM,
                    MARGIN_LEFT_ITEM + DOTTED_LINE_MARGIN * i,
                    MARGIN_LEFT_ITEM + itemCount * (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT),
                    MARGIN_LEFT_ITEM + DOTTED_LINE_MARGIN * i,
                    dashedPaint
            );
        }
        //画底部的线
        canvas.drawLine(
                MARGIN_LEFT_ITEM,
                MARGIN_LEFT_ITEM + DOTTED_LINE_MARGIN * 4,
                MARGIN_LEFT_ITEM + itemCount * (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT),
                MARGIN_LEFT_ITEM + DOTTED_LINE_MARGIN * 4,
                bottomLinePaint
        );
    }

    private void onDrawRectangle(Canvas canvas) {

        ArrayList<Double> temp = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            double countTon = itemList.get(i).getTon();
            temp.add(countTon);
        }

        maxTemp = Collections.max(temp);
        minTemp = Collections.min(temp);


        for (int i = 0; i < itemCount; i++) {
            int left = (int) dp2px(10) + MARGIN_LEFT_ITEM + i * (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT);
            int right = left + (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT) - (int) dp2px(10) * 2;
            Point point = calculateTempPoint(left, right, (int) itemList.get(i).getTon());
            int top = point.y;
            int bottom = (DOTTED_LINE_MARGIN * 4 + MARGIN_LEFT_ITEM - (int) dp2px(1));
            Rect rect = new Rect(left, top, right, bottom);
            canvas.drawRect(rect, rectanglePaint);
        }

    }

    // [16, 13, 17, 12, 25, 12, 23, 26, 16, 13, 18, 19, 22, 19, 21, 14, 14, 19, 17, 23, 17, 14, 13, 18, 19, 19, 13, 14, 12, 11, 11]
    private Point calculateTempPoint(int left, int right, int temp) {
        double minHeight = tempBaseTop;
        double maxHeight = tempBaseBottom;
        double tempY = maxHeight - (temp - minTemp) * 1.0 / (maxTemp - minTemp) * (maxHeight - minHeight);
        Point point = new Point((left + right) / 2, (int) tempY);
        return point;
    }

    private void onDrawBottomText(Canvas canvas) {
        for (int i = 0; i < itemCount; i++) {
            int left = MARGIN_LEFT_ITEM + i * (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT);
            int right = left + (BOTTOM_TEXT_WIDTH + BOTTOM_TEXT_MARGIN_LIFT + BOTTOM_TEXT_MARGIN_RIGHT) - 1;
            int top = DOTTED_LINE_MARGIN * 4;
            int bottom = DOTTED_LINE_MARGIN * 4 + BOTTOM_TEXT_HEIGHT;
            Rect rect = new Rect(left, top, right, bottom);
            Rect targetRect = new Rect(rect.left, rect.bottom, rect.right, rect.bottom);
            Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
            int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            textPaint.setTextAlign(Paint.Align.CENTER);

//            String text = itemList.get(i).getTime();
            String text = "10-" + i;
            canvas.drawText(text, targetRect.centerX(), baseline, textPaint);

        }
    }

    public void setData(List<Rectangle30Item> itemList) {
        this.itemList = itemList;
        itemCount = itemList.size();
        invalidate();
        requestLayout();
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
