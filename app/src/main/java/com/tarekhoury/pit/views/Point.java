package com.tarekhoury.pit.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tarekhoury.pit.utility.ConversionUtils;

public class Point extends View implements Comparable<Point> {

    // Fixed point size (can be dynamic)
    public final static int POINT_SIZE = 30;
    public final static int RADIUS = POINT_SIZE / 2;

    private final Paint pointPaint;
    private int pointX;
    private int pointY;

    public Point(Context context) {
        this(context, null);
    }

    public Point(Context context, AttributeSet attrs) {
        super(context, attrs);

        pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointPaint.setColor(Color.CYAN);
    }

    public void setXY(int x, int y) {
        pointX = x;
        pointY = y;
    }

    public int getPointX() {
        return pointX;
    }

    public int getPointY() {
        return pointY;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Pit parent = (Pit) getParent();

        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:

                int x = ConversionUtils.limitToRange(
                        (int) (getPointX() + event.getX() - RADIUS),
                        parent.getWidth() / 2 * -1,
                        parent.getWidth() / 2);

                int y = ConversionUtils.limitToRange(
                        (int) (getPointY() - event.getY() + RADIUS),
                        parent.getHeight() / 2 * -1,
                        parent.getHeight() / 2);

                setXY(x, y);
                requestLayout();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                parent.sortPoints();
                parent.invalidate();
                break;
        }

        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = resolveSize(POINT_SIZE, widthMeasureSpec);
        int height = resolveSize(POINT_SIZE, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(RADIUS, RADIUS, RADIUS, pointPaint);
    }

    @Override
    public int compareTo(Point o) {
        return Integer.compare(pointX, o.getPointX());
    }

    @NonNull
    @Override
    public String toString() {
        return "X = " + pointX + ", Y = " + pointY;
    }
}