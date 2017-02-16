package com.example.codyclawson.paintapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by CodyClawson on 2/15/2017.
 */

public class TextTool implements CanvasTool{

        public int height;
        private Canvas mCanvas;
        private Paint mPaint;

        private ArrayList<textPoint> textPoints;
        private String text;


        private static ArrayList<View> views;


        public ArrayList<View> getViews() {
            return views;
        }

        public void setViews(ArrayList<View> views) {
            this.views = views;
        }


        public TextTool() {

            textPoints = new ArrayList<textPoint>();
            text = "Hello";
            // we set a new Path
            mPaint = new Paint();
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(2);
            mPaint.setColor(Color.BLACK);
            mPaint.setTextSize(20);

        }

        public void draw(Canvas canvas) {
            // draw the mPath with the mPaint on the canvas when onDraw
            for (textPoint p : textPoints) {
                canvas.drawText(p.text, p.p.x, p.p.y, mPaint);
            }
        }

        // when ACTION_DOWN start touch according to the x,y values
        private void startTouch(float x, float y) {
            textPoints.add(new textPoint(new Point(Math.round(x), Math.round(y)),text ));
        }

        // when ACTION_MOVE move touch according to the x,y values
        private void moveTouch(float x, float y) {

        }

        public void resetTool() {
            textPoints = new ArrayList<textPoint>();
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
//            mPaint.setStrokeWidth(width);
        }
    @Override
        public void setColor(int a, int r, int g, int b) {
            mPaint.setARGB(a, r, g, b);
        }
    @Override
        public void setString(String text) {
            this.text = text;
        }
    }

