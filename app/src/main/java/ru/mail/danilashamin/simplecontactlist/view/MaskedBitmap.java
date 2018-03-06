package ru.mail.danilashamin.simplecontactlist.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Danil on 07.03.2018 on 0:01.
 */

public class MaskedBitmap extends Drawable {

    private Bitmap sourceBitmap;
    private Bitmap maskBitmap;
    private final Paint shaderPaint = new Paint();
    private BitmapShader bitmapShader;

    public void setMaskBitmap(Bitmap maskBitmap) {
        this.maskBitmap = maskBitmap;
        updateScaleMatrix();
    }

    public void setPictureBitmap(Bitmap sourceBitmap) {
        this.sourceBitmap = sourceBitmap;
        bitmapShader = new BitmapShader(sourceBitmap,
                Shader.TileMode.REPEAT,
                Shader.TileMode.REPEAT);
        shaderPaint.setShader(bitmapShader);
        updateScaleMatrix();
    }


    @Override
    public void draw(@NonNull Canvas canvas) {
        if (sourceBitmap == null || maskBitmap == null) {
            return;
        }
        canvas.drawBitmap(maskBitmap, 0, 0, shaderPaint);
    }

    private void updateScaleMatrix() {
        if (sourceBitmap == null || maskBitmap == null) {
            return;
        }
        int maskW = maskBitmap.getWidth();
        int maskH = maskBitmap.getHeight();

        int sourceW = sourceBitmap.getWidth();
        int sourceH = sourceBitmap.getHeight();

        float wScale = maskW / (float) sourceW;
        float hScale = maskH / (float) sourceH;

        float scale = Math.max(wScale, hScale);

        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        matrix.postTranslate((maskW - sourceW * scale) / 2f, (maskH - sourceH * scale) / 2f);
        bitmapShader.setLocalMatrix(matrix);

    }

    @Override
    public void setAlpha(int alpha) {
        shaderPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        shaderPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    @Override
    public int getIntrinsicWidth() {
        return maskBitmap != null ? maskBitmap.getWidth() : super.getIntrinsicWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return maskBitmap != null ? maskBitmap.getHeight() : super.getIntrinsicHeight();
    }
}
