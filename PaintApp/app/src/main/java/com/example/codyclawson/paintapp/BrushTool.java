package com.example.codyclawson.paintapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CodyClawson on 2/15/2017.
 */

public class BrushTool implements CanvasTool{
    public int height;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;

    private HashMap<Path, Paint> paths;


    private static ArrayList<View> views;


    public  ArrayList<View> getViews()
    {
        return views;
    }

    public void setViews(ArrayList<View> views){
        this.views =  views;
    }


    public BrushTool(){
        // we set a new Path
        mPath = new Path();

        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);

        paths = new HashMap<Path, Paint>();

    }


    @Override
    public void setColor(int a, int r, int g, int b) {
        mPaint.setARGB(a,r,g,b);
    }

    @Override
    public void setWidth(float width) {
        mPaint.setStrokeWidth(width);
    }

    @Override
    public void setString(String text) {
        //nothing
    }

    public void draw(Canvas canvas) {
        // draw the mPath with the mPaint on the canvas when onDraw
        canvas.drawPath(mPath, mPaint);
        for (HashMap.Entry<Path, Paint> entry : paths.entrySet()) {
            Path key = entry.getKey();
            Paint value = entry.getValue();
            canvas.drawPath(key, value);
        }

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
        paths = new HashMap<Path, Paint>();
    }

    public void resetPaths(){
        mPath.reset();
    }

    // when ACTION_UP stop touch
    private void upTouch() {
        mPath.lineTo(mX, mY);

        Path temp = new Path();
        temp.set(mPath);
        Paint tempPaint = new Paint();
        tempPaint.set(mPaint);

        Path temp2 = new Path();
        Paint tempPaint2 = new Paint();
        tempPaint2.set(mPaint);

        paths.put(temp, tempPaint);
        paths.put(temp2, tempPaint2);

        resetPaths();

    }

    public void updateTool(Canvas c, int w, int h){
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
}
