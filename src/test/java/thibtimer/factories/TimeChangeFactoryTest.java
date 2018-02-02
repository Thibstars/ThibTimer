package thibtimer.factories;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import be.thibaulthelsmoortel.thibtimer.factories.TimeChangeFactory;
import be.thibaulthelsmoortel.thibtimer.model.TimeChanger;
import be.thibaulthelsmoortel.thibtimer.model.TimerChangeStrategy;
import be.thibaulthelsmoortel.thibtimer.model.TimerStateChronometer;
import be.thibaulthelsmoortel.thibtimer.model.TimerStateTimer;
import be.thibaulthelsmoortel.thibtimer.model.TimerStateWatch;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author Thibault Helsmoortel
 * @since 02/02/2018
 */
@RunWith(value = Parameterized.class)
public class TimeChangeFactoryTest {

    private TimeChangeFactory factory;

    private final TimerChangeStrategy strategy;

    public TimeChangeFactoryTest(TimerChangeStrategy strategy) {
        this.strategy = strategy;
    }

    @Parameters(name = "{index}: strategy: {0}")
    public static Iterable<TimerChangeStrategy> data() {
        return Arrays.asList(
                TimerChangeStrategy.TIMER,
                TimerChangeStrategy.CHRONOMETER,
                TimerChangeStrategy.WATCH);
    }

    @Before
    public void setUp() {
        this.factory = TimeChangeFactory.getInstance();
    }

    @Test
    public void shouldCreateTimeChanger() {
        TimeChanger timeChanger = factory.createTimeChanger(strategy);
        assertThat(
                "Time changer should not be null.",
                timeChanger,
                notNullValue()
                );
        Class expected;
        switch (strategy) {
            case TIMER:
                expected = TimerStateTimer.class;
                break;
            case WATCH:
                expected = TimerStateWatch.class;
                break;
            case CHRONOMETER:
                expected = TimerStateChronometer.class;
                break;
            default:
                expected = TimerStateWatch.class;
        }
        assertThat(
                "Time changer should be of correct type.",
                timeChanger,
                instanceOf(expected)
        );
    }
}
