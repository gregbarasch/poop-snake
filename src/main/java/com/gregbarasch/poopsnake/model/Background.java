package com.gregbarasch.poopsnake.model;

import com.gregbarasch.poopsnake.management.CollisionSystem;
import com.gregbarasch.poopsnake.util.ResourceFactory;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import static com.gregbarasch.poopsnake.util.Constants.WINDOW_SIZE_IN_TILES;

public class Background {

    private final Tile[][] grid = new Tile[WINDOW_SIZE_IN_TILES][WINDOW_SIZE_IN_TILES];

    public Background() {
        final Image blackImage = ResourceFactory.createImage("images/black.png");
        final Image bbBlueImage = ResourceFactory.createImage("images/bb_blue.png");

        // Create background tiles
        for (int x = 0; x < WINDOW_SIZE_IN_TILES; x++) {
            for (int y = 0; y < WINDOW_SIZE_IN_TILES; y++) {

                if (isWall(x, y)) {
                    grid[x][y] = new Tile(x, y, blackImage);
                    CollisionSystem.INSTANCE.block(x, y);
                } else {
                    grid[x][y] = new Tile(x, y, bbBlueImage);
                    CollisionSystem.INSTANCE.unblock(x, y);
                }
            }
        }
    }

    // TODO move render from com.gregbarasch.poopsnake.model?? Should com.gregbarasch.poopsnake.model be able to render itself?
    public void render(Graphics g) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                final Tile tile = grid[x][y];
                g.drawImage(tile.getImage(), x * tile.getSize(), y * tile.getSize());
            }
        }
    }

    private boolean isWall(int x, int y) {
        return x == 0 || x == grid.length - 1 || y == 0 || y == grid.length - 1;
    }
}