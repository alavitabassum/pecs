package com.example.alavi.pecs11;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.app.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class SendRequest extends AppCompatActivity {

    int windowwidth;
    int windowheight;

    float x_cord;
    float y_cord;


    float gapX, gapY;

    ImageView ball, teddy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_request);

        windowwidth = getWindowManager().getDefaultDisplay().getWidth();
        windowheight = getWindowManager().getDefaultDisplay().getHeight();
        ball = (ImageView) findViewById(R.id.ball);
        teddy = (ImageView) findViewById(R.id.teddy);

        ball.setZ(1);
        teddy.setZ(2);

        attachDragDropListener(ball);
        attachDragDropListener(teddy);


        /*
        ball=(ImageView)findViewById(R.id.ball);
        ball.setOnTouchListener(this);
        teddy=(ImageView)findViewById(R.id.teddy);
        teddy.setOnTouchListener(this);

        */

    }
/*
    float x,y =0.0f;
    boolean moving=false;
    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {
        switch(arg1.getAction()){
            case MotionEvent.ACTION_DOWN:
                moving=true;
                break;
            case MotionEvent.ACTION_MOVE:
                if(moving){
                    x=arg1.getRawX()-ball.getWidth()/2;
                    y=arg1.getRawY()-ball.getHeight()*3/2;
                    ball.setX(x);
                    ball.setY(y);
                    x=arg1.getRawX()-teddy.getWidth()/2;
                    y=arg1.getRawY()-teddy.getHeight()*3/2;
                    teddy.setX(x);
                    teddy.setY(y);
                }
                break;
            case MotionEvent.ACTION_UP:
                moving=false;
                break;
        }
        return true;
    }
    */

    private void attachDragDropListener(final ImageView img) {


        img.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {


                    case MotionEvent.ACTION_DOWN:
                        x_cord = event.getRawX();
                        y_cord = event.getRawY();
                        gapX = x_cord - img.getX();
                        gapY = y_cord - img.getY();


                        swapDepth(img);

                        break;


                    case MotionEvent.ACTION_MOVE:
                        x_cord = event.getRawX();
                        y_cord = event.getRawY();


                        if (x_cord > windowwidth - (img.getWidth() - gapX)) {
                            x_cord = windowwidth - (img.getWidth() - gapX);
                        }
                        if (y_cord > windowheight - (img.getHeight() - gapY)) {
                            y_cord = windowheight - (img.getHeight() - gapY);
                        }
                        if (x_cord < 0 + gapX) {
                            x_cord = 0 + gapX;
                        }
                        if (y_cord < 0 + gapY) {
                            y_cord = 0 + gapY;
                        }


                        img.setX(x_cord - gapX);
                        img.setY(y_cord - gapY);

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }


    private void swapDepth(ImageView sourceImage) {


        String[] imageIDS = {"ball", "teddy"};


        Integer[] targetImgId = new Integer[imageIDS.length];


        ImageView[] targetImages = new ImageView[imageIDS.length];


        for (Integer i = 0; i < imageIDS.length; i++) {


            targetImgId[i] = getResources().getIdentifier(imageIDS[i], "id", getPackageName());


            if (sourceImage.getId() != targetImgId[i]) {

                targetImages[i] = ((ImageView) findViewById(targetImgId[i]));
                if (sourceImage.getZ() < targetImages[i].getZ()) {

                    targetImages[i].setZ(targetImages[i].getZ() - 1);

                }
            }

        }

        sourceImage.setZ(imageIDS.length);


    }


}
