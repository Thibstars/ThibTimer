/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package model;

/**
 * Chrono timer state.
 *
 * @author Thibault Helsmoortel
 */
public class TimerStateChrono implements TimerState {

    @Override
    public void changeTime() {
        incrementTime();
    }
}
