package com.example.loustics.models;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.loustics.R;

// Vue qui permet de générer des vagues colorées
public class Wave extends View {

    private Path path;
    private Paint paint;
    private boolean gravity;
    private int color;
    private float waveCount;
    private int startGradientColor, endGradientColor;

    public Wave(Context context) {
        this(context, null, 0);
    }

    public Wave(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Wave(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        createPaint(context, attrs, defStyleAttr);
        createPath();
    }

    private void createPaint(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Wave, defStyleAttr, 0);

        color = attributes.getColor(R.styleable.Wave_waveColor, Color.parseColor("#FF956637"));
        gravity = attributes.getBoolean(R.styleable.Wave_waveGravity, true);
        waveCount = attributes.getFloat(R.styleable.Wave_numberOfWaves, 1);
        boolean setGradient = attributes.getBoolean(R.styleable.Wave_setGradient, false);
        startGradientColor = attributes.getColor(R.styleable.Wave_gradientStartColor, 0);
        endGradientColor = attributes.getColor(R.styleable.Wave_gradientEndColor, 0);

        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        if (setGradient && startGradientColor > 0 && endGradientColor > 0) {
            paint.setShader(new LinearGradient(0, 0, 0, getHeight(), startGradientColor, endGradientColor, Shader.TileMode.MIRROR));
        }
    }

    private void createPath() {
        path = new Path();

        float x1, y1, x2, y2, x3, y3, y1Origin;
        float waveWidth = getWidth() / waveCount;
        path.moveTo(0, (float) getHeight() / 2);
        int count = 0;

        x1 = (float) waveWidth / 3;
        y1Origin = y1 = (float) (getHeight() * Math.random());
        x2 = (float) (2 * waveWidth / 3);
        y2 = (float) (getHeight() * Math.random());
        x3 = waveWidth;
        y3 = (float) (getHeight() / 2);
        path.cubicTo(x1, y1, x2, y2, x3, y3);

        count++;

        while (count < waveCount) {
            // calcul de la pente de la fin de la courbe
            float m = (y3 - y2) / (x3 - x2);
            float newx1 = (float) (waveWidth / 3f);
            x1 += waveWidth;
            y1 = 2 * y3 - y2;
            y2 = (float) (getHeight() * Math.random());
            x2 += waveWidth;
            x3 += waveWidth;
            y3 = (float) (getHeight() / 2);
            path.cubicTo(x1, y1, x2, y2, x3, y3);
            count++;
        }

        // Referme la forme
        if (gravity) {  // bottom
            path.lineTo(x3, getHeight());
            path.lineTo(0, getHeight());
            path.lineTo(0, y1Origin);
        } else {    // top
            path.lineTo(x3, 0);
            path.lineTo(0, 0);
            path.lineTo(0, y1Origin);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas != null && getWidth() != 0 && getHeight() != 0) {
            if (path != null) {
                canvas.drawPath(path, paint);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        createPath();
    }
}
