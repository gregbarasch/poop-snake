package com.gregbarasch.poopsnake.model;

import com.gregbarasch.poopsnake.management.CollisionSystem;
import com.gregbarasch.poopsnake.util.ResourceFactory;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.HashSet;
import java.util.Random;

import static com.gregbarasch.poopsnake.util.Constants.WINDOW_SIZE_IN_TILES;

public class Foods {

    private final HashSet<Tile> foods = new HashSet<>();
    private final Image foodImage = ResourceFactory.createImage("images/food.png");

    public Tile findFood(int x, int y) {
        for (final Tile food : foods) {
            if (food.intersects(x, y))
                return food;
        }
        return null;
    }

    /**
     * @param food The food tile that we are going to attempt to eat
     * @return true if successful false if not
     */
    public boolean eatFood(Tile food) {
        CollisionSystem.INSTANCE.deOccupy(food.getX(), food.getY());
        return foods.remove(food);
    }

    /**
     * Generate between 1 and 2 foods
     * @return Whether or not food can be made
     */
    public boolean makeFood() {
        final Random random = new Random();
        final int numFoods = random.nextInt(2) + 1; // 1 or 2 foods

        for (int i = 1; i <= numFoods; i++) {
            if (CollisionSystem.INSTANCE.getFreeTiles() == 0) return false;

            int x, y;
            do {
                x = random.nextInt(WINDOW_SIZE_IN_TILES);
                y = random.nextInt(WINDOW_SIZE_IN_TILES);
            } while (CollisionSystem.INSTANCE.isOccupied(x, y));

            foods.add(new Tile(x, y, foodImage));
            CollisionSystem.INSTANCE.occupy(x, y);
        }

        return true;
    }

    public boolean noneLeft() {
        return foods.isEmpty();
    }

    public void render(Graphics g) {
        for (final Tile food : foods) {
            final int size = food.getSize();
            g.drawImage(food.getImage(), food.getX() * size, food.getY() * size);
        }
    }
}
