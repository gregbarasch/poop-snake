package com.gregbarasch.poopsnake.model;

public class MoveResult {

    private final Tile newHead;
    private final Tile removedTail;
    private final boolean died;

    public MoveResult(Tile newHead, Tile removedTail, boolean died) {
        this.newHead = newHead;
        this.removedTail = removedTail;
        this.died = died;
    }

    public Tile getNewHead() {
        return newHead;
    }

    public Tile getRemovedTail() {
        return removedTail;
    }

    public boolean died() {
        return died;
    }
}
