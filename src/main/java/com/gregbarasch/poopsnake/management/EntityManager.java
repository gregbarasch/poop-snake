package com.gregbarasch.poopsnake.management;

import com.gregbarasch.poopsnake.model.Background;
import com.gregbarasch.poopsnake.model.Foods;
import com.gregbarasch.poopsnake.model.Snake;
import com.gregbarasch.poopsnake.model.Tile;
import com.gregbarasch.poopsnake.model.Direction;
import com.gregbarasch.poopsnake.model.MoveResult;
import org.newdawn.slick.Graphics;

public enum  EntityManager {

    INSTANCE;

    private static final Background background = new Background();
    private final Snake snake = new Snake();
    private final Foods foods = new Foods();

    private int score = 0;

    // TODO refactor to remove side effect
    EntityManager() {
        snake.spawn();
        foods.makeFood();
    }

    public void directionPressed(Direction direction) {
        snake.changeDirection(direction);
    }

    /**
     * Assumes that a move should be made
     */
    public void timeElapsed() {
        // Move
        final MoveResult moveResult = snake.move();
        final Tile newHead = moveResult.getNewHead();
        final Tile oldTail = moveResult.getRemovedTail();

        // Try and eat food
        final Tile food = foods.findFood(newHead.getX(), newHead.getY());
        if (food != null) {
            foods.eatFood(food);
            snake.grow(oldTail); // oldTail is where the snake will grow from
            score++;

            // If we ate food, make more if necessary
            if (foods.noneLeft()) {
                // You win, no more tiles left
                if (!foods.makeFood())
                    StateManager.INSTANCE.toggleWantGameOver(); // TODO you win
            }
        }

        // Maybe die
        if (moveResult.died())
            StateManager.INSTANCE.toggleWantGameOver();
    }

    public void render(Graphics g) {
        background.render(g);
        snake.render(g);
        foods.render(g);
    }

    public int getScore() {
        return score;
    }
}
