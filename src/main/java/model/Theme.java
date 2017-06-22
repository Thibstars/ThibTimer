/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package model;

/**
 * Enum containing available themes.
 *
 * @author Thibault Helsmoortel
 */
public enum Theme {
    DARK("Dark"), LIGHT("Light");

    private final String name;

    Theme(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
