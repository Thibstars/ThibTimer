/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package model;

import static constants.ModelConstants.HOUR_FORMAT;
import static constants.ModelConstants.MINUTE_FORMAT;
import static constants.ModelConstants.SECONDS_IN_MILLIS;
import static constants.ModelConstants.SECOND_FORMAT;
import static constants.ModelConstants.TIME_DOUBLE_DIGIT_CHECK;
import static constants.ModelConstants.TIME_ZERO;

import constants.StringConstants;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A singleton Timer model class that is Observable.
 *
 * @author Thibault Helsmoortel
 */
public class Timer {

    private static final Logger LOGGER = LogManager.getLogger(Timer.class);

    private static Timer thibTimer = new Timer();
    private Timeable timeable = new TimerStateWatch();

    private javax.swing.Timer swingTimer;
    private int seconds;
    private int minutes;
    private int hours;

    private final List<PropertyChangeListener> propertyChangeListeners;

    /**
     * Constructor not publicly available, this is a singleton class
     * Creates a timer and initialises it to start from the current system's time
     */
    private Timer() {
        this.seconds = TIME_ZERO;
        this.minutes = TIME_ZERO;
        this.hours = TIME_ZERO;
        this.propertyChangeListeners = new ArrayList<>();
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
        javax.swing.Timer oldTimer = swingTimer;
        initialiseTimer();
        Calendar calendar = Calendar.getInstance();
        hours = Integer.parseInt(HOUR_FORMAT.format(calendar.getTime()));
        minutes = Integer.parseInt(MINUTE_FORMAT.format(calendar.getTime()));
        seconds = Integer.parseInt(SECOND_FORMAT.format(calendar.getTime()));
        LOGGER.debug(String.format("Set the current time to: %02d:%02d:%02d", hours, minutes, seconds));
        firePropertyChangeEvent(oldTimer, swingTimer);
    }

    public void addPropertyChangeEventListener(PropertyChangeListener listener) {
        propertyChangeListeners.add(listener);
    }

    private void firePropertyChangeEvent(javax.swing.Timer oldTimer, javax.swing.Timer newTimer) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "swingTimer", oldTimer, newTimer);
        propertyChangeListeners.forEach(listener -> listener.propertyChange(event));
    }

    /**
     * This method initialises the timer and fires the increment() or decrement() depending on the situation.
     */
    private void initialiseTimer() {
        swingTimer = new javax.swing.Timer(SECONDS_IN_MILLIS, e -> changeTime());
        swingTimer.start();
    }

    private void changeTime() {
        javax.swing.Timer oldTimer = swingTimer;
        timeable.changeTime();
        firePropertyChangeEvent(oldTimer, swingTimer);
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        javax.swing.Timer oldTimer = swingTimer;
        this.seconds = seconds;
        firePropertyChangeEvent(oldTimer, swingTimer);
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        javax.swing.Timer oldTimer = swingTimer;
        this.minutes = minutes;
        firePropertyChangeEvent(oldTimer, swingTimer);
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        javax.swing.Timer oldTimer = swingTimer;
        this.hours = hours;
        firePropertyChangeEvent(oldTimer, swingTimer);
    }

    private String getHourString() {
        String hourString = Integer.toString(this.hours);
        if (this.hours < TIME_DOUBLE_DIGIT_CHECK) return "" + TIME_ZERO + hours;
        return hourString;
    }

    private String getMinuteString() {
        String minuteString = Integer.toString(this.minutes);
        if (this.minutes < TIME_DOUBLE_DIGIT_CHECK) return "" + TIME_ZERO + minutes;
        return minuteString;
    }

    private String getSecondString() {
        String secondString = Integer.toString(this.seconds);
        if (this.seconds < TIME_DOUBLE_DIGIT_CHECK) return "" + TIME_ZERO + seconds;
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
        javax.swing.Timer oldTimer = swingTimer;
        hours = hourVal;
        minutes = minuteVal;
        seconds = secondVal;
        firePropertyChangeEvent(oldTimer, swingTimer);
    }

    /**
     * This method stops the timer and sets seconds, minutes and hours to 0
     */
    public void reset() {
        javax.swing.Timer oldTimer = swingTimer;
        swingTimer.stop();
        hours = TIME_ZERO;
        minutes = TIME_ZERO;
        seconds = TIME_ZERO;
        firePropertyChangeEvent(oldTimer, swingTimer);
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