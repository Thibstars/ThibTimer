package be.thibaulthelsmoortel.thibtimer.model;

import static be.thibaulthelsmoortel.thibtimer.constants.ModelConstants.TIME_DOUBLE_DIGIT_CHECK;
import static be.thibaulthelsmoortel.thibtimer.constants.ModelConstants.TIME_ZERO;

/**
 * @author Thibault Helsmoortel
 * @since 06/02/2018
 */
public class Time {

    private int hours;
    private int minutes;
    private int seconds;

    public Time() {
        reset();
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void reset() {
        this.hours = TIME_ZERO;
        this.minutes = TIME_ZERO;
        this.seconds = TIME_ZERO;
    }

    public String getHourString() {
        String hourString = Integer.toString(this.hours);
        if (this.hours < TIME_DOUBLE_DIGIT_CHECK) return "" + TIME_ZERO + hours;
        return hourString;
    }

    public String getMinuteString() {
        String minuteString = Integer.toString(this.minutes);
        if (this.minutes < TIME_DOUBLE_DIGIT_CHECK) return "" + TIME_ZERO + minutes;
        return minuteString;
    }

    public String getSecondString() {
        String secondString = Integer.toString(this.seconds);
        if (this.seconds < TIME_DOUBLE_DIGIT_CHECK) return "" + TIME_ZERO + seconds;
        return secondString;
    }
}
