package com.adrienfenech.Sample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
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
 * Created by octo on 17/05/16.
 */
public class PremeditateActivity extends AppCompatActivity {
    Random rnd;
    MaterialIconView mainIcon, icon1, icon2, icon3, icon4, icon5, icon6;
    boolean shouldStop = true;
    int menuColor = MaterialColor.BlueGrey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.premeditate_layout);
        rnd = new Random();

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MaterialIconView view = (MaterialIconView)v;
                view.animateMaterial()
                        .toColor(getRandomMaterialColor())
                        .setTransition(getRandomTypeOfTransition())
                        .setDirection(getRandomDirectOfTransition())
                        .fromPoint(getRandomOrigin(view))
                        .withDependentAnimationView(new MaterialPropertyAnimator.ViewAnimation() {
                            @Override
                            public void animate(ViewPropertyAnimator animator) {
                                animator.scaleX(1.3f).scaleY(1.3f).setListener(new AnimatorListenerAdapter() {
                                    /**
                                     * {@inheritDoc}
                                     *
                                     * @param animation
                                     */
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        view.setScaleX(1.3f);
                                        view.setScaleY(1.3f);
                                        view.animateMaterial()
                                                .toColor(menuColor)
                                                .setTransition(getRandomTypeOfTransition())
                                                .setDirection(getRandomDirectOfTransition())
                                                .fromPoint(getRandomOrigin(view))
                                                .withDependentAnimationView(new MaterialPropertyAnimator.ViewAnimation() {
                                                    @Override
                                                    public void animate(ViewPropertyAnimator animator) {
                                                        animator.scaleX(1f).scaleY(1f).setListener(new AnimatorListenerAdapter() {
                                                            /**
                                                             * {@inheritDoc}
                                                             *
                                                             * @param animation
                                                             */
                                                            @Override
                                                            public void onAnimationEnd(Animator animation) {
                                                                super.onAnimationEnd(animation);
                                                                view.setScaleX(1f);
                                                                view.setScaleY(1f);
                                                            }
                                                        });
                                                    }
                                                });
                                    }
                                });
                            }
                        });
            }
        };

        mainIcon = (MaterialIconView) findViewById(R.id.mainIcon);
        icon1 = (MaterialIconView) findViewById(R.id.icon1);
        icon2 = (MaterialIconView) findViewById(R.id.icon2);
        icon3 = (MaterialIconView) findViewById(R.id.icon3);
        icon4 = (MaterialIconView) findViewById(R.id.icon4);
        icon5 = (MaterialIconView) findViewById(R.id.icon5);
        icon6 = (MaterialIconView) findViewById(R.id.icon6);

        icon1.setOnClickListener(clickListener);
        icon2.setOnClickListener(clickListener);
        icon3.setOnClickListener(clickListener);
        icon4.setOnClickListener(clickListener);
        icon5.setOnClickListener(clickListener);
        icon6.setOnClickListener(clickListener);

        Bitmap bitmap = Bitmap.createBitmap(dpToPx(80), dpToPx(525), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);

        mainIcon.setMaterialImageBitmap(bitmap);

        mainIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });
    }

    private void init() {
        while ((menuColor = getRandomMaterialColor()) == MaterialColor.White);

        mainIcon.animateMaterial()
                .toColor(MaterialColor.White)
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.LeftToRight)
                .withDependentAnimationView(new MaterialPropertyAnimator.ViewAnimation() {
                    @Override
                    public void animate(ViewPropertyAnimator animator) {
                        animator.setInterpolator(new AccelerateDecelerateInterpolator()).translationX(0).scaleX(1f).setListener(new AnimatorListenerAdapter() {
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
                                launchMainAnimation();
                            }
                        });
                    }
                });

        init(icon1);
        init(icon2);
        init(icon3);
        init(icon4);
        init(icon5);
        init(icon6);

        shouldStop = true;
    }

    private void init(final MaterialIconView view) {
        view.animate().setDuration(300).alpha(0f).translationXBy(dpToPx(20)).setListener(new AnimatorListenerAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.INVISIBLE);
                view.setTranslationX(0);
                view.resetMaterialImageBitmap();
            }
        });
    }

    private void launchMainAnimation() {
        mainIcon.animateMaterial()
                .setDuration(2000)
                .setStartDelay(1000)
                .setInterpolator(new DecelerateInterpolator())
                .setTransition(TypeOfTransition.Line)
                .toColor(menuColor)
                //.toColor(MaterialColor.Lime)
                .withIndependentAnimationView(new MaterialPropertyAnimator.ViewAnimation() {
                    @Override
                    public void animate(ViewPropertyAnimator animator) {
                        animator
                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                .setDuration(1000)
                                .translationX(-dpToPx(150))
                                .scaleX(0.25f)
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
                            }
                        });
                    }
                })
                .addPremeditateAction(new MaterialPropertyAnimator.PremeditateAction() {
                    @Override
                    public void execute(MaterialIconView view, ValueAnimator animator) {
                        launchRandomAnimation(icon6, 1);
                    }

                    @Override
                    public boolean when(MaterialIconView view, ValueAnimator animator) {
                        return animator.getAnimatedFraction() >= 1f / 7;
                    }
                })
                .addPremeditateAction(new MaterialPropertyAnimator.PremeditateAction() {
                    @Override
                    public void execute(MaterialIconView view, ValueAnimator animator) {
                        launchRandomAnimation(icon5, 1);
                    }

                    @Override
                    public boolean when(MaterialIconView view, ValueAnimator animator) {
                        return animator.getAnimatedFraction() >= 2f / 7;
                    }
                })
                .addPremeditateAction(new MaterialPropertyAnimator.PremeditateAction() {
                    @Override
                    public void execute(MaterialIconView view, ValueAnimator animator) {
                        launchRandomAnimation(icon4, 1);
                    }

                    @Override
                    public boolean when(MaterialIconView view, ValueAnimator animator) {
                        return animator.getAnimatedFraction() >= 3f / 7;
                    }
                })
                .addPremeditateAction(new MaterialPropertyAnimator.PremeditateAction() {
                    @Override
                    public void execute(MaterialIconView view, ValueAnimator animator) {
                        launchRandomAnimation(icon3, 1);
                    }

                    @Override
                    public boolean when(MaterialIconView view, ValueAnimator animator) {
                        return animator.getAnimatedFraction() >= 4f / 7;
                    }
                })
                .addPremeditateAction(new MaterialPropertyAnimator.PremeditateAction() {
                    @Override
                    public void execute(MaterialIconView view, ValueAnimator animator) {
                        launchRandomAnimation(icon2, 1);
                    }

                    @Override
                    public boolean when(MaterialIconView view, ValueAnimator animator) {
                        return animator.getAnimatedFraction() >= 5f / 7;
                    }
                })
                .addPremeditateAction(new MaterialPropertyAnimator.PremeditateAction() {
                    @Override
                    public void execute(MaterialIconView view, ValueAnimator animator) {
                        launchRandomAnimation(icon1, 1);
                    }

                    @Override
                    public boolean when(MaterialIconView view, ValueAnimator animator) {
                        return animator.getAnimatedFraction() >= 6f / 7;
                    }
                })
                .setListener(new MaterialAnimatorListenerAdapter() {
                    /**
                     * <p>Notifies the start of the animation.</p>
                     *
                     * @param animation The started animation.
                     */
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

        if (translateDirection == 0)
            view.animateMaterial()
                    .setDuration(300 + rnd.nextInt(700))
                    .fromPoint(getRandomOrigin(view))
                    .setTransition(getRandomTypeOfTransition())
                    .setDirection(getRandomDirectOfTransition())
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    //.toColor(getRandomMaterialColor())
                    .toColor(menuColor)
                    .endingArea(rnd.nextFloat())
                    .setListener(new MaterialAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(ValueAnimator animation) {
                            super.onAnimationEnd(animation);
                            //launchRandomAnimation(view, 0);
                        }
                    });
        else {
            view.setAlpha(0f);
            view.setVisibility(View.VISIBLE);
            view.animateMaterial()
                    //.setDuration(300 + rnd.nextInt(700))
                    .setDuration(1500)
                    .fromPoint(getRandomOrigin(view))
                    .setTransition(TypeOfTransition.Circle)
                    .setDirection(getRandomDirectOfTransition())
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    //.toColor(getRandomMaterialColor())
                    .toColor(menuColor)
                    //.endingArea(rnd.nextFloat())
                    .setListener(new MaterialAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(ValueAnimator animation) {
                            super.onAnimationEnd(animation);
                            //launchRandomAnimation(view, 0);
                        }
                    })
                    .withIndependentAnimationView(new MaterialPropertyAnimator.ViewAnimation() {
                        @Override
                        public void animate(ViewPropertyAnimator animator) {
                            animator
                                    .translationX(0.5f * translateDirection * dpToPx(75))
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


    public int dpToPx(int dp) {
        getResources();
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
