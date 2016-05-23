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

import com.adrienfenech.materialiconview.MaterialAnimatorListenerAdapter;
import com.adrienfenech.materialiconview.MaterialColor;
import com.adrienfenech.materialiconview.MaterialIconView;
import com.adrienfenech.materialiconview.MaterialPropertyAnimator;
import com.adrienfenech.materialiconview.MaterialPropertyAnimator.PremeditateAction;
import com.adrienfenech.materialiconview.TypeOfTransition;

import java.util.ArrayList;
import java.util.List;

import static com.adrienfenech.Sample.AnimationHelper.launchAndroidAnimation;
import static com.adrienfenech.Sample.AnimationHelper.launchCircleAnimation;
import static com.adrienfenech.Sample.AnimationHelper.launchHorizontalAnimation;
import static com.adrienfenech.Sample.AnimationHelper.launchLineAnimation;
import static com.adrienfenech.Sample.AnimationHelper.launchRectangleAnimation;
import static com.adrienfenech.Sample.AnimationHelper.launchTouchAnimation;
import static com.adrienfenech.Sample.AnimationHelper.launchVerticalAnimation;
import static com.adrienfenech.Sample.MaterialHelper.getRandomDirectOfTransition;
import static com.adrienfenech.Sample.MaterialHelper.getRandomMaterialColor;
import static com.adrienfenech.Sample.MaterialHelper.getRandomTypeOfTransition;

/**
 * Created by octo on 19/05/16.
 */
public class MainActivity extends AppCompatActivity {
    private final List<MaterialIconView> materialViews = new ArrayList<>();

    boolean isBlankBitmap = true;
    int menuColor = MaterialColor.BlueGrey;
    private boolean isInTouchAnimation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        materialViews.add((MaterialIconView) findViewById(R.id.mainIcon));
        materialViews.add((MaterialIconView) findViewById(R.id.progressHorizontal));
        materialViews.add((MaterialIconView) findViewById(R.id.progressVertical));
        materialViews.add((MaterialIconView) findViewById(R.id.circle));
        materialViews.add((MaterialIconView) findViewById(R.id.rectangle));
        materialViews.add((MaterialIconView) findViewById(R.id.line));
        materialViews.add((MaterialIconView) findViewById(R.id.android));
        materialViews.add((MaterialIconView) findViewById(R.id.touch));

        for (int i = 0; i < materialViews.size(); i++) {
            final MaterialIconView view = materialViews.get(i);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (MaterialIconView tempView : materialViews)
                        tempView.setClickable(false);
                    cancelAllAnimations();
                    hideItemsButNotThisView(view);
                    if (finalI == materialViews.size() - 1)
                        isInTouchAnimation = !isInTouchAnimation;
                    resetMenu(finalI);
                }
            });
        }

        MaterialIconView view;

        resetMainIcon();

        Bitmap iconHorizontal = Bitmap.createBitmap(dpToPx(75), dpToPx(20), Bitmap.Config.ARGB_8888);
        Canvas iconHorizontalCanvas = new Canvas(iconHorizontal);
        iconHorizontalCanvas.drawColor(Color.WHITE);
        materialViews.get(1).setMaterialImageBitmap(iconHorizontal);

        Bitmap iconVertical = Bitmap.createBitmap(dpToPx(20), dpToPx(75), Bitmap.Config.ARGB_8888);
        Canvas iconVerticalCanvas = new Canvas(iconVertical);
        iconVerticalCanvas.drawColor(Color.WHITE);
        materialViews.get(2).setMaterialImageBitmap(iconVertical);
    }

    private void resetMainIcon() {
        Bitmap bitmap = Bitmap.createBitmap(dpToPx(100), dpToPx(100), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        materialViews.get(0).setMaterialImageBitmap(bitmap);
        isBlankBitmap = true;
    }

    private void cancelAllAnimations() {
        for (MaterialIconView view : materialViews)
            view.cancelAllMaterialAnimation();
    }

    private void launchMenuAnimation() {
        menuColor = getRandomMaterialColor();
        final MaterialIconView mainView = materialViews.get(0);

        // Fill the line from Down to Up (Default direction)
        mainView.animateMaterial()
                .setDuration(2000)
                .setStartDelay(1000)
                .setInterpolator(new DecelerateInterpolator())
                .setTransition(TypeOfTransition.Line)
                .toColor(menuColor)

                // Move center square to left line
                .withIndependentAnimationView(new MaterialPropertyAnimator.ViewAnimation() {
                    @Override
                    public void animate(ViewPropertyAnimator animator) {
                        animator.setInterpolator(new AccelerateDecelerateInterpolator())
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
                                        mainView.setTranslationX(-dpToPx(150));
                                        mainView.setScaleX(0.25f);
                                        mainView.setScaleY(5f);
                                    }
                                });
                    }
                })
                // Add premeditate action to display item according to line filling
                .addPremeditateAction(generatePremeditateAction(1))
                .addPremeditateAction(generatePremeditateAction(2))
                .addPremeditateAction(generatePremeditateAction(3))
                .addPremeditateAction(generatePremeditateAction(4))
                .addPremeditateAction(generatePremeditateAction(5))
                .addPremeditateAction(generatePremeditateAction(6))
                .addPremeditateAction(generatePremeditateAction(7))
                .setListener(new MaterialAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(ValueAnimator animation) {
                        super.onAnimationEnd(animation);
                        for (MaterialIconView view : materialViews)
                            view.setClickable(true);
                    }
                });
    }

    private void showMenuItem(final MaterialIconView view) {
        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);

        // Fill menu item with menuColor
        view.animateMaterial()
                .setDuration(1500)
                .setTransition(getRandomTypeOfTransition())
                .setDirection(getRandomDirectOfTransition())
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .toColor(menuColor)

                // Display menu item
                .withIndependentAnimationView(new MaterialPropertyAnimator.ViewAnimation() {
                    @Override
                    public void animate(ViewPropertyAnimator animator) {
                        animator.translationX(0.5f * dpToPx(75))
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
                                        view.setTranslationX(0.5f * dpToPx(75));
                                    }
                                });
                    }
                });
    }

    private void hideItemsButNotThisView(MaterialIconView viewToSave) {
        for (int i = 1; i < materialViews.size(); i++) {
            final MaterialIconView view = materialViews.get(i);
            if (view != viewToSave)
                hideItem(view);
        }
    }

    private void hideItem(final View view) {
        // Basic item hiding
        view.animate()
                .setDuration(400)
                .translationX(0.4f * dpToPx(75))
                .alpha(0f)
                .setListener(new AnimatorListenerAdapter() {
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
        final MaterialIconView mainView = materialViews.get(0);
        mainView.animate().setDuration(300).scaleX(0).scaleY(0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mainView.setScaleX(0f);
                mainView.setScaleY(0f);
                if (resId == -1)
                    resetMainIcon();
                else {
                    mainView.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), resId));
                    isBlankBitmap = false;
                }
                mainView.animate().setDuration(300).scaleX(1).scaleY(1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mainView.setScaleX(1f);
                        mainView.setScaleY(1f);
                        mainView.setClickable(true);
                        materialViews.get(animationToLaunch).setClickable(true);
                        switch (animationToLaunch) {
                            case 3:
                                launchCircleAnimation(mainView);
                                return;
                            case 4:
                                launchRectangleAnimation(mainView);
                                return;
                            case 5:
                                launchLineAnimation(mainView);
                                return;
                            case 6:
                                launchAndroidAnimation(mainView);
                                return;
                            case 7:
                                launchTouchAnimation(mainView);
                                return;
                            default:
                                materialViews.get(0).setClickable(false);
                                launchMenuAnimation();
                        }
                    }
                });
            }
        });
    }

    private void resetMenu(final int animationToLaunch) {
        final MaterialIconView mainView = materialViews.get(0);
        mainView.animateMaterial()
                .toColor(MaterialColor.White)
                .setTransition(TypeOfTransition.Circle)
                .fromPoint(new Point(mainView.getBitmapWidth() / 2, mainView.getBitmapHeight() / 2))
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
                                mainView.setTranslationX(0);
                                mainView.setScaleX(1f);
                                mainView.setScaleY(1f);
                                if (isBlankBitmap) {
                                    switch (animationToLaunch) {
                                        case 1:
                                            mainView.animate().scaleX(3f).scaleY(0.25f).setListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    super.onAnimationEnd(animation);
                                                    mainView.setScaleX(3f);
                                                    mainView.setScaleY(0.25f);
                                                }
                                            });
                                            mainView.setClickable(true);
                                            materialViews.get(1).setClickable(true);
                                            launchHorizontalAnimation(mainView);
                                            return;
                                        case 2:
                                            mainView.animate().scaleX(0.25f).scaleY(3f).setListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    super.onAnimationEnd(animation);
                                                    mainView.setScaleX(0.25f);
                                                    mainView.setScaleY(3f);
                                                }
                                            });
                                            mainView.setClickable(true);
                                            materialViews.get(2).setClickable(true);
                                            launchVerticalAnimation(mainView);
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
                                        case 7:
                                            swapTouchItem();
                                            mainView.animate().scaleX(3f).scaleY(3f).setListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    super.onAnimationEnd(animation);
                                                    mainView.setScaleX(3f);
                                                    mainView.setScaleY(3f);
                                                }
                                            });
                                            mainView.setClickable(true);
                                            materialViews.get(7).setClickable(true);
                                            launchTouchAnimation(mainView);
                                            return;
                                        default:
                                            launchMenuAnimation();
                                    }
                                } else {
                                    materialViews.get(0).setClickable(true);
                                    materialViews.get(animationToLaunch).setClickable(true);
                                    switch (animationToLaunch) {
                                        case 3:
                                            launchCircleAnimation(mainView);
                                            return;
                                        case 4:
                                            launchRectangleAnimation(mainView);
                                            return;
                                        case 5:
                                            launchLineAnimation(mainView);
                                            return;
                                        case 6:
                                            launchAndroidAnimation(mainView);
                                            return;
                                        case 7:
                                            launchTouchAnimation(mainView);
                                            return;
                                        default:
                                            updateToNewBitmapAndLaunchAnimation(-1, animationToLaunch);
                                    }
                                }
                            }
                        });
                    }
                });
    }

    private PremeditateAction generatePremeditateAction(final int itemPosition) {
        return new PremeditateAction() {
            @Override
            public void execute(MaterialIconView view, ValueAnimator animator) {
                showMenuItem(materialViews.get(materialViews.size() - itemPosition));
            }

            @Override
            public boolean when(MaterialIconView view, ValueAnimator animator) {
                return animator.getAnimatedFraction() >= (float)(itemPosition) / 8;
            }
        };
    }

    private void swapTouchItem() {
        final MaterialIconView touchItemView = materialViews.get(materialViews.size() - 1);
        touchItemView.setClickable(false);
            touchItemView.animate().scaleX(0).scaleY(0).setDuration(400).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    touchItemView.setScaleX(0);
                    touchItemView.setScaleY(0);

                    if (isInTouchAnimation) {
                        touchItemView.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_white_48dp));
                        touchItemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                for (MaterialIconView tempView : materialViews)
                                    tempView.setClickable(false);
                                cancelAllAnimations();
                                hideItemsButNotThisView(materialViews.get(0));
                                isInTouchAnimation = !isInTouchAnimation;
                                resetMenu(0);
                                swapTouchItem();
                            }
                        });
                    }
                    else {
                        materialViews.get(0).setOnTouchListener(null);

                        touchItemView.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_touch_app_white_48dp));
                        touchItemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                for (MaterialIconView tempView : materialViews)
                                    tempView.setClickable(false);
                                cancelAllAnimations();
                                hideItemsButNotThisView((MaterialIconView)v);
                                isInTouchAnimation = !isInTouchAnimation;
                                resetMenu(materialViews.size() - 1);
                            }
                        });
                    }

                    touchItemView.animateMaterial().toColor(menuColor).withDependentAnimationView(new MaterialPropertyAnimator.ViewAnimation() {
                        @Override
                        public void animate(ViewPropertyAnimator animator) {
                            touchItemView.animate().scaleX(1).scaleY(1).setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    touchItemView.setScaleX(1);
                                    touchItemView.setScaleY(1);
                                    touchItemView.setClickable(true);
                                }
                            });
                        }
                    });
                }
            });
    }

    private int dpToPx(int dp) {
        getResources();
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
