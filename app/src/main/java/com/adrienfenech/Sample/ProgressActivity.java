package com.adrienfenech.Sample;

import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.adrienfenech.materialiconview.DirectionOfTransition;
import com.adrienfenech.materialiconview.MaterialAnimatorListenerAdapter;
import com.adrienfenech.materialiconview.MaterialColor;
import com.adrienfenech.materialiconview.MaterialIconView;
import com.adrienfenech.materialiconview.MaterialPropertyAnimator;
import com.adrienfenech.materialiconview.TypeOfTransition;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;

/**
 * Created by octo on 19/05/16.
 */
public class ProgressActivity extends AppCompatActivity {
    MaterialIconView progressHorizontal, progressVertical;
    MaterialPropertyAnimator horizontalAnimator, verticalAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_layout);

        progressHorizontal = (MaterialIconView) findViewById(R.id.progressHorizontal);
        progressVertical = (MaterialIconView) findViewById(R.id.progressVertical);

        initMaterialView(progressHorizontal, HORIZONTAL);
        initMaterialView(progressVertical, VERTICAL);

        launchHorizontalAnimation();
        launchVerticalAnimation();

        progressVertical.setOnTouchListener(new View.OnTouchListener() {
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();

                        if (verticalAnimator != null)
                            verticalAnimator.cancel();
                        if (horizontalAnimator != null)
                            horizontalAnimator.cancel();

                        verticalAnimator = null;
                        horizontalAnimator = null;

                        return true;
                    case MotionEvent.ACTION_UP:
                        float currentTouchX = event.getRawX();
                        float currentTouchY = event.getRawY();


                        return true;
                    case MotionEvent.ACTION_MOVE:
                        return true;
                }
                return false;
            }
        });
    }

    private void initMaterialView(MaterialIconView view, int orientation) {
        Bitmap bitmap;
        if (orientation == HORIZONTAL)
            bitmap = Bitmap.createBitmap(dpToPx(300), dpToPx(50), Bitmap.Config.ARGB_8888);
        else
            bitmap = Bitmap.createBitmap(dpToPx(50), dpToPx(300), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        view.setMaterialImageBitmap(bitmap);
    }

    public int dpToPx(int dp) {
        getResources();
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    private void launchVerticalAnimation() {
        progressVertical.animateMaterial()
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.DownToUp)
                .toColor(MaterialColor.Green)
                .setDuration(1500)

                .withPostAnimation()
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.UpToDown)
                .toColor(MaterialColor.White)
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
                        if (animation.getAnimatedFraction() != 1.0f)
                            return;
                        launchVerticalAnimation();
                    }
                });
    }

    private void launchHorizontalAnimation() {
        progressHorizontal.animateMaterial()
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.LeftToRight)
                .toColor(MaterialColor.Orange)
                .setDuration(1500)

                .withPostAnimation()
                .setTransition(TypeOfTransition.Line)
                .setDirection(DirectionOfTransition.RightToLeft)
                .toColor(MaterialColor.White)
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
                        if (animation.getAnimatedFraction() != 1.0f)
                            return;
                        launchHorizontalAnimation();
                    }
                });
    }
}

