package com.gregbarasch.poopsnake.management;

import static com.gregbarasch.poopsnake.util.Constants.WINDOW_SIZE_IN_TILES;

public enum CollisionSystem {

    INSTANCE;

    private static int freeTiles = WINDOW_SIZE_IN_TILES * WINDOW_SIZE_IN_TILES; // TODO max limitation

    private static final boolean[][] blocked = new boolean[WINDOW_SIZE_IN_TILES][WINDOW_SIZE_IN_TILES];
    private static final boolean[][] occupied = new boolean[WINDOW_SIZE_IN_TILES][WINDOW_SIZE_IN_TILES]; // but still moveTo-able

    public void block(int x, int y) {
        occupied[x][y] = true;
        blocked[x][y] = true;
        freeTiles--;
    }

    public void unblock(int x, int y) {
        occupied[x][y] = false;
        blocked[x][y] = false;
        freeTiles++;
    }

    public void occupy(int x, int y) {
        occupied[x][y] = true;
        freeTiles--;
    }

    public void deOccupy(int x, int y) {
        occupied[x][y] = false;
        freeTiles++;
    }

    public boolean isBlocked(int x, int y) {
        return blocked[x][y];
    }

    public boolean isOccupied(int x, int y) {
        return occupied[x][y];
    }

    public int getFreeTiles() {
        return freeTiles;
    }
}
