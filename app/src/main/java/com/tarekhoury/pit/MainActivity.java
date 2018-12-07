package com.tarekhoury.pit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.tarekhoury.pit.views.Pit;
import com.tarekhoury.pit.views.Point;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

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
            pit.addView(point);
        }

        // Add new point
        addPoint.setOnClickListener(v -> {
            Point p = new Point(this);
            // new points always start at position (0,0)
            p.setXY(0, 0);
            pit.addView(p);
        });
    }
}