package be.thibaulthelsmoortel.thibtimer.model;

/**
 * Chrono timer state.
 *
 * @author Thibault Helsmoortel
 */
public class TimerStateChrono implements TimerState {

    @Override
    public void changeTime() {
        incrementTime();
    }
}
