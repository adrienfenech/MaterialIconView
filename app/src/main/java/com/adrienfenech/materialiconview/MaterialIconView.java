package com.adrienfenech.materialiconview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Adrien Fenech on 27/04/16.
 */
public class MaterialIconView extends ImageView {
    Context context;
    Bitmap bitmap = null;
    Bitmap tempBitmap;
    Canvas canvas;
    int lastColor;
    int width = 0;
    int height = 0;

    public MaterialIconView(Context context) {
        super(context);
        init(context);
    }

    public MaterialIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MaterialIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MaterialIconView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
    }

    public MaterialPropertyAnimator animateMaterial() {
        if (bitmap == null)
            throw new MaterialIconViewError("Bitmap null. Did you miss a call to setMaterialImageBitmap() ?");
        return new MaterialPropertyAnimator(this, false);
    }

    public void setMaterialImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.bitmap = bitmap;
        width = bitmap.getWidth();
        height = bitmap.getHeight();

        resetMaterialImageBitmap();
    }

    public void resetMaterialImageBitmap() {
        tempBitmap = Bitmap.createBitmap(width, height, bitmap.getConfig());
        tempBitmap.eraseColor(Color.parseColor("#00FFFFFF"));
        canvas = new Canvas(tempBitmap);
        clearColorFilter();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawBitmap(bitmap, null, new Rect(0, 0, width, height), paint);
    }

    public int getBitmapWidth() {
        return width;
    }

    public int getBitmapHeight() {
        return height;
    }

    public void cancelMaterialAnimation(MaterialPropertyAnimator animator) {
        animator.cancel();
    }

    class MaterialIconViewError extends Error {
        public MaterialIconViewError(String message) {
            super(message);
        }
    }
}

