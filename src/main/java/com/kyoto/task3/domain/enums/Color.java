package com.kyoto.task3.domain.enums;

import java.util.Random;

public enum Color {
    RED,
    GREEN,
    YELLOW,
    BLUE,
    GRAY;


    private static final Random PRNG = new Random();

    public static Color randomColor()  {
        Color[] colors = values();
        return colors[PRNG.nextInt(colors.length)];
    }
}
