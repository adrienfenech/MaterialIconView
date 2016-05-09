package com.adrienfenech.materialiconview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
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
        BitmapDrawable bitmapDrawable = ((BitmapDrawable)getDrawable());
        if (bitmapDrawable != null)
            setMaterialImageBitmap(bitmapDrawable.getBitmap());
    }


    /**
     * This method returns a MaterialPropertyAnimator object, which can be used to animate
     * specific properties on this View. Instead of {animate()}, you can call this method several
     * times in order to compute different animation.
     *
     * @return MaterialPropertyAnimator A new MaterialPropertyAnimator associated with this View.
     */
    public MaterialPropertyAnimator animateMaterial() {
        if (bitmap == null)
            throw new MaterialIconViewError("Bitmap null. Did you miss a call to setMaterialImageBitmap() ?");
        return new MaterialPropertyAnimator(this, false);
    }

    /**
     * Set a Bitmap Image to this view.
     * @param bitmap Image to set
     */
    public void setMaterialImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.bitmap = bitmap;
        width = bitmap.getWidth();
        height = bitmap.getHeight();

        resetMaterialImageBitmap();
    }

    /**
     * Reset the canvas (and the bitmap) as it was when {setMaterialImageBitmap} was called.
     */
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

    class MaterialIconViewError extends Error {
        public MaterialIconViewError(String message) {
            super(message);
        }
    }
}

