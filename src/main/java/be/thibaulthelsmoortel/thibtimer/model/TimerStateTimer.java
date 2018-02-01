package be.thibaulthelsmoortel.thibtimer.model;

/**
 * Timer timer state.
 *
 * @author Thibault Helsmoortel
 */
public class TimerStateTimer implements TimerState {

    @Override
    public void changeTime() {
        decrementTime();
    }
}
