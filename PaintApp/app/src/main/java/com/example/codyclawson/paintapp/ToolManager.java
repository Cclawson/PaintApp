package com.example.codyclawson.paintapp;

import android.graphics.Canvas;

import java.util.ArrayList;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by Kyle on 2/14/2017.
 */

public class ToolManager{
    private Canvas refCanvas;
    private ArrayList<CanvasTool> mTools;

    private CanvasTool mActiveTool;

    public ToolManager()
    {
        mTools = new ArrayList<CanvasTool>();
    }

    public void registerTool(CanvasTool tool)
    {
        mTools.add(tool);
    }

    //Sends motion event to active tool
    public boolean sendMotionEvent(MotionEvent event)
    {
        if(mActiveTool == null) return false;
        return mActiveTool.sendMotionEvent(event);
    }


    public ArrayList<CanvasTool> getTools()
    {
        return mTools;
    }


    //public View buildToolOptions()
    //{

    //}



}
