package com.example.uas_02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView mImgView;
    Bitmap mBitmap;
    Canvas mCanvas;
    private int mColorBackground;
    Paint mCirclePaint = new Paint();
    Paint mHeadPaint = new Paint();
    ObjectAnimator AnimasiFlip;
    ObjectAnimator AnimasiFadeIn;
    ObjectAnimator AnimasiFadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImgView = findViewById(R.id.my_img_view);

        mCirclePaint.setColor(getResources().getColor(R.color.black));
        mHeadPaint.setColor(getResources().getColor(R.color.white));

        // Flip
        AnimasiFlip = ObjectAnimator.ofFloat(mImgView,"rotationY", 180);
        AnimasiFadeIn = ObjectAnimator.ofFloat(mImgView, "alpha", 0, 1f);
        AnimasiFadeOut = ObjectAnimator.ofFloat(mImgView, "alpha", 1f, 0);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int vWidth = mImgView.getWidth();
        int vHeight = mImgView.getHeight();
        float centerX = vWidth / 2f;
        float centerY = vHeight / 2f;
        float radiusX = vWidth / 3f;
        float radiusY = vHeight / 4f;

        mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
        mImgView.setImageBitmap(mBitmap);
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(mColorBackground);

        drawHead(centerX, centerY, radiusX, radiusY);
        drawRightEye(vWidth, vHeight);
        drawLeftEye(vWidth, vHeight);
        drawEyeConnector(vWidth, vHeight);

        AnimasiFadeIn.setDuration(1000);
        AnimasiFadeIn.start();

        AnimasiFlip.setDuration(3000);
        AnimasiFlip.setStartDelay(1000);
        AnimasiFlip.getStartDelay();
        AnimasiFlip.start();

        AnimasiFadeOut.setStartDelay(4000);
        AnimasiFadeOut.getStartDelay();
        AnimasiFadeOut.setDuration(1000);
        AnimasiFadeOut.start();
    }

    public void drawHead(float centerX, float centerY, float radiusX, float radiusY) {
        mCanvas.drawOval(centerX - radiusY, centerY - radiusX, centerX + radiusY,
                centerY + radiusX, mHeadPaint);
    }

    public void drawRightEye (int mWidth, int mHeight) {
        mCanvas.drawCircle(mWidth / 2 - 200,mHeight / 2, 80, mCirclePaint);
    }

    public void drawLeftEye (int mWidth, int mHeight) {
        mCanvas.drawCircle(mWidth / 2 + 200,mHeight / 2, 80, mCirclePaint);
    }

    public void drawEyeConnector (int mWidth, int mHeight) {
        mCanvas.drawRect(mWidth / 2 - 175, mHeight / 2 + 20,
                mWidth / 2 + 175, mHeight / 2 -20, mCirclePaint);
    }


}