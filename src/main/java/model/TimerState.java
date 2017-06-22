/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package model;

import constants.ModelConstants;

/**
 * Interface providing timer means for {@link Timeable}.
 *
 * @author Thibault Helsmoortel
 */
public interface TimerState extends Timeable {

    default void incrementTime() {
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

    default void decrementTime() {
        Timer timer = Timer.getInstance();
        int hours = timer.getHours();
        int minutes = timer.getMinutes();
        int seconds = timer.getSeconds();
        if (hours > ModelConstants.TIME_ZERO && minutes == ModelConstants.TIME_ZERO && seconds == ModelConstants.TIME_ZERO) {
            hours--;
            minutes = ModelConstants.TIME_MAX;
            seconds = ModelConstants.TIME_MAX;
        } else if (minutes > ModelConstants.TIME_ZERO && seconds == ModelConstants.TIME_ZERO) {
            minutes--;
            seconds = ModelConstants.TIME_MAX;
        } else if (seconds > ModelConstants.TIME_ZERO) seconds--;
        else if (seconds <= ModelConstants.TIME_ZERO) timer.stop();

        timer.setTimerTime(hours, minutes, seconds);
    }
}
