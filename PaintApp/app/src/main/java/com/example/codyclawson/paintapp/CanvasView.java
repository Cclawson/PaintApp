package com.example.codyclawson.paintapp;

/**
 * Created by CodyClawson on 2/15/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class CanvasView extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    Context context;
    SymmetryTool tool;


    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
        tool = new SymmetryTool();
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        tool.draw(canvas);
    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        tool.updateTool(mCanvas, w, h);
    }

    public void clearCanvas() {
        tool.resetTool();
        invalidate();
    }

    //override the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tool.handleEvent(event);
        invalidate();
        return true;
    }
}