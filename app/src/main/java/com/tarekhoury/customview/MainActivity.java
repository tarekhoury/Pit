package com.tarekhoury.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.tarekhoury.customview.views.Pit;
import com.tarekhoury.customview.views.Point;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private Pit pit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pit = findViewById(R.id.pit);
        Button addPoint = findViewById(R.id.add);

        Random random = new Random();

        // Generate and draw 5 random points
        for (int i = 0; i < 5; i++) {
            Point point = new Point(this);
            point.setXY(random.nextInt(900) - 450, random.nextInt(600) - 300);
            point.setOnTouchListener(this);
            pit.addView(point);
        }

        // Add new point
        addPoint.setOnClickListener(v -> {
            Point p = new Point(this);
            // new points always start at position (0,0)
            p.setXY(0, 0);
            p.setOnTouchListener(this);
            pit.addView(p);
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        Point point = (Point) v;

        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:
                point.setXY(
                        (int) (point.getPointX() + event.getX()) - Point.RADIUS,
                        (int) (point.getPointY() - event.getY()) + Point.RADIUS);

                point.requestLayout();
                point.invalidate();
                break;
            case MotionEvent.ACTION_UP:
                v.performClick();
                break;
        }

        return true;
    }
}