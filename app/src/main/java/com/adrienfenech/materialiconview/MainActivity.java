package com.adrienfenech.materialiconview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random rnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        rnd = new Random();
        final DirectionOfTransition[] directionOfTransition = {DirectionOfTransition.UpToDown};
        final int[] directionOfTransitionId = {0};
        final int[] i = {0};

        final View.OnClickListener onClickListener1 = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final MaterialIconView view = (MaterialIconView) v;
                launchNewAnimation1(view);
            }
        };

        final View.OnClickListener onClickListener2 = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final MaterialIconView view = (MaterialIconView) v;
                launchNewAnimation2(view);
            }
        };

        final View.OnClickListener onClickListener3 = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final MaterialIconView view = (MaterialIconView) v;
                launchNewAnimation3(view);
            }
        };

        final View.OnClickListener onClickListener4 = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final MaterialIconView view = (MaterialIconView) v;
                launchNewAnimation4(view);
            }
        };

        final View.OnClickListener onClickListener5 = new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final MaterialIconView view = (MaterialIconView) v;
                launchNewAnimation5(view);
            }
        };


        final MaterialIconView icon1 = (MaterialIconView) findViewById(R.id.icon1);
        icon1.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_bluetooth_white_48dp));
        icon1.setOnClickListener(onClickListener1);

        final MaterialIconView icon2 = (MaterialIconView) findViewById(R.id.icon2);
        icon2.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_chrome_reader_mode_white_48dp));
        icon2.setOnClickListener(onClickListener1);

        final MaterialIconView icon3 = (MaterialIconView) findViewById(R.id.icon3);
        icon3.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_android_white_48dp));
        icon3.setOnClickListener(onClickListener1);

        final MaterialIconView icon4 = (MaterialIconView) findViewById(R.id.icon4);
        icon4.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_wifi_white_48dp));
        icon4.setOnClickListener(onClickListener1);


        final MaterialIconView icon5 = (MaterialIconView) findViewById(R.id.icon5);
        icon5.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_bluetooth_white_48dp));
        icon5.setOnClickListener(onClickListener2);

        final MaterialIconView icon6 = (MaterialIconView) findViewById(R.id.icon6);
        icon6.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_chrome_reader_mode_white_48dp));
        icon6.setOnClickListener(onClickListener2);

        final MaterialIconView icon7 = (MaterialIconView) findViewById(R.id.icon7);
        icon7.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_android_white_48dp));
        icon7.setOnClickListener(onClickListener2);

        final MaterialIconView icon8 = (MaterialIconView) findViewById(R.id.icon8);
        icon8.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_wifi_white_48dp));
        icon8.setOnClickListener(onClickListener2);


        final MaterialIconView icon9 = (MaterialIconView) findViewById(R.id.icon9);
        icon9.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_bluetooth_white_48dp));
        icon9.setOnClickListener(onClickListener3);

        final MaterialIconView icon10 = (MaterialIconView) findViewById(R.id.icon10);
        icon10.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_chrome_reader_mode_white_48dp));
        icon10.setOnClickListener(onClickListener3);

        final MaterialIconView icon11 = (MaterialIconView) findViewById(R.id.icon11);
        icon11.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_android_white_48dp));
        icon11.setOnClickListener(onClickListener3);

        final MaterialIconView icon12 = (MaterialIconView) findViewById(R.id.icon12);
        icon12.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_wifi_white_48dp));
        icon12.setOnClickListener(onClickListener3);


        final MaterialIconView icon13 = (MaterialIconView) findViewById(R.id.icon13);
        icon13.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_bluetooth_white_48dp));
        icon13.setOnClickListener(onClickListener4);

        final MaterialIconView icon14 = (MaterialIconView) findViewById(R.id.icon14);
        icon14.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_chrome_reader_mode_white_48dp));
        icon14.setOnClickListener(onClickListener4);

        final MaterialIconView icon15 = (MaterialIconView) findViewById(R.id.icon15);
        icon15.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_android_white_48dp));
        icon15.setOnClickListener(onClickListener4);

        final MaterialIconView icon16 = (MaterialIconView) findViewById(R.id.icon16);
        icon16.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_wifi_white_48dp));
        icon16.setOnClickListener(onClickListener4);


        final MaterialIconView icon17 = (MaterialIconView) findViewById(R.id.icon17);
        icon17.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_bluetooth_white_48dp));
        icon17.setOnClickListener(onClickListener5);

        final MaterialIconView icon18 = (MaterialIconView) findViewById(R.id.icon18);
        icon18.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_chrome_reader_mode_white_48dp));
        icon18.setOnClickListener(onClickListener5);

        final MaterialIconView icon19 = (MaterialIconView) findViewById(R.id.icon19);
        icon19.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_android_white_48dp));
        icon19.setOnClickListener(onClickListener5);

        final MaterialIconView icon20 = (MaterialIconView) findViewById(R.id.icon20);
        icon20.setMaterialImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_wifi_white_48dp));
        icon20.setOnClickListener(onClickListener5);
    }

    private void launchNewAnimation1(final MaterialIconView view) {
        if (rnd.nextInt(5) == 0)
            view.animateMaterial()
                    .duration(500 + rnd.nextInt(1000))
                    .fromPoint(new Point(view.getBitmapWidth() / 2, view.getBitmapHeight() / 2))
                    .typeOfTransition(TypeOfTransition.Circle)
                    .directionOfTransition(getRandomDirectOfTransition())
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .toColor(getRandomMaterialColor())
                    .endingArea(rnd.nextFloat())
                    .setListener(new MaterialAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(ValueAnimator animation) {
                            super.onAnimationEnd(animation);
                            launchNewAnimation1(view);
                        }
                    });
        else
            view.animateMaterial()
                    .duration(500 + rnd.nextInt(1000))
                    .typeOfTransition(getRandomTypeOfTransition())
                    .directionOfTransition(getRandomDirectOfTransition())
                    .toColor(getRandomMaterialColor())
                    .setInterpolator(new AccelerateDecelerateInterpolator())
                    .endingArea(rnd.nextFloat())
                    .setListener(new MaterialAnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(ValueAnimator animation) {
                            super.onAnimationEnd(animation);
                            launchNewAnimation1(view);
                        }
                    });
    }

    private void launchNewAnimation2(final MaterialIconView view) {
        view.animateMaterial()
                .typeOfTransition(getRandomTypeOfTransition())
                .directionOfTransition(getRandomDirectOfTransition())
                .toColor(getRandomMaterialColor())
                .startingDelay(2000)
                .duration(1000)
                .withDependantAnimationView(new MaterialPropertyAnimator.ViewAnimation() {
                    @Override
                    public void animate(ViewPropertyAnimator animator) {
                        animator.rotation(360).setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new AnimatorListenerAdapter() {
                            /**
                             * {@inheritDoc}
                             *
                             * @param animation
                             */
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                view.setRotation(0f);
                            }
                        });
                    }
                });
    }

    private void launchNewAnimation3(final MaterialIconView view) {
        view.animateMaterial()
                .typeOfTransition(getRandomTypeOfTransition())
                .directionOfTransition(getRandomDirectOfTransition())
                .toColor(getRandomMaterialColor())
                .startingDelay(2000)
                .duration(1000)
                .withIndependantAnimationView(new MaterialPropertyAnimator.ViewAnimation() {
                    @Override
                    public void animate(ViewPropertyAnimator animator) {
                        animator.rotation(360).setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new AnimatorListenerAdapter() {
                            /**
                             * {@inheritDoc}
                             *
                             * @param animation
                             */
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                view.setRotation(0f);
                            }
                        });
                    }
                });
    }

    private void launchNewAnimation4(final MaterialIconView view) {
        view.animateMaterial()
                .toColor(MaterialColor.getMaterialColorByIndice(MaterialColor.Teal, 500))
                .directionOfTransition(DirectionOfTransition.UpRightToDownLeft)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .endingArea(1f)
                .setListener(new MaterialAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(ValueAnimator animation) {
                        super.onAnimationEnd(animation);
                        view.animateMaterial()
                                .toColor(MaterialColor.getMaterialColorByIndice(MaterialColor.Cyan, 500))
                                .directionOfTransition(DirectionOfTransition.RightToLeft)
                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                .endingArea(0.6f)
                                .setListener(new MaterialAnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(ValueAnimator animation) {
                                        super.onAnimationEnd(animation);
                                        view.animateMaterial()
                                                .toColor(MaterialColor.getMaterialColorByIndice(MaterialColor.Blue, 500))
                                                .directionOfTransition(DirectionOfTransition.DownRightToUpLeft)
                                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                                .endingArea(0.4f);
                                    }
                                });
                    }
                });
    }

    private void launchNewAnimation5(final MaterialIconView view) {
        view.animateMaterial()
                .toColor(MaterialColor.getMaterialColorByIndice(MaterialColor.Teal, 500))
                .directionOfTransition(DirectionOfTransition.UpRightToDownLeft)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .endingArea(1f)

             /** New Post Animation **/
                .newPostAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(MaterialColor.Cyan, 500))
                .directionOfTransition(DirectionOfTransition.RightToLeft)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .endingArea(0.6f)

             /** New Post Animation **/
                .newPostAnimation()
                .toColor(MaterialColor.getMaterialColorByIndice(MaterialColor.Blue, 500))
                .directionOfTransition(DirectionOfTransition.DownRightToUpLeft)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .endingArea(0.4f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        switch (rnd.nextInt(17)) {
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
        }
        return MaterialColor.getMaterialColorByIndice(color, 500);
    }
}
