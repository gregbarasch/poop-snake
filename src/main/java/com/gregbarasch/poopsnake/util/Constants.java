package com.gregbarasch.poopsnake.util;

import org.newdawn.slick.TrueTypeFont;

import java.awt.Font;

import static java.awt.Font.BOLD;

public class Constants {
    public static final int TILE_SIZE_IN_PIXELS = 32;
    public static final int WINDOW_SIZE_IN_TILES = 32;
    public static final int WINDOW_SIZE_IN_PIXELS = TILE_SIZE_IN_PIXELS * WINDOW_SIZE_IN_TILES;

    public static final int RUNNING_STATE = 1;
    public static final int PAUSED_STATE = 2;
    public static final int GAME_OVER_STATE = 3;

    public static final TrueTypeFont verdanaBold32Font = new TrueTypeFont(new Font("Verdana", BOLD, 32), true);
}