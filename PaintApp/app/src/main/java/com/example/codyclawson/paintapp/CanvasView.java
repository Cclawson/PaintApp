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

    Paint paint2 = new Paint();


    ToolManager mManager;

    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
    }

    public void Init(ToolManager manager)
    {
        mManager = manager;
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mManager.draw(canvas);
    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        mManager.updateTool(mCanvas, w, h);

    }

    public void clearCanvas() {
        mManager.resetTool();
        invalidate();
    }

    //override the onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = mManager.sendMotionEvent(event);
        invalidate();
        return result;
    }
}