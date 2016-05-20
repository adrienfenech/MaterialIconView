package com.adrienfenech.Sample;

import android.graphics.Point;

import com.adrienfenech.materialiconview.DirectionOfTransition;
import com.adrienfenech.materialiconview.MaterialColor;
import com.adrienfenech.materialiconview.MaterialIconView;
import com.adrienfenech.materialiconview.TypeOfTransition;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by octo on 20/05/16.
 */
public class MaterialHelper {
    private final static Random rnd;
    private static int lastColorGenerated;

    static {
        rnd = new Random();
        lastColorGenerated = MaterialColor.White;
    }

    public static Point getRandomOrigin(MaterialIconView view) {
        return new Point(rnd.nextInt(view.getBitmapWidth()), rnd.nextInt(view.getBitmapHeight()));
    }

    public static TypeOfTransition getRandomTypeOfTransition() {
        List<TypeOfTransition> typeOfTransitionList = Arrays.asList(TypeOfTransition.values());
        return typeOfTransitionList.get(rnd.nextInt(typeOfTransitionList.size()));
    }

    public static DirectionOfTransition getRandomDirectOfTransition() {
        List<DirectionOfTransition> directionOfTransitionList = Arrays.asList(DirectionOfTransition.values());
        return directionOfTransitionList.get(rnd.nextInt(directionOfTransitionList.size()));
    }

    public static int getRandomMaterialColor() {
        int color = lastColorGenerated;
        while (color == lastColorGenerated) {
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
        }
        lastColorGenerated = color;
        return MaterialColor.getMaterialColorByIndice(color, 500);
    }
}
