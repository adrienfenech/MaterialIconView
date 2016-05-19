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
import android.util.Pair;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Adrien Fenech on 28/04/16.
 */
public class MaterialPropertyAnimator {
    /** Default animation's duration **/
    public static final long DEFAULT_ANIMATION_DURATION = 500; // 500ms

    private static final String TAG = "MaterialPtyAnimator";

    /** MaterialIconView used **/
    private final MaterialIconView materialIconView;

    /** ValueAnimator used to perform the animation **/
    final ValueAnimator anim;

    /** Used if a basic animation is added to this one **/
    ViewPropertyAnimator viewPropertyAnimator = null;

    /** Specify if animation has to be launch with {@link #launchAnimation()} **/
    private final boolean requestAnimationLaunching;

    private final MaterialPropertyAnimator that = this;

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
    static final int CONCURRENT_ANIMATION =             0x0052;
    static final int PREMEDITATE_ACTION =               0x0053;
    static final int AREA_LISTENER =                    0x0054;
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
    private boolean concurrentAnimationHasBeenSet =     false;
    private boolean premeditateActionHasBeenSet =       false;
    private boolean animationHasBeenCancelled =         false;

    final Map<Integer, Object> animationValues;

    private Runnable animationRunnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    private Runnable animationStarter = new Runnable() {
        @Override
        public void run() {
            generateAnimation();
            if (startingDelayHasBeenSet) {
                new Handler().postDelayed(animationRunnable, (Long) animationValues.get(STARTING_DELAY));
            } else {
                new Handler().post(animationRunnable);
            }
        }
    };

    /**
     * Animation Constructor. The constructor is in package visibility in order to be created
     * with {@link MaterialIconView#animateMaterial()}.
     * @param materialIconView The {@link MaterialIconView} associated
     * @param requestFire Boolean use with {#newPostAnimation()} method.
     * When @param requestAnimationLaunching is set to true, the animation need a call to {#launchAnimation()} to be executed
     */
    MaterialPropertyAnimator(MaterialIconView materialIconView, boolean requestFire) {
        this.materialIconView = materialIconView;
        this.anim = ValueAnimator.ofFloat(0, 1); // animate from 0 to 1
        this.requestAnimationLaunching = requestFire;

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
        concurrentAnimationHasBeenSet = false;
        premeditateActionHasBeenSet = false;
        animationHasBeenCancelled = false;

        animationValues.put(DURATION, DEFAULT_ANIMATION_DURATION);
        animationValues.put(TYPE_OF_TRANSITION, TypeOfTransition.Circle);
        animationValues.put(DIRECTION_OF_TRANSITION, DirectionOfTransition.DownToUp);
        animationValues.put(INTERPOLATOR, new LinearInterpolator());
        animationValues.put(PREMEDITATE_ACTION, new ArrayList<PremeditateAction>());

        materialIconView.animators.add(this);
    }

    /**
     * This method will cause the canvas to be paint with a specific color when the
     * animation is started.
     * @param color Color to use
     * @return This object, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator fromColor(final int color) {
        animationValues.put(FROM_COLOR, color);
        fromColorHasBeenSet = true;
        enqueueAnimation();

        return this;
    }

    /**
     * This method will cause the canvas to be paint with a specific color according to animation.
     * @param color Color to use
     * @return This object, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator toColor(final int color) {
        animationValues.put(TO_COLOR, color);
        toColorHasBeenSet = true;
        enqueueAnimation();

        return this;
    }

    /**
     * This method lets you add listener to the animation to be notified when
     * the animation is starting, updating and ending.
     * @param materialAnimatorListenerAdapter The listener
     * @return This object, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator setListener(MaterialAnimatorListenerAdapter materialAnimatorListenerAdapter) {
        animationValues.put(ANIMATOR_LISTENER, materialAnimatorListenerAdapter);
        animatorListenerHasBeenSet = true;
        enqueueAnimation();

        return this;
    }

    /**
     * This method is a syntactic sugar to add an animation when this one will finish.
     * It'is equivalent to call a new {@link MaterialIconView#animateMaterial()} in {@link MaterialAnimatorListenerAdapter#onAnimationEnd(ValueAnimator)}.
     * This method will return a new MaterialPropertyAnimator which will be called at the end of this one (One frame later)
     * @return A new MaterialPropertyAnimator, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator withPostAnimation() {
        MaterialPropertyAnimator postAnimation = new MaterialPropertyAnimator(materialIconView, true);
        animationValues.put(POST_ANIMATION, postAnimation);
        postAnimationHasBeenSet = true;
        enqueueAnimation();

        return postAnimation;
    }

    /**
     * This method is a syntactic sugar to add an animation when this one will start.
     * It'is equivalent to call a new {@link MaterialIconView#animateMaterial()} in {@link MaterialAnimatorListenerAdapter#onAnimationStart(ValueAnimator)}.
     * This method will return a new MaterialPropertyAnimator which will be called at the beginning of this one (One frame later)
     * @return A new MaterialPropertyAnimator, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator withConcurrentAnimation() {
        MaterialPropertyAnimator concurrentAnimation = new MaterialPropertyAnimator(materialIconView, true);
        animationValues.put(CONCURRENT_ANIMATION, concurrentAnimation);
        concurrentAnimationHasBeenSet = true;
        enqueueAnimation();

        return concurrentAnimation;
    }

    /**
     * Sets the interpolator for the underlying animator that animates the requested properties.
     * By default, the animator uses the {@link AccelerateDecelerateInterpolator}. Calling this method
     * will cause the declared object to be used instead.
     *
     * @param interpolator The TimeInterpolator to be used for ensuing property animations. A value
     * of <code>null</code> will result in accelerate decelerate interpolation.
     * @return This object, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator setInterpolator(TimeInterpolator interpolator) {
        if (interpolator == null)
            return this;

        animationValues.put(INTERPOLATOR, interpolator);
        interpolatorHasBeenSet = true;
        enqueueAnimation();

        return this;
    }

    /**
     * Let you specify the transition type of this animation. By default, Circle is used.
     * @param typeOfTransition Transition to use
     * @return This object, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator setTransition(TypeOfTransition typeOfTransition) {
        animationValues.put(TYPE_OF_TRANSITION, typeOfTransition);
        typeOfTransitionHasBeenSet = true;
        enqueueAnimation();

        return this;
    }

    /**
     * Let you specify the direction of this animation. By default, DownToUp is used.
     * @param directionOfTransition Direction to use
     * @return This object, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator setDirection(DirectionOfTransition directionOfTransition) {
        animationValues.put(DIRECTION_OF_TRANSITION, directionOfTransition);
        directionOfTransitionHasBeenSet = true;
        enqueueAnimation();

        return this;
    }

    /**
     * Let you specify from which point as to begin the animation. Used only with Circle transition.
     * @param point The origin of the transition
     * @return This object, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator fromPoint(Point point) {
        animationValues.put(FROM_POINT, point);
        fromPointHasBeenSet = true;
        enqueueAnimation();

        return this;
    }

    /**
     * TODO
     *
     * Has currently no effect.
     */
    public MaterialPropertyAnimator toPoint(Point point) {
        animationValues.put(TO_POINT, point);
        toPointHasBeenSet = true;
        enqueueAnimation();

        return this;
    }

    /**
     * Let you specify a starting area for your animation. The value has to be in range 0 - 1.
     * (Default value is 0, means 0% of the total canvas area)
     * @param area The value to be used
     * @return This object, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator startingArea(final float area) {
        animationValues.put(STARTING_AREA, area);
        startingAreaHasBeenSet = true;
        enqueueAnimation();

        return this;
    }

    /**
     * Let you specify an ending area for your animation. The value has to be in range 0 - 1.
     * (Default value is 1, means 100% of the total canvas area)
     * @param area The value to be used
     * @return This object, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator endingArea(final float area) {
        animationValues.put(ENDING_AREA, area);
        endingAreaHasBeenSet = true;
        enqueueAnimation();

        return this;
    }

    /**
     * Sets the duration for the underlying animator that animates the requested properties.
     * By default, the animator uses {@link #DEFAULT_ANIMATION_DURATION}. Calling this method
     * will cause the declared value to be used instead.
     * @param duration The length of ensuing property animations, in milliseconds. The value
     * cannot be negative.
     * @return This object, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator setDuration(final long duration) {
        animationValues.put(DURATION, duration);
        durationHasBeenSet = true;
        enqueueAnimation();

        return this;
    }

    /**
     * Sets the startDelay for the underlying animator that animates the requested properties.
     * By default, the animator uses no delay. Calling this method
     * will cause the declared value to be used instead.
     * @param startDelay The delay of ensuing property animations, in milliseconds. The value
     * cannot be negative.
     * @return This object, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator setStartDelay(final long startDelay) {
        animationValues.put(STARTING_DELAY, startDelay);
        startingDelayHasBeenSet = true;
        enqueueAnimation();

        return this;
    }

    /**
     * This method let you add a dependent animation to the view. When implementing
     * ViewAnimation, you will access a ViewPropertyAnimator object. You can use this object as
     * the one provided by {@link View#animate()} method in order to add original animation
     * such as Rotation, Scale, ... with this animation.
     *
     * This animation is DEPENDENT, which means this animation will start with the same delay,
     * and use the duration of currently material animation.
     *
     * @param viewAnimation The animation
     * @return This object, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator withDependentAnimationView(ViewAnimation viewAnimation) {
        final long startingDelay = startingDelayHasBeenSet ? (long) animationValues.get(STARTING_DELAY) : 0;

        if (durationHasBeenSet)
            viewPropertyAnimator = materialIconView.animate().setStartDelay(startingDelay).setDuration((Long) animationValues.get(DURATION));
        else
            viewPropertyAnimator = materialIconView.animate().setStartDelay(startingDelay);

        viewAnimation.animate(viewPropertyAnimator);
        enqueueAnimation();

        return this;
    }

    /**
     * This method let you add an independent animation to the view. When implementing
     * ViewAnimation, you will access a ViewPropertyAnimator object. You can use this object as
     * the one provided by {@link View#animate()} method in order to add original animation
     * such as Rotation, Scale, ... with this animation.
     *
     * This animation is INDEPENDENT, which means this animation will NOT start with the same delay,
     * and NOT use the duration of currently material animation.
     *
     * @param viewAnimation The animation
     * @return This object, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator withIndependentAnimationView(ViewAnimation viewAnimation) {
        viewPropertyAnimator = materialIconView.animate();
        viewAnimation.animate(viewPropertyAnimator);
        enqueueAnimation();

        return this;
    }

    /**
     * This method let you program a specific action which will be executed when a specific condition is succeed.
     * The @param premeditateAction object has two methods: {@link PremeditateAction#execute} & {@link PremeditateAction#when}.
     * The code within the {@link PremeditateAction#execute} method will be execute when the {@link PremeditateAction#when} method will return true.
     * This action will be executed only one time (when the {@link PremeditateAction#when} method will return true for the first time).
     * You can add several {@link PremeditateAction} object to the same animation.
     * @param premeditateAction The premeditate object
     * @return This object, allowing calls to methods in this class to be chained.
     */
    public MaterialPropertyAnimator addPremeditateAction(PremeditateAction premeditateAction) {
        List<PremeditateAction> premeditateActionList = (List<PremeditateAction>) animationValues.get(PREMEDITATE_ACTION);
        premeditateActionList.add(premeditateAction);
        premeditateActionHasBeenSet = true;
        enqueueAnimation();

        return this;
    }

    /**
     * Cancel the animation associated to this MaterialPropertyAnimator
     */
    public void cancel() {
        animationHasBeenCancelled = true;
        if (viewPropertyAnimator != null)
            viewPropertyAnimator.cancel();
        this.anim.cancel();
    }

    /**
     * Enqueue the animation only if it is not a concurrent or a post animation,
     * in order to generate and launch it next frame.
     */
    private void enqueueAnimation() {
        if (!requestAnimationLaunching) {
            launchAnimation();
        }
    }

    /**
     * Generate and launch the animation next frame.
     */
    private void launchAnimation() {
        materialIconView.removeCallbacks(animationStarter);
        materialIconView.postOnAnimation(animationStarter);
    }

    /**
     * Generate the animation
     */
    private void generateAnimation() {
        animationRunnable = new Runnable() {
            @Override
            public void run() {
                int toColor = materialIconView.lastColor;
                if (toColorHasBeenSet)
                    toColor = (int) animationValues.get(TO_COLOR);

                if (fromColorHasBeenSet) {
                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
                    paint.setColor((Integer) animationValues.get(FROM_COLOR));
                    materialIconView.canvas.drawRect(0, 0, materialIconView.originalBitmapWidth, materialIconView.originalBitmapHeight, paint);
                    materialIconView.setImageDrawable(new BitmapDrawable(materialIconView.context.getResources(), materialIconView.canvasBitmap));
                }

                final Paint paint = new Paint();
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
                paint.setAntiAlias(true);
                paint.setColor(toColor);

                anim.setDuration((Long) animationValues.get(DURATION)); // for 300 ms

                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        if (premeditateActionHasBeenSet)
                            managePremeditateAction(animation);
                        if (animatorListenerHasBeenSet) {
                            ((MaterialAnimatorListenerAdapter) animationValues.get(ANIMATOR_LISTENER)).onAnimationUpdate(animation);
                        }
                        Pair<Point, RectF> originAndArea = generateOriginAndRelativeAreaToCover(animation);
                        switch ((TypeOfTransition) (animationValues.get(TYPE_OF_TRANSITION))) {
                            case Circle:
                                animateCircle(originAndArea.first, originAndArea.second, paint);
                                break;
                            case Line:
                                animateLine(originAndArea.second, paint);
                                break;
                            case Rect:
                                animateRectangle(originAndArea.second, paint);
                                break;
                        }
                    }
                });

                anim.setInterpolator(new AccelerateDecelerateInterpolator());
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        if (animatorListenerHasBeenSet) {
                            ((MaterialAnimatorListenerAdapter) animationValues.get(ANIMATOR_LISTENER)).onAnimationStart(anim);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (animatorListenerHasBeenSet) {
                            ((MaterialAnimatorListenerAdapter) animationValues.get(ANIMATOR_LISTENER)).onAnimationEnd(anim);
                        }
                        materialIconView.removeMaterialAnimator(that);
                    }
                });

                if (concurrentAnimationHasBeenSet) {
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            if (animationHasBeenCancelled)
                                return;
                            ((MaterialPropertyAnimator) animationValues.get(CONCURRENT_ANIMATION)).launchAnimation();
                        }
                    });
                }

                if (postAnimationHasBeenSet) {
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            if (animationHasBeenCancelled)
                                return;
                            ((MaterialPropertyAnimator) animationValues.get(POST_ANIMATION)).launchAnimation();
                        }
                    });
                }

                if (interpolatorHasBeenSet)
                    anim.setInterpolator((TimeInterpolator) animationValues.get(INTERPOLATOR));
                anim.start();
            }
        };
    }

    /**
     * Manage all PremeditateAction objects
     * @param animator
     */
    private void managePremeditateAction(ValueAnimator animator) {
        List<PremeditateAction> allActions = (List<PremeditateAction>) animationValues.get(PREMEDITATE_ACTION);
        List<PremeditateAction> actionDone = new ArrayList<PremeditateAction>();

        for (PremeditateAction premeditateAction : allActions) {
            if (premeditateAction.when(materialIconView, animator)) {
                premeditateAction.execute(materialIconView, animator);
                actionDone.add(premeditateAction);
            }
        }

        allActions.removeAll(actionDone);
        if (allActions.size() == 0)
            premeditateActionHasBeenSet = false;
    }

    /**
     * Create the area of current animation which has to be paint.
     * @param animator The animation
     * @return A Pair which contains the origin point of the rectangle and the rectangle
     * representing the area covered.
     */
    private Pair<Point, RectF> generateOriginAndRelativeAreaToCover(ValueAnimator animator) {
        float rectX1 = 0;
        float rectY1 = 0;
        float rectX2 = materialIconView.originalBitmapWidth;
        float rectY2 = materialIconView.originalBitmapHeight;

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

        if (animatorListenerHasBeenSet) {
            ((MaterialAnimatorListenerAdapter) animationValues.get(ANIMATOR_LISTENER)).onAreaCoveredUpdate(animator, start + end);
        }

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

    /**
     * Use to draw the circle
     * @param origin The origin
     * @param areaToCover The area to cover
     * @param paint The paint object used
     */
    private void animateCircle(Point origin, RectF areaToCover, Paint paint) {
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
                if (((areaToCover.right - areaToCover.left) / materialIconView.getBitmapWidth())
                        + ((areaToCover.bottom - areaToCover.top) / materialIconView.getBitmapHeight())
                        > 1.925f) {
                    radiusToApply = (float) (Math.min(areaToCover.right - areaToCover.left, areaToCover.bottom - areaToCover.top) * Math.sqrt(2));
                } else
                    radiusToApply = Math.min(areaToCover.right - areaToCover.left, areaToCover.bottom - areaToCover.top);
                break;
            default:
                radiusToApply = (float) Math.sqrt(Math.pow(areaToCover.right - areaToCover.left, 2) + Math.pow(areaToCover.bottom - areaToCover.top, 2));
                break;
        }
        materialIconView.canvas.drawCircle(centerX, centerY, radiusToApply, paint);
        materialIconView.setImageDrawable(new BitmapDrawable(materialIconView.context.getResources(), materialIconView.canvasBitmap));
    }

    /**
     * Use to draw the rectangle
     * @param areaToCover The are to cover
     * @param paint The paint object used
     */
    private void animateRectangle(RectF areaToCover, Paint paint) {
        materialIconView.canvas.drawRect(areaToCover, paint);
        materialIconView.setImageDrawable(new BitmapDrawable(materialIconView.context.getResources(), materialIconView.canvasBitmap));
    }

    /**
     * Use to draw the line
     * @param areaToCover The are to cover
     * @param paint The paint object used
     */
    private void animateLine(RectF areaToCover, Paint paint) {
        float x1 = 0;
        float y1 = 0;
        float x2 = 0;
        float y2 = 0;

        boolean shouldBeARectangle = false;
        final float width = (float) Math.sqrt(Math.pow(areaToCover.right - areaToCover.left, 2) + Math.pow(areaToCover.bottom - areaToCover.top, 2));

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
        materialIconView.setImageDrawable(new BitmapDrawable(materialIconView.context.getResources(), materialIconView.canvasBitmap));
    }

    public interface ViewAnimation {
        /**
         * This method lets you get a {@link ViewPropertyAnimator} object as you can get
         * with {@link View#animate()} method. You can simply chain call
         * to methods as basic animation.
         * @param animator The {@link ViewPropertyAnimator} object
         */
        void animate(ViewPropertyAnimator animator);
    }

    public interface PremeditateAction {

        /**
         * The code within the execute method will be executed when the {@link #when} method
         * will return true for the first time.
         * @param view Current view which is animated
         * @param animator Animator object updated
         */
        void execute(MaterialIconView view, ValueAnimator animator);

        /**
         * Method to indicate when the execute method has to be executed.
         * @param view Current view which is animated
         * @param animator Animator object updated
         * @return Boolean indicates if the execute method has to be executed
         */
        boolean when(MaterialIconView view, ValueAnimator animator);
    }
}
