package com.adrienfenech.Sample;

import android.animation.ValueAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.adrienfenech.materialiconview.DirectionOfTransition;
import com.adrienfenech.materialiconview.MaterialAnimatorListenerAdapter;
import com.adrienfenech.materialiconview.MaterialColor;
import com.adrienfenech.materialiconview.MaterialIconView;
import com.adrienfenech.materialiconview.TypeOfTransition;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CustomActivity extends AppCompatActivity {
    Random rnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout);
        rnd = new Random();

        findViewById(R.id.icon1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAnimationCircle((MaterialIconView) v);
            }
        });


        findViewById(R.id.icon2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAnimationRectangle((MaterialIconView) v);
            }
        });


        findViewById(R.id.icon3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAnimationLine((MaterialIconView) v);
            }
        });


        findViewById(R.id.icon4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRandomAnimation((MaterialIconView) v);
            }
        });


        findViewById(R.id.icon5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRandomCircleAnimation((MaterialIconView) v);
            }
        });
    }

    private void launchAnimationCircle(final MaterialIconView view) {
        int color = getRandomMaterialColor();
        view.animateMaterial()
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
                        launchAnimationCircle(view);
                    }
                });
    }

    private void launchAnimationRectangle(final MaterialIconView view) {
        int color = getRandomMaterialColor();
        view.animateMaterial()
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
                        launchAnimationRectangle(view);
                    }
                });
    }

    private void launchAnimationLine(final MaterialIconView view) {
        int color = getRandomMaterialColor();
        view.animateMaterial()
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
                        launchAnimationLine(view);
                    }
                });
    }


    private void launchRandomAnimation(final MaterialIconView view) {
        if (rnd.nextInt(5) == 0)
            view.animateMaterial()
                    .setDuration(500 + rnd.nextInt(1000))
                    .fromPoint(getRandomOrigin(view))
                    .setTransition(TypeOfTransition.Circle)
                    .setDirection(getRandomDirectOfTransition())
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .toColor(getRandomMaterialColor())
                    .endingArea(rnd.nextFloat())
                    .setListener(new MaterialAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(ValueAnimator animation) {
                            super.onAnimationEnd(animation);
                            launchRandomAnimation(view);
                        }
                    });
        else
            view.animateMaterial()
                    .setDuration(500 + rnd.nextInt(1000))
                    .setTransition(getRandomTypeOfTransition())
                    .setDirection(getRandomDirectOfTransition())
                    .toColor(getRandomMaterialColor())
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .endingArea(rnd.nextFloat())
                    .setListener(new MaterialAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(ValueAnimator animation) {
                            super.onAnimationEnd(animation);
                            launchRandomAnimation(view);
                        }
                    });
    }

    private void launchRandomCircleAnimation(final MaterialIconView view) {
        view.animateMaterial()
                .setDuration(300 + rnd.nextInt(700))
                .fromPoint(getRandomOrigin(view))
                .setTransition(TypeOfTransition.Circle)
                .setDirection(getRandomDirectOfTransition())
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .toColor(getRandomMaterialColor())
                .endingArea(rnd.nextFloat())
                .setListener(new MaterialAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(ValueAnimator animation) {
                        super.onAnimationEnd(animation);
                        launchRandomCircleAnimation(view);
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
}
