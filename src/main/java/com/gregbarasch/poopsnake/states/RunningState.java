package com.gregbarasch.poopsnake.states;

import com.gregbarasch.poopsnake.management.EntityManager;
import com.gregbarasch.poopsnake.management.StateManager;
import com.gregbarasch.poopsnake.util.ResourceFactory;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import static java.lang.Thread.sleep;
import static com.gregbarasch.poopsnake.model.Direction.DOWN;
import static com.gregbarasch.poopsnake.model.Direction.LEFT;
import static com.gregbarasch.poopsnake.model.Direction.RIGHT;
import static com.gregbarasch.poopsnake.model.Direction.UP;
import static com.gregbarasch.poopsnake.util.Constants.GAME_OVER_STATE;
import static com.gregbarasch.poopsnake.util.Constants.PAUSED_STATE;
import static com.gregbarasch.poopsnake.util.Constants.RUNNING_STATE;
import static org.newdawn.slick.Input.KEY_A;
import static org.newdawn.slick.Input.KEY_D;
import static org.newdawn.slick.Input.KEY_DOWN;
import static org.newdawn.slick.Input.KEY_ESCAPE;
import static org.newdawn.slick.Input.KEY_LEFT;
import static org.newdawn.slick.Input.KEY_P;
import static org.newdawn.slick.Input.KEY_PAUSE;
import static org.newdawn.slick.Input.KEY_Q;
import static org.newdawn.slick.Input.KEY_RIGHT;
import static org.newdawn.slick.Input.KEY_S;
import static org.newdawn.slick.Input.KEY_UP;
import static org.newdawn.slick.Input.KEY_W;

public class RunningState extends BasicGameState {

    private final Music mainMusic = ResourceFactory.createMusic("sounds/Komiku_-_09_-_Glouglou.ogg");
    private boolean mainMusicPaused = false;

    @Override
    public void init(GameContainer container, StateBasedGame stateBasedGame) {}

    @Override
    public void enter(GameContainer container, StateBasedGame stateBasedGame) {
        if (mainMusicPaused)
            mainMusic.resume();
        else
            mainMusic.loop();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame stateBasedGame) {
        mainMusic.pause();
        mainMusicPaused = true;
    }

    @Override
    public void update(GameContainer container, StateBasedGame stateBasedGame, int delta) {
        if (StateManager.INSTANCE.wantsPause())
            stateBasedGame.enterState(PAUSED_STATE);
        else if (StateManager.INSTANCE.wantsGameOver())
            stateBasedGame.enterState(GAME_OVER_STATE);
        else
            EntityManager.INSTANCE.timeElapsed();

        try { sleep(90L); } catch (InterruptedException ex) { ex.printStackTrace(); }
    }

    @Override
    public void render(GameContainer container, StateBasedGame stateBasedGame, Graphics g) {
        EntityManager.INSTANCE.render(g);
    }


    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case KEY_W:
            case KEY_UP:
                EntityManager.INSTANCE.directionPressed(UP);
                break;

            case KEY_A:
            case KEY_LEFT:
                EntityManager.INSTANCE.directionPressed(LEFT);
                break;

            case KEY_S:
            case KEY_DOWN:
                EntityManager.INSTANCE.directionPressed(DOWN);
                break;

            case KEY_D:
            case KEY_RIGHT:
                EntityManager.INSTANCE.directionPressed(RIGHT);
                break;

            case KEY_P:
            case KEY_PAUSE:
                StateManager.INSTANCE.toggleWantPause();
                break;

            case KEY_Q:
            case KEY_ESCAPE:
                StateManager.INSTANCE.quit();
        }
    }

    @Override
    public int getID() {
        return RUNNING_STATE;
    }
}

