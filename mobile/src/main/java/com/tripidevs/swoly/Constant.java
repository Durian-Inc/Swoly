package com.tripidevs.swoly;

/*
 * Created by maver on 12/20/2016.
 * Constants for mobile
 */

public class Constant {
    public static int max = 135;
    public static float percent = .55f;
    public  static int barWeight = 45;

    public static float weightOnEachSide()
    {
        return ((max*percent)-barWeight)/2;
    }

    public static float weightTotal()
    {
        return (max*percent);
    }

    public static float totalMinusBar()
    {
        return (max*percent)-barWeight;
    }
}