package be.thibaulthelsmoortel.thibtimer.model;

import be.thibaulthelsmoortel.thibtimer.constants.ModelConstants;

/**
 * Interface providing timer means for {@link TimeChanger}.
 *
 * @author Thibault Helsmoortel
 */
public interface TimerState extends TimeChanger {

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
            if (hours >= ModelConstants.TIME_NEXT_VALUE_HOURS_AMOUNT) {
                hours = ModelConstants.TIME_ZERO;
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
