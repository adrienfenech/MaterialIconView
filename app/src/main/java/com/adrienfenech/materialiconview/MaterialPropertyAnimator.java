package com.adrienfenech.materialiconview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;

/**
 * Created by Adrien Fenech on 28/04/16.
 */
public class MaterialPropertyAnimator {
    static final long DURATION_VALUE = 500; // 500ms
    private static final String TAG = "MaterialPtyAnimator";
    private final MaterialIconView materialIconView;
    private final boolean requestFire;

    /**
     * Constants used to associate a property being requested and the mechanism used to set
     * the property (this class calls directly into View to set the properties in question).
     */
    static final int NONE =                             0x0000;
    static final int FROM_COLOR =                       0x0001;
    static final int TO_COLOR =                         0x0002;
    static final int STARTING_AREA =                    0x0010;
    static final int ENDING_AREA =                      0x0011;
    static final int DURATION =                         0x0020;
    static final int STARTING_DELAY =                   0x0021;
    static final int FROM_POINT =                       0x0030;
    static final int TO_POINT =                         0x0031;
    static final int TYPE_OF_TRANSITION =               0x0040;
    static final int DIRECTION_OF_TRANSITION =          0x0041;
    static final int ANIMATOR_LISTENER =                0x0050;
    static final int POST_ANIMATION =                   0x0051;
    static final int INTERPOLATOR =                     0x0060;

    private boolean fromColorHasBeenSet =               false;
    private boolean toColorHasBeenSet =                 false;
    private boolean typeOfTransitionHasBeenSet =        false;
    private boolean directionOfTransitionHasBeenSet =   false;
    private boolean startingAreaHasBeenSet =            false;
    private boolean endingAreaHasBeenSet =              false;
    private boolean durationHasBeenSet =                false;
    private boolean startingDelayHasBeenSet =           false;
    private boolean fromPointHasBeenSet =               false;
    private boolean toPointHasBeenSet =                 false;
    private boolean animatorListenerHasBeenSet =        false;
    private boolean interpolatorHasBeenSet =            false;
    private boolean postAnimationHasBeenSet =           false;

    final Map<Integer, Object> animationValues;

    FutureTask animationTaskFutur = null;

    private Runnable animationRunnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    private Runnable animationStarter = new Runnable() {
        @Override
        public void run() {
            //animationTaskFutur = new FutureTask(animationRunnable, null);
            if (startingDelayHasBeenSet) {
                new Handler().postDelayed(animationRunnable, (Long) animationValues.get(STARTING_DELAY));
            } else {
                new Handler().post(animationRunnable);
            }
        }
    };

    MaterialPropertyAnimator(MaterialIconView materialIconView, boolean requestFire) {
        this.materialIconView = materialIconView;
        this.requestFire = requestFire;

        animationValues = new HashMap<>();
        fromColorHasBeenSet = false;
        toColorHasBeenSet = false;
        typeOfTransitionHasBeenSet = true;
        directionOfTransitionHasBeenSet = true;
        startingAreaHasBeenSet = false;
        endingAreaHasBeenSet = false;
        durationHasBeenSet = true;
        startingDelayHasBeenSet = false;
        fromPointHasBeenSet = false;
        toPointHasBeenSet = false;
        animatorListenerHasBeenSet = false;
        interpolatorHasBeenSet = true;
        postAnimationHasBeenSet = false;

        animationValues.put(DURATION, DURATION_VALUE);
        animationValues.put(TYPE_OF_TRANSITION, TypeOfTransition.Circle);
        animationValues.put(DIRECTION_OF_TRANSITION, DirectionOfTransition.DownToUp);
        animationValues.put(INTERPOLATOR, new LinearInterpolator());
    }

    MaterialPropertyAnimator fromColor(final int color) {
        animationValues.put(FROM_COLOR, color);
        fromColorHasBeenSet = true;
        computeAnimationRunnable();

        return this;
    }

    MaterialPropertyAnimator toColor(final int color) {
        animationValues.put(TO_COLOR, color);
        toColorHasBeenSet = true;
        computeAnimationRunnable();

        return this;
    }

    MaterialPropertyAnimator setListener(MaterialAnimatorListenerAdapter materialAnimatorListenerAdapter) {
        animationValues.put(ANIMATOR_LISTENER, materialAnimatorListenerAdapter);
        animatorListenerHasBeenSet = true;
        computeAnimationRunnable();

        return this;
    }

    MaterialPropertyAnimator newPostAnimation() {
        MaterialPropertyAnimator postAnimation = new MaterialPropertyAnimator(materialIconView, true);
        animationValues.put(POST_ANIMATION, postAnimation);
        postAnimationHasBeenSet = true;
        computeAnimationRunnable();

        return postAnimation;
    }

    MaterialPropertyAnimator setInterpolator(TimeInterpolator interpolator) {
        animationValues.put(INTERPOLATOR, interpolator);
        interpolatorHasBeenSet = true;
        computeAnimationRunnable();

        return this;
    }

    MaterialPropertyAnimator typeOfTransition(TypeOfTransition typeOfTransition) {
        animationValues.put(TYPE_OF_TRANSITION, typeOfTransition);
        typeOfTransitionHasBeenSet = true;
        computeAnimationRunnable();

        return this;
    }

    MaterialPropertyAnimator directionOfTransition(DirectionOfTransition directionOfTransition) {
        animationValues.put(DIRECTION_OF_TRANSITION, directionOfTransition);
        directionOfTransitionHasBeenSet = true;
        computeAnimationRunnable();

        return this;
    }

    MaterialPropertyAnimator fromPoint(Point point) {
        animationValues.put(FROM_POINT, point);
        fromPointHasBeenSet = true;
        computeAnimationRunnable();

        return this;
    }

    MaterialPropertyAnimator toPoint(Point point) {
        animationValues.put(TO_POINT, point);
        toPointHasBeenSet = true;
        computeAnimationRunnable();

        return this;
    }

    MaterialPropertyAnimator startingArea(final float area) {
        animationValues.put(STARTING_AREA, area);
        startingAreaHasBeenSet = true;
        computeAnimationRunnable();

        return this;
    }

    MaterialPropertyAnimator endingArea(final float area) {
        animationValues.put(ENDING_AREA, area);
        endingAreaHasBeenSet = true;
        computeAnimationRunnable();

        return this;
    }

    MaterialPropertyAnimator duration(final long duration) {
        animationValues.put(DURATION, duration);
        durationHasBeenSet = true;
        computeAnimationRunnable();

        return this;
    }

    MaterialPropertyAnimator startingDelay(final long delay) {
        animationValues.put(STARTING_DELAY, delay);
        startingDelayHasBeenSet = true;
        computeAnimationRunnable();

        return this;
    }

    MaterialPropertyAnimator withDependantAnimationView(ViewAnimation viewAnimation) {
        final long startingDelay = startingDelayHasBeenSet ? (long) animationValues.get(STARTING_DELAY) : 0;

        if (durationHasBeenSet)
            viewAnimation.animate(materialIconView.animate().setStartDelay(startingDelay).setDuration((Long) animationValues.get(DURATION)));
        else
            viewAnimation.animate(materialIconView.animate().setStartDelay(startingDelay));
        computeAnimationRunnable();

        return this;
    }

    MaterialPropertyAnimator withIndependantAnimationView(ViewAnimation viewAnimation) {
        viewAnimation.animate(materialIconView.animate());
        computeAnimationRunnable();

        return this;
    }

    private void fire() {
        materialIconView.removeCallbacks(animationStarter);
        materialIconView.postOnAnimation(animationStarter);
    }

    private void computeAnimationRunnable() {
        animationRunnable = new Runnable() {
            @Override
            public void run() {
                int toColor = materialIconView.lastColor;
                if (toColorHasBeenSet)
                    toColor = (int) animationValues.get(TO_COLOR);

                //
                if (fromColorHasBeenSet) {
                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
                    paint.setColor((Integer) animationValues.get(FROM_COLOR));
                    materialIconView.canvas.drawRect(0, 0, materialIconView.width, materialIconView.height, paint);
                    materialIconView.setImageDrawable(new BitmapDrawable(materialIconView.context.getResources(), materialIconView.tempBitmap));
                }
                //
                final Paint paint = new Paint();
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
                paint.setAntiAlias(true);
                paint.setColor(toColor);

                final ValueAnimator anim = ValueAnimator.ofFloat(0, 1); // animate from 0 to 1
                anim.setDuration((Long) animationValues.get(DURATION)); // for 300 ms

                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override public void onAnimationUpdate(ValueAnimator animation) {
                        if (animatorListenerHasBeenSet)
                            ((MaterialAnimatorListenerAdapter)animationValues.get(ANIMATOR_LISTENER)).onAnimationUpdate(animation);
                        Pair<Point, RectF> originAndArea = computeRectAnimation(animation);
                        switch ((TypeOfTransition) (animationValues.get(TYPE_OF_TRANSITION))) {
                            case Circle:
                                drawCircle(originAndArea.first, originAndArea.second, paint);
                                break;
                            case Line:
                                drawLine(originAndArea.second, paint);
                                break;
                            case Rect:
                                drawRect(originAndArea.second, paint);
                                break;
                        }
                    }
                });

                anim.setInterpolator(new AccelerateDecelerateInterpolator());

                if (animatorListenerHasBeenSet) {
                    final MaterialAnimatorListenerAdapter materialAnimatorListenerAdapter = (MaterialAnimatorListenerAdapter) animationValues.get(ANIMATOR_LISTENER);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            materialAnimatorListenerAdapter.onAnimationStart(anim);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            materialAnimatorListenerAdapter.onAnimationEnd(anim);
                        }
                    });
                }

                if (postAnimationHasBeenSet) {
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            ((MaterialPropertyAnimator)animationValues.get(POST_ANIMATION)).fire();
                        }
                    });
                }

                if (interpolatorHasBeenSet)
                    anim.setInterpolator((TimeInterpolator) animationValues.get(INTERPOLATOR));
                anim.start();
            }
        };

        if (!requestFire) {
            materialIconView.removeCallbacks(animationStarter);
            materialIconView.postOnAnimation(animationStarter);
        }
    }

    private Pair<Point, RectF> computeRectAnimation(ValueAnimator animator) {
        float rectX1 = 0;
        float rectY1 = 0;
        float rectX2 = materialIconView.width;
        float rectY2 = materialIconView.height;

        float newRectX1;
        float newRectX2;
        float newRectY1;
        float newRectY2;

        float startingAreaFraction = 0;
        float endingAreaFraction = 1;
        if (startingAreaHasBeenSet)
            startingAreaFraction = (float) animationValues.get(STARTING_AREA);
        if (endingAreaHasBeenSet)
            endingAreaFraction = (float) animationValues.get(ENDING_AREA);

        float animatedFraction = animator.getAnimatedFraction();
        float start = startingAreaFraction - startingAreaFraction * animatedFraction;
        float end = animatedFraction * endingAreaFraction;

        switch ((DirectionOfTransition)(animationValues.get(DIRECTION_OF_TRANSITION))) {
            case UpToDown:
                newRectY2 = rectY2 * end + rectY2 * (start);
                return new Pair<>(new Point((int)rectX2 / 2, (int)rectY1), new RectF(rectX1, rectY1, rectX2, newRectY2));

            case UpRightToDownLeft:
                newRectX1 = rectX2 - (rectX2 * end + rectX2 * (start));
                newRectY2 = rectY2 * end + rectY2 * (start);
                return new Pair<>(new Point((int)rectX2, (int)rectY1), new RectF(newRectX1, rectY1, rectX2, newRectY2));

            case RightToLeft:
                newRectX1 = rectX2 - (rectX2 * end + rectX2 * (start));
                return new Pair<>(new Point((int)rectX2, (int)rectY2 / 2), new RectF(newRectX1, rectY1, rectX2, rectY2));

            case DownRightToUpLeft:
                newRectX1 = rectX2 - (rectX2 * end + rectX2 * (start));
                newRectY1 = rectY2 - (rectY2 * end + rectY2 * (start));
                return new Pair<>(new Point((int)rectX2, (int)rectY2), new RectF(newRectX1, newRectY1, rectX2, rectY2));

            case DownToUp:
                newRectY1 = rectY2 - (rectY2 * end + rectY2 * (start));
                return new Pair<>(new Point((int)rectX2 / 2, (int)rectY2), new RectF(rectX1, newRectY1, rectX2, rectY2));

            case DownLeftToUpRight:
                newRectY1 = rectY2 - (rectY2 * end + rectY2 * (start));
                newRectX2 = rectX2 * end + rectX2 * (start);
                return new Pair<>(new Point((int)rectX1, (int)rectY2), new RectF(rectX1, newRectY1, newRectX2, rectY2));

            case LeftToRight:
                newRectX2 = rectX2 * end + rectX2 * (start);
                return new Pair<>(new Point((int)rectX1, (int)rectY2 / 2), new RectF(rectX1, rectY1, newRectX2, rectY2));

            case UpLeftToDownRight:
                newRectX2 = rectX2 * end + rectX2 * (start);
                newRectY2 = rectY2 * end + rectY2 * (start);
                return new Pair<>(new Point((int)rectX1, (int)rectY1), new RectF(rectX1, rectY1, newRectX2, newRectY2));
        }
        return new Pair<>(new Point(), new RectF(0, 0, 0, 0));
    }

    private void drawCircle(Point origin, RectF areaToCover, Paint paint) {
        if (fromPointHasBeenSet)
            origin = (Point) animationValues.get(FROM_POINT);
        int centerX = origin.x;
        int centerY = origin.y;

        float radiusToApply;

        switch (((DirectionOfTransition)animationValues.get(DIRECTION_OF_TRANSITION))) {
            case UpToDown:
            case RightToLeft:
            case DownToUp:
            case LeftToRight:
                radiusToApply = (float) (Math.min(areaToCover.right - areaToCover.left, areaToCover.bottom - areaToCover.top) * Math.sqrt(2));
                break;
            default:
                radiusToApply = (float) Math.sqrt(Math.pow(areaToCover.right - areaToCover.left, 2) + Math.pow(areaToCover.bottom - areaToCover.top, 2));
                break;
        }
        materialIconView.canvas.drawCircle(centerX, centerY, radiusToApply, paint);
        materialIconView.setImageDrawable(new BitmapDrawable(materialIconView.context.getResources(), materialIconView.tempBitmap));
    }

    private void drawRect(RectF areaToCover, Paint paint) {
        materialIconView.canvas.drawRect(areaToCover, paint);
        materialIconView.setImageDrawable(new BitmapDrawable(materialIconView.context.getResources(), materialIconView.tempBitmap));
    }

    private void drawLine(RectF areaToCover, Paint paint) {
        float x1 = 0;
        float y1 = 0;
        float x2 = 0;
        float y2 = 0;

        boolean shouldBeARectangle = false;
        final float width = (float) Math.sqrt(Math.pow(areaToCover.right - areaToCover.left, 2) + Math.pow(areaToCover.bottom - areaToCover.top, 2));
        final float semiWidth = width / 2;

        switch (((DirectionOfTransition)animationValues.get(DIRECTION_OF_TRANSITION))) {
            case UpToDown:
            case RightToLeft:
            case DownToUp:
            case LeftToRight:
                shouldBeARectangle = true;
                break;
            case UpRightToDownLeft:
                x1 = areaToCover.left;
                y1 = areaToCover.top;
                x2 = areaToCover.right;
                y2 = areaToCover.bottom;
                break;
            case DownRightToUpLeft:
                x1 = areaToCover.left;
                y1 = areaToCover.bottom;
                x2 = areaToCover.right;
                y2 = areaToCover.top;
                break;
            case DownLeftToUpRight:
                x1 = areaToCover.left;
                y1 = areaToCover.bottom;
                x2 = areaToCover.right;
                y2 = areaToCover.top;
                break;
            case UpLeftToDownRight:
                x1 = areaToCover.left;
                y1 = areaToCover.top;
                x2 = areaToCover.right;
                y2 = areaToCover.bottom;
                break;
            default:
                x1 = areaToCover.right;
                y1 = areaToCover.top;
                x2 = areaToCover.left;
                y2 = areaToCover.bottom;
        }

        if (shouldBeARectangle)
            materialIconView.canvas.drawRect(areaToCover, paint);
        else {
            paint.setStrokeWidth(width);
            materialIconView.canvas.drawLine(x1, y1, x2, y2, paint);
        }
        materialIconView.setImageDrawable(new BitmapDrawable(materialIconView.context.getResources(), materialIconView.tempBitmap));
    }

    void cancel() {
        Log.d(TAG, "cancel: animation");
        if (animationTaskFutur == null)
            return;
        animationTaskFutur.cancel(true);
    }

    public interface ViewAnimation {
        void animate(ViewPropertyAnimator animator);
    }
}
