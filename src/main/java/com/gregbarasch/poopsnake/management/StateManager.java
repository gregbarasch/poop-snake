package com.gregbarasch.poopsnake.management;

import org.lwjgl.openal.AL;

import java.util.concurrent.atomic.AtomicBoolean;

public enum StateManager {

    INSTANCE;

    private static final AtomicBoolean wantPause = new AtomicBoolean(false);
    private static final AtomicBoolean wantGameOver = new AtomicBoolean(false);

    /**
     * @return whether the game should be in a paused state or not. Doesnt actually make the transition tho
     */
    public boolean wantsPause() {
        return wantPause.get();
    }


    public void toggleWantPause() {
        wantPause.set(!wantPause.get());
    }

    /**
     * @return whether the game should be in a game over state or not. Doesnt actually make the transition tho
     */
    public boolean wantsGameOver() {
        return wantGameOver.get();
    }

    @SuppressWarnings("WeakerAccess")
    public void toggleWantGameOver() {
        wantGameOver.set(!wantGameOver.get());
    }

    public void quit() {
        AL.destroy();
        System.exit(0);
    }

    public void panic() {
        AL.destroy();
        System.exit(1);
    }
}
