package com.example.codyclawson.paintapp;

import android.graphics.Canvas;

import java.util.ArrayList;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;


/**
 * Created by Kyle on 2/14/2017.
 */

public class ToolManager {
    private Canvas refCanvas;
    private CanvasTool mActiveTool;
    private ArrayList<CanvasTool> mTools;
    LinearLayout mToolLayout;
    SeekBar a;
    SeekBar r;
    SeekBar g;
    SeekBar b;
    SeekBar width;

    EditText text;

    AppCompatActivity activity;


    public ToolManager(AppCompatActivity main, LinearLayout optionLayout) {
        activity = main;
        mTools = new ArrayList<>();
        mToolLayout = optionLayout;
        a = (SeekBar) main.findViewById(R.id.alphaSeek);
        a.setProgress(255);
        r = (SeekBar) main.findViewById(R.id.redSeek);
        g = (SeekBar) main.findViewById(R.id.greenSeek);
        b = (SeekBar) main.findViewById(R.id.blueSeek);
        width = (SeekBar) main.findViewById(R.id.widthSeek);
        text = (EditText) main.findViewById(R.id.textOption);


    }

    public void setActiveTool(CanvasTool tool) {
        mActiveTool = tool;
    }

    //Sends motion event to active tool
    public boolean sendMotionEvent(MotionEvent event) {
        if (mActiveTool == null) return false;
        return mActiveTool.sendMotionEvent(event);
    }

    void updateTool(Canvas mCanvas, int w, int h) {
        mActiveTool.updateTool(mCanvas, w, h);
    }

    void resetTool() {
        for (CanvasTool t : mTools) {
            t.resetTool();
        }
    }

    public void draw(Canvas canvas) {
//        mActiveTool.draw(canvas);
        for (CanvasTool t : mTools) {
            t.draw(canvas);
        }
    }

    public void registerTool(CanvasTool tool, String name) {
        mTools.add(tool);

        addSelectionOption(tool, name);

    }


    private void addSelectionOption(final CanvasTool tool, String name) {
        Button b = new Button(activity);
        b.setText(name);

        final ToolManager manager = this;


        b.setOnClickListener(new View.OnClickListener() {
            ToolManager innerManager = manager;
            CanvasTool innerTool = tool;

            @Override
            public void onClick(View v) {
                innerManager.setActiveTool(tool);
            }
        });

        mToolLayout.addView(b);


    }


    public void onOptionButtonClicked() {

        mActiveTool.setColor(a.getProgress(), r.getProgress(), g.getProgress(), b.getProgress());
        mActiveTool.setString(text.getText().toString());
        mActiveTool.setWidth((((float) (width.getProgress() + 1)) / 255.0f) * 10);


    }


}
