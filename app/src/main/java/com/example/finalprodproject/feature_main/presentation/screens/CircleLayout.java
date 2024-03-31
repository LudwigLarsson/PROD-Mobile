package com.example.finalprodproject.feature_main.presentation.screens;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.finalprodproject.R;

import java.util.ArrayList;
import java.util.List;

public class CircleLayout extends View {
    private Paint paint;
    private Paint linePaint;
    private final int radius = 100;
    private int count = 0;
    private double angleInRadians = Math.toRadians(60);
    private double angleInRadians2 = Math.toRadians(120);


    public CircleLayout(Context context) {
        super(context);
        init();
    }

    public CircleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.FILL);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.LTGRAY);
        linePaint.setStrokeWidth(5);

        DashPathEffect dashPathEffect = new DashPathEffect(new float[]{10, 10}, 0);
        linePaint.setPathEffect(dashPathEffect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int desiredHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightMeasureSpec + 100, getResources().getDisplayMetrics());
        int heightMeasureSpecCustom = MeasureSpec.makeMeasureSpec(desiredHeight, MeasureSpec.EXACTLY);
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpecCustom);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        float centerX = 220;
        float centerY = 150;

        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                float endX = centerX + (float) (3 * radius * Math.cos(angleInRadians));
                float endY = centerY + (float) (3 * radius * Math.sin(angleInRadians));

                if (i + 1 != count) canvas.drawLine(centerX, centerY, endX, endY, linePaint);

                canvas.drawCircle(centerX, centerY, radius, paint);

                centerX = endX;
                centerY = endY;
            } else {
                float endX2 = centerX + (float) (3 * radius * Math.cos(angleInRadians2));
                float endY2 = centerY + (float) (3 * radius * Math.sin(angleInRadians2));

                if (i + 1 != count) canvas.drawLine(centerX, centerY, endX2, endY2, linePaint);
                canvas.drawCircle(centerX, centerY, radius, paint);

                centerX = endX2;
                centerY = endY2;
            }
        }

    }

    public void addCircle() {
        count++;
        invalidate();
    }
}
