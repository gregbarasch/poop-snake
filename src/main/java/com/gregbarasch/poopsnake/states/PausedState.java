package com.gregbarasch.poopsnake.states;

import com.gregbarasch.poopsnake.management.StateManager;
import com.gregbarasch.poopsnake.util.DelayedTask;
import com.gregbarasch.poopsnake.util.ResourceFactory;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import static com.gregbarasch.poopsnake.util.Constants.PAUSED_STATE;
import static com.gregbarasch.poopsnake.util.Constants.RUNNING_STATE;
import static org.newdawn.slick.Input.KEY_A;
import static org.newdawn.slick.Input.KEY_D;
import static org.newdawn.slick.Input.KEY_DOWN;
import static org.newdawn.slick.Input.KEY_ENTER;
import static org.newdawn.slick.Input.KEY_ESCAPE;
import static org.newdawn.slick.Input.KEY_LEFT;
import static org.newdawn.slick.Input.KEY_P;
import static org.newdawn.slick.Input.KEY_PAUSE;
import static org.newdawn.slick.Input.KEY_RIGHT;
import static org.newdawn.slick.Input.KEY_S;
import static org.newdawn.slick.Input.KEY_SPACE;
import static org.newdawn.slick.Input.KEY_UP;
import static org.newdawn.slick.Input.KEY_W;

public class PausedState extends BasicGameState {

    private final Image pauseScreenImage = ResourceFactory.createImage("images/pause_screen.jpg");
    private final Sound pauseMusicSound = ResourceFactory.createSound("sounds/pause_music.ogg");
    private final Sound pauseJingleSound = ResourceFactory.createSound("sounds/pause_sound.ogg");

    private DelayedTask delayedPauseMusic;

    @Override
    public void init(GameContainer container, StateBasedGame stateBasedGame) {}

    @Override
    public void enter(GameContainer container, StateBasedGame stateBasedGame) {
        pauseJingleSound.play();
        delayedPauseMusic = new DelayedTask(
                () -> {
                    pauseMusicSound.loop();
                    return null;
                }, 2
        );
    }

    @Override
    public void leave(GameContainer container, StateBasedGame stateBasedGame) {
        if (delayedPauseMusic != null)
            delayedPauseMusic.kill();

        if (pauseMusicSound.playing())
            pauseMusicSound.stop();
    }

    @Override
    public void update(GameContainer container, StateBasedGame stateBasedGame, int delta) {
        if (!StateManager.INSTANCE.wantsPause())
            stateBasedGame.enterState(RUNNING_STATE);
    }

    @Override
    public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics g) {
        g.drawImage(pauseScreenImage, 0, 0);
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case KEY_W:
            case KEY_UP:
            case KEY_A:
            case KEY_LEFT:
            case KEY_S:
            case KEY_DOWN:
            case KEY_D:
            case KEY_RIGHT:
            case KEY_ESCAPE:
            case KEY_P:
            case KEY_PAUSE:
            case KEY_SPACE:
            case KEY_ENTER:
                StateManager.INSTANCE.toggleWantPause();
        }
    }

    @Override
    public int getID() {
        return PAUSED_STATE;
    }
}