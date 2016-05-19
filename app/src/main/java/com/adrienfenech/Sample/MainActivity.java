package com.adrienfenech.Sample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.adrienfenech.materialiconview.DirectionOfTransition;
import com.adrienfenech.materialiconview.MaterialAnimatorListenerAdapter;
import com.adrienfenech.materialiconview.MaterialColor;
import com.adrienfenech.materialiconview.MaterialIconView;
import com.adrienfenech.materialiconview.MaterialPropertyAnimator;
import com.adrienfenech.materialiconview.TypeOfTransition;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by octo on 19/05/16.
 */
public class MainActivity extends AppCompatActivity {
    Random rnd;
    MaterialIconView mainIcon, progressHorizontal, progressVertical, circle, rectangle, line, android;
    boolean shouldStop = false;
    boolean isInTransition = false;
    int menuColor = MaterialColor.BlueGrey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        rnd = new Random();

        mainIcon = (MaterialIconView) findViewById(R.id.mainIcon);

        progressHorizontal = (MaterialIconView) findViewById(R.id.progressHorizontal);
        progressVertical = (MaterialIconView) findViewById(R.id.progressVertical);
        circle = (MaterialIconView) findViewById(R.id.circle);
        rectangle = (MaterialIconView) findViewById(R.id.rectangle);
        line = (MaterialIconView) findViewById(R.id.line);
        android = (MaterialIconView) findViewById(R.id.android);

        mainIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldStop || isInTransition)
                    return;
                shouldStop = true;
                isInTransition = true;
                cancelAllAnimations();
                hideItemsButNotThisView(mainIcon);
                closeMenu(0);
            }
        });

        progressHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldStop || isInTransition)
                    return;
                shouldStop = true;
                isInTransition = true;
                cancelAllAnimations();
                hideItemsButNotThisView(progressHorizontal);
                closeMenu(1);
            }
        });

        progressVertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldStop || isInTransition)
                    return;
                shouldStop = true;
                isInTransition = true;
                cancelAllAnimations();
                hideItemsButNotThisView(progressVertical);
                closeMenu(2);
            }
        });

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldStop || isInTransition)
                    return;
                shouldStop = true;
                isInTransition = true;
                cancelAllAnimations();
                hideItemsButNotThisView(circle);
                closeMenu(3);
            }
        });

        rectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldStop || isInTransition)
                    return;
                shouldStop = true;
                isInTransition = true;
                cancelAllAnimations();
                hideItemsButNotThisView(rectangle);
                closeMenu(4);
            }
        });

        line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldStop || isInTransition)
                    return;
                shouldStop = true;
                isInTransition = true;
                cancelAllAnimations();
                hideItemsButNotThisView(line);
                closeMenu(5);
            }
        });

        android.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldStop || isInTransition)
                    return;
                shouldStop = true;
                isInTransition = true;
                cancelAllAnimations();
                hideItemsButNotThisView(android);
                closeMenu(6);
            }
        });

        resetMainIcon();

        Bitmap iconHorizontal = Bitmap.createBitmap(dpToPx(75), dpToPx(20), Bitmap.Config.ARGB_8888);
        Canvas iconHorizontalCanvas = new Canvas(iconHorizontal);
        iconHorizontalCanvas.drawColor(Color.WHITE);
        progressHorizontal.setMaterialImageBitmap(iconHorizontal);

        Bitmap iconVertical = Bitmap.createBitmap(dpToPx(20), dpToPx(75), Bitmap.Config.ARGB_8888);
        Canvas iconVerticalCanvas = new Canvas(iconVertical);
        iconVerticalCanvas.drawColor(Color.WHITE);
        progressVertical.setMaterialImageBitmap(iconVertical);
    }

    private void resetMainIcon() {
        Bitmap bitmap = Bitmap.createBitmap(dpToPx(100), dpToPx(100), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        mainIcon.setMaterialImageBitmap(bitmap);
    }

    private void cancelAllAnimations() {
        mainIcon.cancelAllMaterialAnimation();
        progressHorizontal.cancelAllMaterialAnimation();
        progressVertical.cancelAllMaterialAnimation();
        circle.cancelAllMaterialAnimation();
        rectangle.cancelAllMaterialAnimation();
        line.cancelAllMaterialAnimation();
        android.cancelAllMaterialAnimation();
    }

    private void launchMenuAnimation() {
        isInTransition = false;
        mainIcon.animateMaterial()
                .setDuration(2000)
                .setStartDelay(1000)
                .setInterpolator(new DecelerateInterpolator())
                .setTransition(TypeOfTransition.Line)
                .toColor(menuColor)
                .withIndependentAnimationView(new MaterialPropertyAnimator.ViewAnimation() {
                    @Override
                    public void animate(ViewPropertyAnimator animator) {
                        animator
                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                .setDuration(1000)
                                .translationX(-dpToPx(150))
                                .scaleX(0.25f)
                                .scaleY(5f)
                                .setListener(new AnimatorListenerAdapter() {
                                    /**
                                     * {@inheritDoc}
                                     *
                                     * @param animation
                                     */
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        mainIcon.setTranslationX(-dpToPx(150));
                                        mainIcon.setScaleX(0.25f);
                                        mainIcon.setScaleY(5f);
                                    }
                                });
                    }
                })
                .addPremeditateAction(new MaterialPropertyAnimator.PremeditateAction() {
                    @Override
                    public void execute(MaterialIconView view, ValueAnimator animator) {
                        launchRandomAnimation(android, 1);
                    }

                    @Override
                    public boolean when(MaterialIconView view, ValueAnimator animator) {
                        return animator.getAnimatedFraction() >= 1f / 7;
                    }
                })
                .addPremeditateAction(new MaterialPropertyAnimator.PremeditateAction() {
                    @Override
                    public void execute(MaterialIconView view, ValueAnimator animator) {
                        launchRandomAnimation(line, 1);
                    }

                    @Override
                    public boolean when(MaterialIconView view, ValueAnimator animator) {
                        return animator.getAnimatedFraction() >= 2f / 7;
                    }
                })
                .addPremeditateAction(new MaterialPropertyAnimator.PremeditateAction() {
                    @Override
                    public void execute(MaterialIconView view, ValueAnimator animator) {
                        launchRandomAnimation(rectangle, 1);
                    }

                    @Override
                    public boolean when(MaterialIconView view, ValueAnimator animator) {
                        return animator.getAnimatedFraction() >= 3f / 7;
                    }
                })
                .addPremeditateAction(new MaterialPropertyAnimator.PremeditateAction() {
                    @Override
                    public void execute(MaterialIconView view, ValueAnimator animator) {
                        launchRandomAnimation(circle, 1);
                    }

                    @Override
                    public boolean when(MaterialIconView view, ValueAnimator animator) {
                        return animator.getAnimatedFraction() >= 4f / 7;
                    }
                })
                .addPremeditateAction(new MaterialPropertyAnimator.PremeditateAction() {
                    @Override
                    public void execute(MaterialIconView view, ValueAnimator animator) {
                        launchRandomAnimation(progressVertical, 1);
                    }

                    @Override
                    public boolean when(MaterialIconView view, ValueAnimator animator) {
                        return animator.getAnimatedFraction() >= 5f / 7;
                    }
                })
                .addPremeditateAction(new MaterialPropertyAnimator.PremeditateAction() {
                    @Override
                    public void execute(MaterialIconView view, ValueAnimator animator) {
                        launchRandomAnimation(progressHorizontal, 1);
                    }

                    @Override
                    public boolean when(MaterialIconView view, ValueAnimator animator) {
                        return animator.getAnimatedFraction() >= 6f / 7;
                    }
                })
                .setListener(new MaterialAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(ValueAnimator animation) {
                        super.onAnimationStart(animation);
                        shouldStop = false;
                    }
                });
    }

    private void launchRandomAnimation(final MaterialIconView view, final int translateDirection) {
        if (shouldStop)
            return;
        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);
        view.animateMaterial()
                .setDuration(1500)
                .setTransition(TypeOfTransition.Line)
                .setDirection(getRandomDirectOfTransition())
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .toColor(menuColor)
                .setListener(new MaterialAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(ValueAnimator animation) {
                        super.onAnimationEnd(animation);
                    }
                })
                .withIndependentAnimationView(new MaterialPropertyAnimator.ViewAnimation() {
                    @Override
                    public void animate(ViewPropertyAnimator animator) {
                        animator.translationX(0.5f * translateDirection * dpToPx(75))
                                .alpha(1f)
                                .setDuration(1500)
                                .setInterpolator(new DecelerateInterpolator())
                                .setListener(new AnimatorListenerAdapter() {
                                    /**
                                     * {@inheritDoc}
                                     *
                                     * @param animation
                                     */
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        view.setVisibility(View.VISIBLE);
                                        view.setAlpha(1f);
                                        view.setTranslationX(0.5f * translateDirection * dpToPx(75));
                                    }
                                });
                    }
                });
    }

    private void launchHorizontalAnimation() {
        if (shouldStop)
            return;
        isInTransition = false;

        mainIcon.animateMaterial()
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.LeftToRight)
                .toColor(getRandomMaterialColor())
                .setDuration(1500)

                .withPostAnimation()
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.RightToLeft)
                .toColor(getRandomMaterialColor())
                .setDuration(1500)
                .setListener(new MaterialAnimatorListenerAdapter() {
                    /**
                     * <p>Notifies the end of the animation.</p>
                     *
                     * @param animation The animation which reached its end.
                     */
                    @Override
                    public void onAnimationEnd(ValueAnimator animation) {
                        super.onAnimationEnd(animation);
                        launchHorizontalAnimation();
                    }
                });
    }

    private void launchVerticalAnimation() {
        if (shouldStop)
            return;
        isInTransition = false;

        mainIcon.animateMaterial()
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.DownToUp)
                .toColor(getRandomMaterialColor())
                .setDuration(1500)

                .withPostAnimation()
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.UpToDown)
                .toColor(getRandomMaterialColor())
                .setDuration(1500)
                .setListener(new MaterialAnimatorListenerAdapter() {
                    /**
                     * <p>Notifies the end of the animation.</p>
                     *
                     * @param animation The animation which reached its end.
                     */
                    @Override
                    public void onAnimationEnd(ValueAnimator animation) {
                        super.onAnimationEnd(animation);
                        launchVerticalAnimation();
                    }
                });
    }

    private void launchCircleAnimation() {
        if (shouldStop)
            return;
        isInTransition = false;

        int color = getRandomMaterialColor();
        mainIcon.animateMaterial()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 100))
                .setDirection(DirectionOfTransition.UpRightToDownLeft)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 200))
                .setDirection(DirectionOfTransition.RightToLeft)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 300))
                .setDirection(DirectionOfTransition.DownRightToUpLeft)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 400))
                .setDirection(DirectionOfTransition.DownToUp)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 500))
                .setDirection(DirectionOfTransition.DownLeftToUpRight)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 600))
                .setDirection(DirectionOfTransition.LeftToRight)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 700))
                .setDirection(DirectionOfTransition.UpLeftToDownRight)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 800))
                .setDirection(DirectionOfTransition.UpToDown)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new MaterialAnimatorListenerAdapter() {
                    /**
                     * <p>Notifies the end of the animation.</p>
                     *
                     * @param animation The animation which reached its end.
                     */
                    @Override
                    public void onAnimationStart(ValueAnimator animation) {
                        super.onAnimationStart(animation);
                        launchCircleAnimation();
                    }
                });
    }

    private void launchRectangleAnimation() {
        if (shouldStop)
            return;
        isInTransition = false;

        int color = getRandomMaterialColor();
        mainIcon.animateMaterial()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 100))
                .setTransition(TypeOfTransition.Rect)
                .setDirection(DirectionOfTransition.UpRightToDownLeft)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 200))
                .setTransition(TypeOfTransition.Rect)
                .setDirection(DirectionOfTransition.RightToLeft)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 300))
                .setTransition(TypeOfTransition.Rect)
                .setDirection(DirectionOfTransition.DownRightToUpLeft)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 400))
                .setTransition(TypeOfTransition.Rect)
                .setDirection(DirectionOfTransition.DownToUp)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 500))
                .setTransition(TypeOfTransition.Rect)
                .setDirection(DirectionOfTransition.DownLeftToUpRight)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 600))
                .setTransition(TypeOfTransition.Rect)
                .setDirection(DirectionOfTransition.LeftToRight)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 700))
                .setTransition(TypeOfTransition.Rect)
                .setDirection(DirectionOfTransition.UpLeftToDownRight)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 800))
                .setTransition(TypeOfTransition.Rect)
                .setDirection(DirectionOfTransition.UpToDown)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new MaterialAnimatorListenerAdapter() {
                    /**
                     * <p>Notifies the end of the animation.</p>
                     *
                     * @param animation The animation which reached its end.
                     */
                    @Override
                    public void onAnimationStart(ValueAnimator animation) {
                        super.onAnimationStart(animation);
                        launchRectangleAnimation();
                    }
                });

    }

    private void launchLineAnimation() {
        if (shouldStop)
            return;
        isInTransition = false;

        int color = getRandomMaterialColor();
        mainIcon.animateMaterial()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 100))
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.UpRightToDownLeft)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 200))
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.RightToLeft)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 300))
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.DownRightToUpLeft)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 400))
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.DownToUp)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 500))
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.DownLeftToUpRight)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 600))
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.LeftToRight)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 700))
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.UpLeftToDownRight)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())

                .withConcurrentAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(color, 800))
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.UpToDown)
                .endingArea(0.55f)
                .setDuration(350)
                .setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(new MaterialAnimatorListenerAdapter() {
                    /**
                     * <p>Notifies the end of the animation.</p>
                     *
                     * @param animation The animation which reached its end.
                     */
                    @Override
                    public void onAnimationStart(ValueAnimator animation) {
                        super.onAnimationStart(animation);
                        launchLineAnimation();
                    }
                });
    }

    private void launchAndroidAnimation() {
        if (shouldStop)
            return;
        isInTransition = false;

        if (rnd.nextInt(5) == 0)
            mainIcon.animateMaterial()
                    .setDuration(300 + rnd.nextInt(700))
                    .fromPoint(getRandomOrigin(mainIcon))
                    .setTransition(TypeOfTransition.Circle)
                    .setDirection(getRandomDirectOfTransition())
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .toColor(getRandomMaterialColor())
                    .endingArea(rnd.nextFloat())
                    .setListener(new MaterialAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(ValueAnimator animation) {
                            super.onAnimationEnd(animation);
                            launchAndroidAnimation();
                        }
                    });
        else
            mainIcon.animateMaterial()
                    .setDuration(300 + rnd.nextInt(700))
                    .setTransition(getRandomTypeOfTransition())
                    .setDirection(getRandomDirectOfTransition())
                    .toColor(getRandomMaterialColor())
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .endingArea(rnd.nextFloat())
                    .setListener(new MaterialAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(ValueAnimator animation) {
                            super.onAnimationEnd(animation);
                            launchAndroidAnimation();
                        }
                    });
    }

    private void hideItemsButNotThisView(MaterialIconView view) {
        if (view != progressHorizontal)
            hideItem(progressHorizontal);
        if (view != progressVertical)
            hideItem(progressVertical);
        if (view != circle)
            hideItem(circle);
        if (view != rectangle)
            hideItem(rectangle);
        if (view != line)
            hideItem(line);
        if (view != android)
            hideItem(android);
    }

    private void hideItem(final View view) {
        view.animate().setDuration(400).translationX(0.4f * dpToPx(75)).alpha(0f).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setAlpha(0f);
                view.setTranslationX(0f);
                view.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void updateToNewBitmapAndLaunchAnimation(final int resId, final int animationToLaunch) {
        mainIcon.animate().setDuration(300).scaleX(0).scaleY(0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mainIcon.setScaleX(0f);
                mainIcon.setScaleY(0f);
                if (resId == -1)
                    resetMainIcon();
                else
                    mainIcon.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), resId));
                mainIcon.animate().setDuration(300).scaleX(1).scaleY(1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        shouldStop = false;
                        mainIcon.setScaleX(1f);
                        mainIcon.setScaleY(1f);
                        switch (animationToLaunch) {
                            case 3:
                                launchCircleAnimation();
                                return;
                            case 4:
                                launchRectangleAnimation();
                                return;
                            case 5:
                                launchLineAnimation();
                                return;
                            case 6:
                                launchAndroidAnimation();
                                return;
                            default:launchMenuAnimation();
                        }
                    }
                });
            }
        });
    }

    private void closeMenu(final int animationToLaunch) {
        while ((menuColor = getRandomMaterialColor()) == MaterialColor.White);

        mainIcon.animateMaterial()
                .toColor(MaterialColor.White)
                .setTransition(TypeOfTransition.Circle)
                .fromPoint(new Point(mainIcon.getBitmapWidth() / 2, mainIcon.getBitmapHeight() / 2))
                .withDependentAnimationView(new MaterialPropertyAnimator.ViewAnimation() {
                    @Override
                    public void animate(ViewPropertyAnimator animator) {
                        animator
                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                .translationX(0)
                                .scaleY(1f)
                                .scaleX(1f)
                                .setListener(new AnimatorListenerAdapter() {
                            /**
                             * {@inheritDoc}
                             *
                             * @param animation
                             */
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mainIcon.setTranslationX(0);
                                mainIcon.setScaleX(1f);
                                mainIcon.setScaleY(1f);
                                switch (animationToLaunch) {
                                    case 1:
                                        shouldStop = false;
                                        mainIcon.animate().scaleX(3f).scaleY(0.25f).setListener(new AnimatorListenerAdapter() {
                                            @Override
                                            public void onAnimationEnd(Animator animation) {
                                                super.onAnimationEnd(animation);
                                                mainIcon.setScaleX(3f);
                                                mainIcon.setScaleY(0.25f);
                                            }
                                        });
                                        launchHorizontalAnimation();
                                        return;
                                    case 2:
                                        shouldStop = false;
                                        mainIcon.animate().scaleX(0.25f).scaleY(3f).setListener(new AnimatorListenerAdapter() {
                                            @Override
                                            public void onAnimationEnd(Animator animation) {
                                                super.onAnimationEnd(animation);
                                                mainIcon.setScaleX(0.25f);
                                                mainIcon.setScaleY(3f);
                                            }
                                        });
                                        launchVerticalAnimation();
                                        return;
                                    case 3:
                                        updateToNewBitmapAndLaunchAnimation(R.drawable.ic_cloud_done_white_48dp, animationToLaunch);
                                        return;
                                    case 4:
                                        updateToNewBitmapAndLaunchAnimation(R.drawable.ic_keyboard_white_48dp, animationToLaunch);
                                        return;
                                    case 5:
                                        updateToNewBitmapAndLaunchAnimation(R.drawable.ic_send_white_48dp, animationToLaunch);
                                        return;
                                    case 6:
                                        updateToNewBitmapAndLaunchAnimation(R.drawable.ic_android_black_48dp, animationToLaunch);
                                        return;
                                    default:
                                        updateToNewBitmapAndLaunchAnimation(-1, animationToLaunch);
                                }
                            }
                        });
                    }
                });
    }

    private Point getRandomOrigin(MaterialIconView view) {
        return new Point(rnd.nextInt(view.getBitmapWidth()), rnd.nextInt(view.getBitmapHeight()));
    }

    private TypeOfTransition getRandomTypeOfTransition() {
        List<TypeOfTransition> typeOfTransitionList = Arrays.asList(TypeOfTransition.values());
        return typeOfTransitionList.get(rnd.nextInt(typeOfTransitionList.size()));
    }

    private DirectionOfTransition getRandomDirectOfTransition() {
        List<DirectionOfTransition> directionOfTransitionList = Arrays.asList(DirectionOfTransition.values());
        return directionOfTransitionList.get(rnd.nextInt(directionOfTransitionList.size()));
    }

    private int getRandomMaterialColor() {
        int color = MaterialColor.Red;
        switch (rnd.nextInt(19)) {
            case 0:
                color = MaterialColor.Amber;
                break;
            case 1:
                color = MaterialColor.Blue;
                break;
            case 2:
                color = MaterialColor.BlueGrey;
                break;
            case 3:
                color = MaterialColor.Brown;
                break;
            case 4:
                color = MaterialColor.Cyan;
                break;
            case 5:
                color = MaterialColor.DeepOrange;
                break;
            case 6:
                color = MaterialColor.DeepPurple;
                break;
            case 7:
                color = MaterialColor.Green;
                break;
            case 8:
                color = MaterialColor.Indigo;
                break;
            case 9:
                color = MaterialColor.LightBlue;
                break;
            case 10:
                color = MaterialColor.LightGreen;
                break;
            case 11:
                color = MaterialColor.Lime;
                break;
            case 12:
                color = MaterialColor.Orange;
                break;
            case 13:
                color = MaterialColor.Yellow;
                break;
            case 14:
                color = MaterialColor.Pink;
                break;
            case 15:
                color = MaterialColor.Purple;
                break;
            case 16:
                color = MaterialColor.Grey;
                break;
            case 17:
                return MaterialColor.White;
            case 18:
                return MaterialColor.Black;
        }
        return MaterialColor.getMaterialColorByIndice(color, 500);
    }

    private int dpToPx(int dp) {
        getResources();
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
