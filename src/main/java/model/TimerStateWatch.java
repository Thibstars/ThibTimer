/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package model;

import constants.ModelConstants;

/**
 * Watch timer state.
 *
 * @author Thibault Helsmoortel
 */

public class TimerStateWatch implements Timeable {

    @Override
    public void changeTime() {
        //Increment time
        Timer timer = Timer.getInstance();
        int hours = timer.getHours();
        int minutes = timer.getMinutes();
        int seconds = timer.getSeconds();
        seconds++;
        if (seconds >= ModelConstants.TIME_NEXT_VALUE_AMOUNT) {
            seconds = ModelConstants.TIME_ZERO;
            minutes++;
            if (minutes >= ModelConstants.TIME_NEXT_VALUE_AMOUNT) {
                minutes = ModelConstants.TIME_ZERO;
                hours++;
            }
        }
        timer.setTimerTime(hours, minutes, seconds);
    }
}
