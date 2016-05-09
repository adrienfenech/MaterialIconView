package com.adrienfenech.materialiconview;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Adrien Fenech on 02/05/16.
 */
public class MaterialColor {

    public static final int Red =           Color.parseColor("#F44336");
    public static final int Pink =          Color.parseColor("#E91E63");
    public static final int Purple =        Color.parseColor("#9C27B0");
    public static final int DeepPurple =    Color.parseColor("#673AB7");
    public static final int Indigo =        Color.parseColor("#3F51B5");
    public static final int Blue =          Color.parseColor("#2196F3");
    public static final int LightBlue =     Color.parseColor("#03A9F4");
    public static final int Cyan =          Color.parseColor("#00BCD4");
    public static final int Teal =          Color.parseColor("#009688");
    public static final int Green =         Color.parseColor("#4CAF50");
    public static final int LightGreen =    Color.parseColor("#8BC34A");
    public static final int Lime =          Color.parseColor("#CDDC39");
    public static final int Yellow =        Color.parseColor("#FFEB3B");
    public static final int Amber =         Color.parseColor("#FFC107");
    public static final int Orange =        Color.parseColor("#FF9800");
    public static final int DeepOrange =    Color.parseColor("#FF5722");
    public static final int Brown =         Color.parseColor("#795548");
    public static final int Grey =          Color.parseColor("#9E9E9E");
    public static final int BlueGrey =      Color.parseColor("#607D8B");
    public static final int Black =         Color.parseColor("#000000");
    public static final int White =         Color.parseColor("#FFFFFF");


    /**
     * Get a set of Material color using a specific color as base, from {@link MaterialColor}
     * static field. You can specify the variations number of color as second parameter.
     * @param baseColor The base color
     * @param colorCount Number of variation
     * @return The color set
     */
    public static ArrayList<Integer> getMaterialColorSet(int baseColor, int colorCount) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        float [] baseColorHSL = colorToHsl(baseColor);

        float lght = 0.25f;// initial lightness value (experimental)
        float lStep = (0.85f - lght) / colorCount; // step to go up to 0.6 lightness (experimental)
        for (int i = 0; i < colorCount; i++) {
            baseColor = hslToColor(255 ,baseColorHSL[0] , baseColorHSL[1] , lght);
            resultList.add(baseColor);
            lght += lStep;
        }
        resultList.add(hslToColor(255, baseColorHSL[0], baseColorHSL[1], 0.95f));
        Collections.reverse(resultList);

        return resultList;
    }

    /**
     * Get a specific color by index. This method use {getMaterialColorSet} with 9 as
     * variation number. The indice must be in range 0 - 9, or use index
     * from Material Design Color from Google GuideLines.
     * @param baseColor The base color
     * @param index The index used
     * @return The specific color
     */
    public static int getMaterialColorByIndice(int baseColor, int index) {
        ArrayList<Integer> colors = getMaterialColorSet(baseColor, 9);
        if (index >= 50) {
            if (index == 50)
                index = 0;
            else
                index /= 100;
        }

        return colors.get(index);
    }

    private static float[]  colorToHsl(int hexColor) {
        int color = hexColor;
        float r = ((0x00ff0000 & color) >> 16) / 255.0F;
        float g = ((0x0000ff00 & color) >> 8) / 255.0F;
        float b = ((0x000000ff & color)) / 255.0F;
        float max = Math.max(Math.max(r, g), b);
        float min = Math.min(Math.min(r, g), b);
        float c = max - min;

        float hTemp = 0.0F;
        if (c == 0) {
            hTemp = 0;
        } else if (max == r) {
            hTemp = (float) (g - b) / c;
            if (hTemp < 0)
                hTemp += 6.0F;
        } else if (max == g) {
            hTemp = (float) (b - r) / c + 2.0F;
        } else if (max == b) {
            hTemp = (float) (r - g) / c + 4.0F;
        }
        float h = 60.0F * hTemp;

        float l = (max + min) * 0.5F;

        float s;
        if (c == 0) {
            s = 0.0F;
        } else {
            s = c / (1 - Math.abs(2.0F * l - 1.0F));
        }

        float []  hsl  = {h , s , l } ;
        return hsl;
    }

    private static int hslToColor(int alpha, float hue, float saturation, float lightness) {
        float hh = hue;
        float ss = saturation;
        float ll = lightness;
        float h, s, v;
        h = hh;
        ll *= 2;
        ss *= (ll <= 1) ? ll : 2 - ll;
        v = (ll + ss) / 2;
        s = ((ll + ss) != 0) ? (2 * ss) / (ll + ss) : 0;
        return Color.HSVToColor(alpha, new float[] { h, s, v });
    }
}
