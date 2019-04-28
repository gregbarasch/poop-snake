package com.gregbarasch.poopsnake.util;

import com.gregbarasch.poopsnake.management.StateManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.io.IOException;
import java.io.InputStream;

public class ResourceFactory {

    public static Image createImage(String ref) {
        try (final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(ref)) {
            return new Image(in, ref, false);
        } catch (IOException | SlickException ex) {
            ex.printStackTrace();
            StateManager.INSTANCE.panic();
        }
        return null; // should never hit
    }

    public static Music createMusic(String ref) {
        try (final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(ref)) {
             return new Music(in, ref);
        } catch (IOException | SlickException ex) {
            ex.printStackTrace();
            StateManager.INSTANCE.panic();
        }
        return null; // should never hit
    }

    public static Sound createSound(String ref) {
        try (final InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(ref)) {
            return new Sound(in, ref);
        } catch (IOException | SlickException ex) {
            ex.printStackTrace();
            StateManager.INSTANCE.panic();
        }
        return null; // should never hit
    }
}
