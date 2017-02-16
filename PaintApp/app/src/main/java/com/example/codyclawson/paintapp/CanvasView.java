package com.example.codyclawson.paintapp;

/**
 * Created by CodyClawson on 2/15/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class CanvasView extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    Context context;
    SymmetryTool tool;
    CircleDrawTool tool2;
    TextTool tool3;
    EraserTool tool4;

    Paint paint2 = new Paint();


    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
        tool = new SymmetryTool();
        tool2 = new CircleDrawTool();
        tool3 = new TextTool();
        tool4 = new EraserTool();
        paint2.setColor(Color.BLACK);
        paint2.setStyle(Paint.Style.FILL);
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        tool4.draw(canvas);
        tool.draw(canvas);
//        tool2.draw(canvas);
        tool3.draw(canvas);
    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        tool.updateTool(mCanvas, w, h);
        tool2.updateTool(mCanvas, w, h);
        tool3.updateTool(mCanvas, w, h);
        tool4.updateTool(mCanvas, w, h);

    }

    public void clearCanvas() {
        tool.resetTool();
        tool2.resetTool();
        tool3.resetTool();
        tool4.resetTool();
        invalidate();
    }

    //override the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tool.handleEvent(event);
        tool2.handleEvent(event);
        tool3.handleEvent(event);
        tool4.handleEvent(event);
        invalidate();
        return true;
    }
}