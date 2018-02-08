package be.thibaulthelsmoortel.thibtimer.model;

import static be.thibaulthelsmoortel.thibtimer.constants.ModelConstants.HOUR_FORMAT;
import static be.thibaulthelsmoortel.thibtimer.constants.ModelConstants.MINUTE_FORMAT;
import static be.thibaulthelsmoortel.thibtimer.constants.ModelConstants.SECONDS_IN_MILLIS;
import static be.thibaulthelsmoortel.thibtimer.constants.ModelConstants.SECOND_FORMAT;

import be.thibaulthelsmoortel.thibtimer.constants.StringConstants;
import be.thibaulthelsmoortel.thibtimer.factories.TimeChangeFactory;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A singleton Timer model class that fires events.
 *
 * @author Thibault Helsmoortel
 */
public class Timer {

    private static final Logger LOGGER = LogManager.getLogger(Timer.class);

    private static Timer timer = new Timer();
    private TimerChangeStrategy timerChangeStrategy;
    private TimeChanger timeChanger;

    private javax.swing.Timer swingTimer;
    private Time time;

    private final List<PropertyChangeListener> propertyChangeListeners;

    /**
     * Constructor not publicly available, this is a singleton class
     * Creates a timer and initialises it to start from the current system's time
     */
    private Timer() {
        this.time = new Time();
        this.propertyChangeListeners = new ArrayList<>();
        setTimerChangeStrategy(TimerChangeStrategy.WATCH);
        initialiseTimer();
        setTimerToCurrentTime();
    }

    /**
     * @return timer, this is the instance that manages all the time features
     */
    public static synchronized Timer getInstance() {
        return timer;
    }

    /**
     * This method sets the timer values to the current (system) time
     * Note: the timer might have around a one second delay to the real system's time
     * This is because the timer is first set to the current time, and afterwards initialised
     */
    public void setTimerToCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        time.setHours(Integer.parseInt(HOUR_FORMAT.format(calendar.getTime())));
        time.setMinutes(Integer.parseInt(MINUTE_FORMAT.format(calendar.getTime())));
        time.setSeconds(Integer.parseInt(SECOND_FORMAT.format(calendar.getTime())));
        setTime(time);
        LOGGER.trace(String.format("Set the current time to: %02d:%02d:%02d", time.getHours(), time.getMinutes(), time.getSeconds()));
    }

    public void addPropertyChangeEventListener(PropertyChangeListener listener) {
        propertyChangeListeners.add(listener);
    }

    private void firePropertyChangeEvent(javax.swing.Timer oldTimer, javax.swing.Timer newTimer) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "swingTimer", oldTimer, newTimer);
        propertyChangeListeners.forEach(listener -> listener.propertyChange(event));
    }

    /**
     * This method initialises the timer and fires the changeTime method.
     */
    private void initialiseTimer() {
        swingTimer = new javax.swing.Timer(SECONDS_IN_MILLIS, e -> changeTime());
        swingTimer.start();
    }

    private void changeTime() {
        javax.swing.Timer oldTimer = swingTimer;
        timeChanger.changeTime();
        firePropertyChangeEvent(oldTimer, swingTimer);
    }

    public void setTime(Time time) {
        javax.swing.Timer oldTimer = swingTimer;
        this.time = time;
        firePropertyChangeEvent(oldTimer, swingTimer);
    }

    public Time getTime() {
        return time;
    }

    /**
     * This method stops the timer and sets seconds, minutes and hours to 0
     */
    public void reset() {
        javax.swing.Timer oldTimer = swingTimer;
        swingTimer.stop();
        time.reset();
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

    public TimerChangeStrategy getTimerChangeStrategy() {
        return timerChangeStrategy;
    }

    public void setTimerChangeStrategy(TimerChangeStrategy timerChangeStrategy) {
        this.timerChangeStrategy = timerChangeStrategy;
        this.timeChanger = TimeChangeFactory.getInstance().createTimeChanger(timerChangeStrategy);
    }

    @Override
    public String toString() {
        return time.getHourString() + StringConstants.TIMER_EXTENDED_DELIMITER + time.getMinuteString() + StringConstants.TIMER_EXTENDED_DELIMITER + time.getSecondString();
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