package com.gregbarasch.poopsnake.management;

import static com.gregbarasch.poopsnake.util.Constants.WINDOW_SIZE_IN_TILES;

public enum CollisionSystem {

    INSTANCE;

    private static int unOccupiedTiles = WINDOW_SIZE_IN_TILES * WINDOW_SIZE_IN_TILES; // TODO max limitation

    private static final boolean[][] blocked = new boolean[WINDOW_SIZE_IN_TILES][WINDOW_SIZE_IN_TILES];
    private static final boolean[][] occupied = new boolean[WINDOW_SIZE_IN_TILES][WINDOW_SIZE_IN_TILES]; // but still moveTo-able

    public void block(int x, int y) {
        occupied[x][y] = true;
        blocked[x][y] = true;
    }

    public void unblock(int x, int y) {
        occupied[x][y] = false;
        blocked[x][y] = false;
    }

    public void occupy(int x, int y) {
        occupied[x][y] = true;
        unOccupiedTiles--;
    }

    public void deOccupy(int x, int y) {
        occupied[x][y] = false;
        unOccupiedTiles++;
    }

    public boolean isBlocked(int x, int y) {
        return blocked[x][y];
    }

    public boolean isOccupied(int x, int y) {
        return occupied[x][y];
    }

    public int getTotalFreeTiles() {
        return unOccupiedTiles;
    }
}
