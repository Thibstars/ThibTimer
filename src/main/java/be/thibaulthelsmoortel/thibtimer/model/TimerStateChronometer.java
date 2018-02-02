package be.thibaulthelsmoortel.thibtimer.model;

/**
 * Chronometer timer state.
 *
 * @author Thibault Helsmoortel
 */
public class TimerStateChronometer implements TimerState {

    @Override
    public void changeTime() {
        incrementTime();
    }
}
