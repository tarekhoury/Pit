package com.tarekhoury.customview.utility;

import android.content.res.Resources;

import com.tarekhoury.customview.views.Point;

public class ConversionUtils {

    /**
     * Convert from point X value to a screen position X value
     */
    public static int transformX(int x, int canvasWidth) {
        return x + (canvasWidth / 2) - Point.RADIUS;
    }

    /**
     * Convert from point Y value to a screen position Y value
     */
    public static int transformY(int y, int canvasHeight) {
        return (y * -1) + (canvasHeight / 2) - Point.RADIUS;
    }

    /**
     * Convert from pixels to device dp
     */
    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * Convert from device dp to pixels
     */
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * Limit a value to a range of numbers
     * if value is larger than max then we return max
     * if value is smaller than min then we return min
     * if value is between min and max then we return value
     */
    public static int limitToRange(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }
}
