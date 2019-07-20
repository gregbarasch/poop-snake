package com.gregbarasch.poopsnake.model;

import com.gregbarasch.poopsnake.management.CollisionSystem;
import com.gregbarasch.poopsnake.util.ResourceFactory;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import static com.gregbarasch.poopsnake.model.Direction.DOWN;
import static com.gregbarasch.poopsnake.model.Direction.LEFT;
import static com.gregbarasch.poopsnake.model.Direction.RIGHT;
import static com.gregbarasch.poopsnake.model.Direction.UP;

public class Snake {

    private final LinkedList<Tile> snakeTiles = new LinkedList<>();
    private final Queue<Direction> changeDirectionQueue = new LinkedList<>();

    private Direction currentFacingDirection;
    private Image currentHeadImage;

    private final Image headLeftImage = ResourceFactory.createImage("images/head_left.png");
    private final Image headRightImage = ResourceFactory.createImage("images/head_right.png");
    private final Image headUpImage = ResourceFactory.createImage("images/head_up.png");
    private final Image headDownImage = ResourceFactory.createImage("images/head_down.png");
    private final Image bodyImage = ResourceFactory.createImage("images/body.png");

    public boolean spawn() {
        currentFacingDirection = RIGHT;
        currentHeadImage = headRightImage;

        for (int y = 14; y >= 14; y--) {
            for (int x = 14; x >= 12; x--) {
                CollisionSystem.INSTANCE.block(x, y);
                snakeTiles.addLast(new Tile(x, y, bodyImage));
            }
        }

        return true; // TODO if cant spawn into world
    }

    public MoveResult move() {

        // Change direction if necessary
        if (!changeDirectionQueue.isEmpty())
            setCurrentFacingDirection(changeDirectionQueue.remove());

        // remove tail
        final Tile oldTail = snakeTiles.removeLast();
        final int oldTailX = oldTail.getX();
        final int oldTailY = oldTail.getY();
        CollisionSystem.INSTANCE.unblock(oldTailX, oldTailY);

        // old head becomes body
        final Tile oldHead = snakeTiles.getFirst();
        final int oldHeadX = oldHead.getX();
        final int oldHeadY = oldHead.getY();
        oldHead.setImage(bodyImage);

        // Get coordinates for new head
        Integer newX = null, newY = null;
        switch (currentFacingDirection) {
            case UP:
                newX = oldHeadX;
                newY = oldHeadY-1;
                break;

            case DOWN:
                newX = oldHeadX;
                newY = oldHeadY+1;
                break;

            case LEFT:
                newX = oldHeadX-1;
                newY = oldHeadY;
                break;

            case RIGHT:
                newX = oldHeadX+1;
                newY = oldHeadY;
                break;
        }

        // Check if blocked/death, then block space regardless
        final boolean isDead = CollisionSystem.INSTANCE.isBlocked(newX, newY);
        CollisionSystem.INSTANCE.block(newX, newY);

        // create new head
        final Tile newHead = new Tile(newX, newY, currentHeadImage);
        snakeTiles.addFirst(newHead);

        return new MoveResult(newHead, oldTail, isDead);
    }

    /**
     * Only grows from the tail
     * @param tail The previous tail
     * @return success or failure
     */
    public boolean grow(Tile tail) {
        final int x = tail.getX();
        final int y = tail.getY();

        if (CollisionSystem.INSTANCE.isBlocked(x, y)) return false;

        snakeTiles.addLast(tail);
        CollisionSystem.INSTANCE.block(x, y);
        return true;
    }

    public void changeDirection(Direction direction) {
        changeDirectionQueue.add(direction);
    }

    /**
     * @param currentFacingDirection the direction that the snake is going to move
     * @return true if successful, false if not
     */
    private boolean setCurrentFacingDirection(final Direction currentFacingDirection) {
        switch (currentFacingDirection) {
            case UP:
                if (this.currentFacingDirection == UP || this.currentFacingDirection == DOWN) return false;
                currentHeadImage = headUpImage;
                break;

            case DOWN:
                if (this.currentFacingDirection == DOWN || this.currentFacingDirection == UP) return false;
                currentHeadImage = headDownImage;
                break;

            case LEFT:
                if (this.currentFacingDirection == LEFT || this.currentFacingDirection == RIGHT) return false;
                currentHeadImage = headLeftImage;
                break;

            case RIGHT:
                if (this.currentFacingDirection == RIGHT || this.currentFacingDirection == LEFT) return false;
                currentHeadImage = headRightImage;
                break;
        }

        this.currentFacingDirection = currentFacingDirection;
        return true;
    }

    public void render(Graphics g) {
        final Iterator iterator = snakeTiles.descendingIterator();
        while(iterator.hasNext()) {
            final Tile tile = (Tile) iterator.next();
            final int size = tile.getSize();
            g.drawImage(tile.getImage(), tile.getX() * size, tile.getY() * size);
        }
    }
}