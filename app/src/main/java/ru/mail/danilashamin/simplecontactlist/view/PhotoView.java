package ru.mail.danilashamin.simplecontactlist.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;

import ru.mail.danilashamin.simplecontactlist.R;

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

    public void setPhoto(Bitmap bitmap, PhotoShapeForm shapeForm) {
        Bitmap mask = BitmapFactory.decodeResource(getContext().getResources(), shapeForm == PhotoShapeForm.HEART ? R.drawable.heart_mask : R.drawable.star_mask);

        Bitmap result = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas tempCanvas = new Canvas(result);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        tempCanvas.drawBitmap(bitmap, 0, 0, null);
        tempCanvas.drawBitmap(mask, 0, 0, paint);
        paint.setXfermode(null);

        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (shapeForm != null) {
            switch (shapeForm) {
                case STAR:
                    float mid = canvas.getWidth() / 2;
                    float min = Math.min(canvas.getWidth(), canvas.getHeight());
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

                    int top = 0;
                    int left = 0;

                    int WIDTH = 200;
                    int HEIGHT = 200;
                    path.moveTo(left + WIDTH / 2, top + HEIGHT / 2); // Starting point
                    // Create a cubic Bezier cubic left path
                    path.cubicTo(left + WIDTH / 5, top,
                            left + WIDTH / 4, top + 4 * HEIGHT / 5,
                            left + WIDTH / 2, top + HEIGHT);
                    // This is right Bezier cubic path
                    path.cubicTo(left + 3 * WIDTH / 4, top + 4 * HEIGHT / 5,
                            left + 4 * WIDTH / 5, top,
                            left + WIDTH / 2, top + HEIGHT / 4);

                    path.close();
                    canvas.drawPath(path, paint);
                    break;

            }
        }


        super.onDraw(canvas);
    }
}
