package com.example.codyclawson.paintapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;

/**
 * Created by BrandonBerning on 2/15/2017.
 */

public class EraserTool implements CanvasTool{

    public int height;
    private Canvas mCanvas;
    private Path mPath;
    private Path mPath2;
    private Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;

    public EraserTool() {
        // we set a new Path
        mPath = new Path();
        mPath2 = new Path();

        // and we set a new Paint with the desired attributes
        mPaint = new Paint();

        mPaint.setStrokeWidth(20f);
        mPaint.setAlpha(0x00);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    public void draw(Canvas canvas) {
        // draw the mPath with the mPaint on the canvas when onDraw
        canvas.drawPath(mPath, mPaint);
    }

    // when ACTION_DOWN start touch according to the x,y values
    private void startTouch(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    // when ACTION_MOVE move touch according to the x,y values
    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }

    }

    public void resetTool() {
        mPath.reset();
    }

    private void upTouch() {
        mPath.lineTo(mX, mY);
    }

    public void updateTool(Canvas c, int x, int y) {
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
    public void setColor(int a, int r, int g, int b) {
    }

    @Override
    public void setWidth(float width) {
        mPaint.setStrokeWidth(width);
    }

    @Override
    public void setString(String text) {
        //nothing
    }
}