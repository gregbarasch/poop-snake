package com.gregbarasch.poopsnake.states;

import com.gregbarasch.poopsnake.management.EntityManager;
import com.gregbarasch.poopsnake.management.StateManager;
import com.gregbarasch.poopsnake.util.ResourceFactory;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import static java.lang.Thread.sleep;
import static com.gregbarasch.poopsnake.util.Constants.GAME_OVER_STATE;
import static com.gregbarasch.poopsnake.util.Constants.verdanaBold32Font;
import static com.gregbarasch.poopsnake.util.Constants.WINDOW_SIZE_IN_PIXELS;
import static org.newdawn.slick.Input.KEY_ENTER;
import static org.newdawn.slick.Input.KEY_ESCAPE;
import static org.newdawn.slick.Input.KEY_Q;
import static org.newdawn.slick.Input.KEY_SPACE;

public class GameOverState extends BasicGameState {

    private final Image gameOverScreenImage = ResourceFactory.createImage("images/game_over_screen.jpg");
    private final Sound deathSound = ResourceFactory.createSound("sounds/death_sound.ogg");

    private String scoreString;
    private float scoreStringX;
    private float scoreStringY;

    @Override
    public void init(GameContainer container, StateBasedGame game) {}

    @Override
    public void enter(GameContainer container, StateBasedGame stateBasedGame) {
        scoreString = "SCORE: " + EntityManager.INSTANCE.getScore();
        scoreStringX = WINDOW_SIZE_IN_PIXELS/2F - verdanaBold32Font.getWidth(scoreString)/2F;
        scoreStringY = WINDOW_SIZE_IN_PIXELS/4F * 3;

        deathSound.play();

        // Force sleep before rendering screen
        try { sleep(2000L); } catch (InterruptedException ex) { ex.printStackTrace(); }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) {}

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) {
        g.drawImage(gameOverScreenImage, 0, 0);
        verdanaBold32Font.drawString(scoreStringX, scoreStringY, scoreString);
    }

    @Override
    public void keyPressed(int key, char c) {
        switch (key) {
            case KEY_ESCAPE:
            case KEY_SPACE:
            case KEY_ENTER:
            case KEY_Q:
                StateManager.INSTANCE.quit();
        }
    }

    @Override
    public int getID() {
        return GAME_OVER_STATE;
    }
}
