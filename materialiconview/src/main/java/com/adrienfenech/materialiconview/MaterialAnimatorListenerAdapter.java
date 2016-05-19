package com.adrienfenech.materialiconview;

import android.animation.ValueAnimator;

/**
 * Created by Adrien Fenech on 02/05/16.
 */
public abstract class MaterialAnimatorListenerAdapter {

    /**
     * <p>Notifies the start of the animation.</p>
     *
     * @param animation The started animation.
     */
    public void onAnimationStart(ValueAnimator animation) {
    }

    /**
     * <p>Notifies the end of the animation.</p>
     *
     * @param animation The animation which reached its end.
     */
    public void onAnimationEnd(ValueAnimator animation) {
    }

    /**
     * <p>Notifies the occurrence of another frame of the animation.</p>
     *
     * @param animation The animation which was repeated.
     */
    public void onAnimationUpdate(ValueAnimator animation) {
    }

    /**
     * <p>Notifies the occurrence of another frame of the animation with the current area covered.</p>
     * @param animation
     * @param areaCovered
     */
    public void onAreaCoveredUpdate(ValueAnimator animation, float areaCovered) {
    }
}
