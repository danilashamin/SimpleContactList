package ru.mail.danilashamin.simplecontactlist.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;

/**
 * Created by Danil on 06.03.2018 on 16:48.
 */

public class PhotoView extends android.support.v7.widget.AppCompatImageView {
    private Paint paint;
    private Path path;
    private PhotoShapeForm shapeForm;

    public PhotoView(Context context) {
        super(context);
        init();
    }

    public PhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PhotoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        path = new Path();
        paint = new Paint();
    }

    public void setPhoto(Bitmap bitmap) {
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        invalidate();
    }

    public void setShapeForm(PhotoShapeForm shapeForm) {
        this.shapeForm = shapeForm;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        if (shapeForm != null) {
            switch (shapeForm) {
                case STAR:
                    float mid = getWidth() / 2;
                    float min = Math.min(getWidth(), getHeight());
                    float half = min / 2;
                    mid = mid - half;

                    path.reset();

                    //draw star
                    path.moveTo(mid + half * 0.5f, half * 0.84f);
                    path.lineTo(mid + half * 1.5f, half * 0.84f);
                    path.lineTo(mid + half * 0.68f, half * 1.45f);
                    path.lineTo(mid + half * 1.0f, half * 0.5f);
                    path.lineTo(mid + half * 1.32f, half * 1.45f);
                    path.lineTo(mid + half * 0.5f, half * 0.84f);

                    path.close();
                    canvas.drawPath(path, paint);
                    break;
                case HEART:
                    path.reset();
                    float length = 100;
                    float x = canvas.getWidth() / 2;
                    float y = canvas.getHeight();

                    canvas.rotate(45, x, y);

                    path.moveTo(x, y);
                    path.lineTo(x - length, y);
                    path.arcTo(new RectF(x - length - (length / 2), y - length, x - (length / 2), y), 90, 180);
                    path.arcTo(new RectF(x - length, y - length - (length / 2), x, y - (length / 2)), 180, 180);
                    path.lineTo(x, y);
                    path.close();
                    canvas.drawPath(path, paint);
                    break;
            }
        }


        super.onDraw(canvas);
    }

}
