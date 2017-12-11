package com.example.yana.alphabetter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yana on 12/9/2017.
 * The custom View object that allows the user to draw/trace letters in the target
 * language to practice writing them.
 *
 * Used http://valokafor.com/android-drawing-app-tutorial-part-1/ as reference
 */

public class LetterCanvas extends View {
    // the path being drawn
    private Path drawPath;

    // defines what to draw
    private Paint canvasPaint;

    // defines how to draw
    private Paint drawPaint;

    // paint color
    private int paintColor = 0xFFFF0000;

    // transfers
    public Canvas drawCanvas;

    //canvas bitmap
    private Bitmap canvasBitmap;

    //brush size
    private float brushSize;

    private void init(){
        brushSize = 15;
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(brushSize);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);

    }

    // constructor
    public LetterCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);

        //setup canvas
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0 , 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //create canvas with a particular size
        super.onSizeChanged(w, h, oldw, oldh);

        //create bitmap with a particular width and height
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        //apply bitmap to graphic to start drawing.
        drawCanvas = new Canvas(canvasBitmap);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        //respond to down, move and up events
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // extend path to touch
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                // extend path to touch
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(touchX, touchY);
                // display the path
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }

        //redraw
        invalidate();
        return true;
    }

    // erases entire drawing, returns true if successfully erased
    public boolean eraseAll() {
        if (drawCanvas != null) {
            drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            invalidate();
            return true;
        }
        return false;

    }


}
