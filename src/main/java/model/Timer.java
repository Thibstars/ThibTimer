/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package model;

import constants.ModelConstants;
import constants.StringConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Observable;

/**
 * A singleton Timer model class that is Observable.
 *
 * @author Thibault Helsmoortel
 */

public class Timer extends Observable {

    private static final Logger LOGGER = LogManager.getLogger(Timer.class);

    private static Timer thibTimer = new Timer();
    private Timeable timeable = new TimerStateWatch();

    private DateFormat hourFormat = ModelConstants.HOUR_FORMAT;
    private DateFormat minuteFormat = ModelConstants.MINUTE_FORMAT;
    private DateFormat secondFormat = ModelConstants.SECOND_FORMAT;

    private javax.swing.Timer swingTimer;
    private int seconds;
    private int minutes;
    private int hours;

    /**
     * Constructor not publicly available, this is a singleton class
     * Creates a timer and initialises it to start from the current system's time
     */
    private Timer() {
        this.seconds = ModelConstants.TIME_ZERO;
        this.minutes = ModelConstants.TIME_ZERO;
        this.hours = ModelConstants.TIME_ZERO;
        initialiseTimer();
        setTimerToCurrentTime();
    }

    /**
     * @return thibTimer, this is the instance that manages all the time features
     */
    public static synchronized Timer getInstance() {
        return thibTimer;
    }

    /**
     * This method sets the timer values to the current (system) time
     * Note: the timer might have around a one second delay to the real system's time
     * This is because the timer is first set to the current time, and afterwards initialised
     */
    public void setTimerToCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        hours = Integer.parseInt(hourFormat.format(calendar.getTime()));
        minutes = Integer.parseInt(minuteFormat.format(calendar.getTime()));
        seconds = Integer.parseInt(secondFormat.format(calendar.getTime()));
        LOGGER.debug(String.format("Set the current time to: %02d:%02d:%02d", hours, minutes, seconds));
        setChanged();
        notifyObservers();
    }

    /**
     * This method initialises the timer and fires the increment() or decrement() depending on the situation.
     */
    private void initialiseTimer() {
        swingTimer = new javax.swing.Timer(ModelConstants.SECONDS_IN_MILLIS, e -> changeTime());
        swingTimer.start();
    }

    private void changeTime() {
        timeable.changeTime();
        setChanged();
        notifyObservers();
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
        setChanged();
        notifyObservers();
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
        setChanged();
        notifyObservers();
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
        setChanged();
        notifyObservers();
    }

    private String getHourString() {
        String hourString = Integer.toString(this.hours);
        if (this.hours < ModelConstants.TIME_DOUBLE_DIGIT_CHECK) return "" + ModelConstants.TIME_ZERO + hours;
        return hourString;
    }

    private String getMinuteString() {
        String minuteString = Integer.toString(this.minutes);
        if (this.minutes < ModelConstants.TIME_DOUBLE_DIGIT_CHECK) return "" + ModelConstants.TIME_ZERO + minutes;
        return minuteString;
    }

    private String getSecondString() {
        String secondString = Integer.toString(this.seconds);
        if (this.seconds < ModelConstants.TIME_DOUBLE_DIGIT_CHECK) return "" + ModelConstants.TIME_ZERO + seconds;
        return secondString;
    }

    /**
     * This method stops the timer and sets the desired values for seconds, minutes and hours
     *
     * @param hourVal   The value to set hours to
     * @param minuteVal The value to set minutes to
     * @param secondVal The value to set seconds to
     */
    public void setTimerTime(int hourVal, int minuteVal, int secondVal) {
        hours = hourVal;
        minutes = minuteVal;
        seconds = secondVal;
        setChanged();
        notifyObservers();
    }

    /**
     * This method stops the timer and sets seconds, minutes and hours to 0
     */
    public void reset() {
        swingTimer.stop();
        hours = ModelConstants.TIME_ZERO;
        minutes = ModelConstants.TIME_ZERO;
        seconds = ModelConstants.TIME_ZERO;
        setChanged();
        notifyObservers();
    }

    /**
     * This method starts the timer
     */
    public void start() {
        swingTimer.start();
    }

    /**
     * This method stops the timer
     */
    public void stop() {
        swingTimer.stop();
    }

    public Timeable getTimeable() {
        return timeable;
    }

    public void setTimeable(Timeable timeable) {
        this.timeable = timeable;
    }

    @Override
    public String toString() {
        return getHourString() + StringConstants.TIMER_EXTENDED_DELIMITER + getMinuteString() + StringConstants.TIMER_EXTENDED_DELIMITER + getSecondString();
    }

    /**
     * Block the ability to clone the Timer object
     *
     * @return Object This method will never return an Object
     * @throws CloneNotSupportedException when attempting to clone the timer
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

}