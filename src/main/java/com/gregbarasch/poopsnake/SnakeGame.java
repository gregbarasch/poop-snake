package com.gregbarasch.poopsnake;

import com.gregbarasch.poopsnake.states.GameOverState;
import com.gregbarasch.poopsnake.states.PausedState;
import com.gregbarasch.poopsnake.states.RunningState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;

import static com.gregbarasch.poopsnake.util.Constants.WINDOW_SIZE_IN_PIXELS;

public class SnakeGame extends StateBasedGame {

    private SnakeGame() {
        super("!!! Poop Snake !!!");
    }

    @Override
    public void initStatesList(GameContainer container) {
        addState(new RunningState());
        addState(new PausedState());
        addState(new GameOverState());
    }

    public static void main(String[] args) {

        // natives resources are managed locally
        final String nativesAbsolutePath = new File("target/natives").getAbsolutePath();
        System.setProperty("org.lwjgl.librarypath", nativesAbsolutePath);
        System.setProperty("net.java.games.input.librarypath", nativesAbsolutePath);

        try {
            final AppGameContainer appGameContainer = new AppGameContainer(new SnakeGame(), WINDOW_SIZE_IN_PIXELS, WINDOW_SIZE_IN_PIXELS, false);
            appGameContainer.setShowFPS(false);
            appGameContainer.start();
        } catch (SlickException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}