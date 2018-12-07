package com.tarekhoury.customview.utility;

import android.content.res.Resources;

public class ConversionUtils {

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int limitToRange(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }
}
