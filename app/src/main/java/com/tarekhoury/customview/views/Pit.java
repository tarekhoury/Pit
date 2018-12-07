package com.tarekhoury.customview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.tarekhoury.customview.utility.ConversionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pit extends ViewGroup {

    private final Paint axisLinePaint;
    private final Paint pointLinePaint;

    private int canvasWidth;
    private int canvasHeight;

    //TODO Consider usage of SortedSet
    private List<Point> pointList = new ArrayList<>();

    public Pit(Context context) {
        this(context, null);
    }

    public Pit(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Flag to allow ViewGroup to draw (needed to draw the axis)
        setWillNotDraw(false);

        axisLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        axisLinePaint.setColor(Color.WHITE);
        axisLinePaint.setStrokeWidth(2);

        pointLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pointLinePaint.setColor(Color.RED);
        pointLinePaint.setStrokeWidth(2);
    }

    @Override
    public void addView(View child) {
        // Keep all child views in a sorted list so we can easily draw them
        pointList.add((Point) child);
        Collections.sort(pointList);
        super.addView(child);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        measureChildren(
                MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.AT_MOST));

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        canvasWidth = getWidth();
        canvasHeight = getHeight();

        for (Point point : pointList) {
            int pointX = ConversionUtils.transformX(point.getPointX(), canvasWidth);
            int pointY = ConversionUtils.transformY(point.getPointY(), canvasHeight);

            point.layout(pointX, pointY, point.getMeasuredWidth() + pointX, point.getMeasuredHeight() + pointY);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw the X and Y axis according to the canvas size
        canvas.drawLine(canvasWidth / 2, 0, canvasWidth / 2, canvasHeight, axisLinePaint);
        canvas.drawLine(0, canvasHeight / 2, canvasWidth, canvasHeight / 2, axisLinePaint);

        // Draw lines between points
        for (int i = 0; i < pointList.size() - 1; i++) {
            // Assuming all children are of type Point
            Point point1 = pointList.get(i);
            Point point2 = pointList.get(i + 1);

            // if point2 is null, then point1 is the last child.
            if (point2 == null) {
                break;
            }

            canvas.drawLine(
                    ConversionUtils.transformX(point1.getPointX(), canvasWidth) + Point.RADIUS,
                    ConversionUtils.transformY(point1.getPointY(), canvasHeight) + Point.RADIUS,
                    ConversionUtils.transformX(point2.getPointX(), canvasWidth) + Point.RADIUS,
                    ConversionUtils.transformY(point2.getPointY(), canvasHeight) + Point.RADIUS,
                    pointLinePaint);
        }
    }
}