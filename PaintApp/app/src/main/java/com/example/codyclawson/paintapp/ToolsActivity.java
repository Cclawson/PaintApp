package com.example.codyclawson.paintapp;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class ToolsActivity extends AppCompatActivity {

    private ToolManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        mManager = new ToolManager();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
      return mManager.sendMotionEvent(event);
    }




}
