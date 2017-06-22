/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package model;

import constants.ModelConstants;

/**
 * Timer timer state.
 *
 * @author Thibault Helsmoortel
 */

public class TimerStateTimer implements Timeable {

    @Override
    public void changeTime() {
        //Decrement time
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
