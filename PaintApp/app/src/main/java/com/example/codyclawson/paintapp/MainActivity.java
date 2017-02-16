package com.example.codyclawson.paintapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.MediaStore;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private ToolManager mManager;
    private CanvasView customCanvas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
        LinearLayout toolLayout = (LinearLayout)findViewById(R.id.toolSelectArea);


        mManager = new ToolManager(this, toolLayout);
        customCanvas.Init(mManager);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        BrushTool b = new BrushTool();
        SymmetryTool s = new SymmetryTool();
        TextTool tTool = new TextTool();
        CircleDrawTool c = new CircleDrawTool();
        EraserTool e = new EraserTool();
        mManager.registerTool(s, "Symmetry Tool");
        mManager.registerTool(b, "Brush Tool");
        mManager.registerTool(tTool, "Text Tool");
        mManager.registerTool(c, "Circle Tool");
        mManager.registerTool(e, "Cool Tool");

        mManager.setActiveTool(s);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return mManager.sendMotionEvent(event);
    }

    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }
    public void onOptionButtonClicked(View view) {
        mManager.onOptionButtonClicked();
    }


    public void saveCanvas(View view){
        AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
        saveDialog.setTitle("Save drawing");
        saveDialog.setMessage("Save drawing to device Gallery?");
        saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                customCanvas.setDrawingCacheEnabled(true);
                String imgSaved = MediaStore.Images.Media.insertImage(
                        getContentResolver(), customCanvas.getDrawingCache(),
                        UUID.randomUUID().toString()+".png", "drawing");
            }
        });
        saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        saveDialog.show();
    }
}
