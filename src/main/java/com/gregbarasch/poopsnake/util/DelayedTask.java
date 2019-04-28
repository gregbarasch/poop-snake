package com.gregbarasch.poopsnake.util;

import com.gregbarasch.poopsnake.management.StateManager;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

public class DelayedTask {

    private final Timer timer = new Timer();

    public DelayedTask(Callable<?> function, int seconds) {
        timer.schedule(new Task(function), seconds*1000L);
    }

    public void kill() {
        timer.cancel();
    }

    private class Task extends TimerTask {

        final Callable<?> function;

        Task(Callable<?> function) {
            this.function = function;
        }

        @Override
        public void run() {
            try {
                function.call();
            } catch (Exception ex) {
                ex.printStackTrace();
                StateManager.INSTANCE.panic();
            }
            timer.cancel();
        }
    }
}
