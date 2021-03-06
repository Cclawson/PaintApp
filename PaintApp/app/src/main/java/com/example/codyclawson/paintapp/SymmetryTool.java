package com.example.codyclawson.paintapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by CodyClawson on 2/15/2017.
 */

public class SymmetryTool implements CanvasTool {

    public int height;
    private Canvas mCanvas;
    private Path mPath;
    private Path mPath2;
    private Paint mPaint;
    private float mX, mY, mX2, mY2;
    private static final float TOLERANCE = 5;
    private float centerX;
    private float centerY;
    private float distfromcenterX = 0;
    private float distfromcenterY = 0;
    private boolean xSymmetry = true;
    private boolean ySymmetry = true;

    private HashMap<Path, Paint> paths;


    private static ArrayList<View> views;


    public  ArrayList<View> getViews()
    {
        return views;
    }

    public void setViews(ArrayList<View> views){
        this.views =  views;
    }


    public SymmetryTool(){
        // we set a new Path
        mPath = new Path();
        mPath2 = new Path();

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
        canvas.drawPath(mPath2, mPaint);
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
        mX2 = x;
        mY2 = y;
        if(xSymmetry) {
            distfromcenterX = centerX - x;
            mX2 = centerX + distfromcenterX;
        }
        if(ySymmetry) {
            distfromcenterY = centerY - y;
            mY2 = centerY  + distfromcenterY;
        }
        mPath2.moveTo(mX2, mY2 );
    }

    // when ACTION_MOVE move touch according to the x,y values
    private void moveTouch(float x, float y) {
        distfromcenterX = x;
        distfromcenterY = y;
        if(xSymmetry) {
            distfromcenterX = centerX - x;
        }
        if(ySymmetry) {
            distfromcenterY = centerY - y;
        }
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        float dx2 = Math.abs(x - mX2);
        float dy2 = Math.abs((y-mY2));
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }

        if (dx2 >= TOLERANCE || dy2 >= TOLERANCE) {
            mPath2.quadTo(mX2, mY2, (centerX + mX2 + distfromcenterX) / 2, (centerY + mY2 + distfromcenterY ) / 2);
            mX2 = centerX + distfromcenterX;
            mY2 = centerY + distfromcenterY ;
        }
    }

    public void resetTool() {
        mPath.reset();
        mPath2.reset();
        paths = new HashMap<Path, Paint>();
    }

    public void resetPaths(){
        mPath.reset();
        mPath2.reset();
    }

    // when ACTION_UP stop touch
    private void upTouch() {
        mPath.lineTo(mX, mY);
        mPath2.lineTo(mX2, mY2);

        Path temp = new Path();
        temp.set(mPath);
        Paint tempPaint = new Paint();
        tempPaint.set(mPaint);

        Path temp2 = new Path();
        temp2.set(mPath2);
        Paint tempPaint2 = new Paint();
        tempPaint2.set(mPaint);

        paths.put(temp, tempPaint);
        paths.put(temp2, tempPaint2);

        resetPaths();

    }

    public void updateTool(Canvas c, int w, int h){
        mCanvas = c;
        centerX = w / 2;
        centerY = h / 2;
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
