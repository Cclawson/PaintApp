package com.example.codyclawson.paintapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by CodyClawson on 2/15/2017.
 */

public class CircleDrawTool implements CanvasTool {

    public int height;
    private Canvas mCanvas;
    private Paint mPaint;

    private ArrayList<Point> circlePoints;


    private static ArrayList<View> views;


    public ArrayList<View> getViews() {
        return views;
    }

    public void setViews(ArrayList<View> views) {
        this.views = views;
    }


    public CircleDrawTool() {
        circlePoints = new ArrayList<Point>();

        // we set a new Path
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void draw(Canvas canvas) {
        // draw the mPath with the mPaint on the canvas when onDraw
        for (Point p : circlePoints) {
            canvas.drawCircle(p.x, p.y, 20, mPaint);
        }
    }

    // when ACTION_DOWN start touch according to the x,y values
    private void startTouch(float x, float y) {
        circlePoints.add(new Point(Math.round(x), Math.round(y)));
    }

    // when ACTION_MOVE move touch according to the x,y values
    private void moveTouch(float x, float y) {

    }

    public void resetTool() {
        circlePoints = new ArrayList<Point>();
    }

    // when ACTION_UP stop touch
    private void upTouch() {
    }

    public void updateTool(Canvas c, int w, int h) {
        mCanvas = c;
    }

    @Override
    public boolean sendMotionEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                break;
        }
        return true;
    }
    @Override

    public void setWidth(float width) {
        mPaint.setStrokeWidth(width);
    }
    @Override

    public void setColor(int a, int r, int g, int b) {
        mPaint.setARGB(a, r, g, b);
    }
    @Override

    public void setString(String text) {

    }
}
