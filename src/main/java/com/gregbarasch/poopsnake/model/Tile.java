package com.gregbarasch.poopsnake.model;

import org.newdawn.slick.Image;

import static com.gregbarasch.poopsnake.util.Constants.TILE_SIZE_IN_PIXELS;

public class Tile {

    private final int x; //
    private final int y; // tile coordinates
    private Image image;

    @SuppressWarnings("WeakerAccess")
    public Tile(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    /**
     * @return size in pixels
     */
    @SuppressWarnings("WeakerAccess")
    public int getSize() {
        return TILE_SIZE_IN_PIXELS;
    }

    @SuppressWarnings("WeakerAccess")
    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @SuppressWarnings("WeakerAccess")
    public void setImage(Image image) {
        this.image = image;
    }

    @SuppressWarnings("WeakerAccess")
    public boolean intersects(int x, int y) {
        return x == this.x && y == this.y;
    }

    @SuppressWarnings("unused")
    public boolean intersects(Tile tile) {
        return intersects(tile.getX(), tile.getY());
    }
}
