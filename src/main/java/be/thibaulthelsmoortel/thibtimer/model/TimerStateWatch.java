package be.thibaulthelsmoortel.thibtimer.model;

/**
 * Watch timer state.
 *
 * @author Thibault Helsmoortel
 */
public class TimerStateWatch implements TimerState {

    @Override
    public void changeTime() {
        updateCurrentTime();
    }
}
