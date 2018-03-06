package ru.mail.danilashamin.simplecontactlist.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;

import ru.mail.danilashamin.simplecontactlist.R;

/**
 * Created by Danil on 06.03.2018 on 16:48.
 */

public class PhotoView extends android.support.v7.widget.AppCompatImageView {
    private Paint paint;
    private Bitmap photo;

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
        paint = new Paint();
    }

    public void setPhoto(Bitmap bitmap, PhotoShapeForm shapeForm) {
        Bitmap mask = BitmapFactory.decodeResource(getContext().getResources(), shapeForm == PhotoShapeForm.HEART ? R.drawable.heart_mask : R.drawable.star_mask);

        photo = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas tempCanvas = new Canvas(photo);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        tempCanvas.drawBitmap(bitmap, 0, 0, null);
        tempCanvas.drawBitmap(mask, 0, 0, paint);
        paint.setXfermode(null);

        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (photo != null) {
            canvas.drawBitmap(photo, 0, 0, paint);
        }
        super.onDraw(canvas);
    }
}
