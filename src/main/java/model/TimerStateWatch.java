/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package model;

/**
 * Watch timer state.
 *
 * @author Thibault Helsmoortel
 */
public class TimerStateWatch implements TimerState {

    @Override
    public void changeTime() {
        incrementTime();
    }
}
