package be.thibaulthelsmoortel.thibtimer.model;

import java.util.EnumMap;

/**
 * Factory class for TimeChange's.
 *
 * @author Thibault Helsmoortel
 * @since 02/02/2018
 */
public class TimeChangeFactory {

    private static TimeChangeFactory ourInstance = new TimeChangeFactory();

    public static TimeChangeFactory getInstance() {
        return ourInstance;
    }

    private EnumMap<TimerChangeStrategy, TimeChanger> timeChangers;

    private TimeChangeFactory() {
    }

    public TimeChanger createTimeChanger(TimerChangeStrategy strategy) {
        if (timeChangers == null) {
            timeChangers = new EnumMap<>(TimerChangeStrategy.class);
        }
        if (!timeChangers.containsKey(strategy)) {
            TimeChanger timeChanger;
            switch (strategy) {
                case TIMER:
                    timeChanger = new TimerStateTimer();
                    break;
                case WATCH:
                    timeChanger = new TimerStateWatch();
                    break;
                case CHRONOMETER:
                    timeChanger = new TimerStateChronometer();
                    break;
                    default:
                        timeChanger = new TimerStateWatch();
            }
            timeChangers.put(strategy, timeChanger);
        }
        return timeChangers.get(strategy);
    }
}
