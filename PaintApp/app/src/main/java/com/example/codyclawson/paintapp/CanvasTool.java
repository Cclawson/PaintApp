package com.example.codyclawson.paintapp;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Kyle on 2/14/2017.
 */

public interface CanvasTool {

    boolean sendMotionEvent(MotionEvent event);
    void draw(Canvas canvas);
    void updateTool(Canvas mCanvas, int w, int h);
    void resetTool();
    void setColor(int a, int r, int g, int b);
    void setWidth(float width);
    void setString(String text);


}
