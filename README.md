# MaterialIconView

A library created to animate canvas of material design icon. As for a any `View` with `animate()`method, you can use here `a,imateMaterial()` of `MaterialIconView`.
This library provide also a `MaterialColor` class which can be used to create dynamically Material Color, and use it.
## How to include it ?

Simply add

```groovy
compile 'com.adrienfenech:material-icon-view:0.1.0'
```

## How to use it ?

Firtly, you need to add an image to the `MaterialIconView`. You can use `src` parameter in xml, or `setMaterialImageBitmap` programmatically.

To animate it, you only need to call `mMaterialIconView.animateMaterial()` as `mView.animate()` in basic view.

## Example

Example 1:
```Java
mMaterialIconView.animateMaterial().toColor(MaterialColor.Blue); // Apply a default transition from current color to Blue.
```

Example 2:
```Java
mMaterialIconView.animateMaterial()
        .setDuration(1500) // Set animation's duration to 1500ms
        .typeOfTransition(TypeOfTransition.Circle) // Set transition's type to a Circle
        .setInterpolator(new AccelerateDecelerateInterpolator()) // Use AccelerateDecelerate interpolator
        .toColor(MaterialColor.Green); // Apply a transition from current color to Green
```

Example 3:
```Java
mMaterialIconView.animateMaterial()
        .endingArea(0.7) // Will cover 70% of the surface (default 1 or 100%)
        .startingArea(0.2) // Start covering from 20% of the surface (default 0 or 0%)
        .typeOfTransition(TypeOfTransition.Line) // Set transition's type to a Line
        .directionOfTransition(DirectionOfTransition.LeftToRight) // Set transition's direction from Left to Right
        .toColor(MaterialColor.Orange); // Apply a transition from current color to Orange
```

## Advanced features

As basic `ViewPropertyAnimator`, you can add listener to material animation, with callback to start, update and end of the animation.
With MaterialIconView, you can chain or supperpose animation. Instead of use start or end callback to perform several animation, you can use 2 specifics methods:

```Java
withConcurrentAnimation() // Will return a new MaterialPropertyAnimator which can be customized and will be executed when the current annimation will start.
withPostAnimation() // Will return a new MaterialPropertyAnimator which can be customized and will be executed when the current annimation will finish.
```

The two next method will perform the same animation.

```Java
private void launchNewAnimation1(final MaterialIconView view) {
        view.animateMaterial()
                .toColor(MaterialColor.Teal)
                .directionOfTransition(DirectionOfTransition.UpRightToDownLeft)
                .setListener(new MaterialAnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(ValueAnimator animation) {
                        super.onAnimationEnd(animation);
                        view.animateMaterial()
                                .toColor(MaterialColor.Cyan)
                                .directionOfTransition(DirectionOfTransition.RightToLeft)
                                .endingArea(0.5f)
                                .setListener(new MaterialAnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(ValueAnimator animation) {
                                        super.onAnimationEnd(animation);
                                        view.animateMaterial()
                                                .toColor(MaterialColor.Blue)
                                                .directionOfTransition(DirectionOfTransition.DownRightToUpLeft)
                                                .setInterpolator(new AccelerateDecelerateInterpolator())
                                                .endingArea(0.4f);
                                    }
                                });
                    }
                });
    }
    
    private void launchNewAnimation2(final MaterialIconView view) {
        view.animateMaterial()
                .toColor(MaterialColor.getMaterialColorByIndice(MaterialColor.Teal, 500))
                .directionOfTransition(DirectionOfTransition.UpRightToDownLeft)

             /** New Concurrent Animation **/
                .withConcurrentAnimation()
                .toColor(MaterialColor.Cyan)
                .directionOfTransition(DirectionOfTransition.RightToLeft)
                .setStartDelay(MaterialPropertyAnimator.DEFAULT_ANIMATION_DURATION)
                .endingArea(0.5f)

             /** New Post Animation **/
                .withPostAnimation()
                .toColor(MaterialColor.Blue)
                .directionOfTransition(DirectionOfTransition.DownRightToUpLeft)
                .endingArea(0.4f);
    }
```

`MaterialPropertyAnimator` provides also two methods which can be usefull if you want to use basic view's animation (as Rotation, scale, alpha, ...):

```Java

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
MaterialPropertyAnimator withDependentAnimationView(ViewAnimation viewAnimation)

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
MaterialPropertyAnimator withIndependentAnimationView(ViewAnimation viewAnimation)
```
