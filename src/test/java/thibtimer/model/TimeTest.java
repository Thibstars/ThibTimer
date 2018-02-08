package thibtimer.model;

import static be.thibaulthelsmoortel.thibtimer.constants.ModelConstants.TIME_ZERO;
import static org.junit.Assert.assertEquals;

import be.thibaulthelsmoortel.thibtimer.model.Time;
import java.util.Random;
import org.junit.Test;

/**
 * @author Thibault Helsmoortel
 * @since 06/02/2018
 */
public class TimeTest {

    private static final int TEST_REPEATS = 10;
    private Time time;
    private static final int LOW = 0;
    private static final int HIGH = 60;

    @Test
    public void shouldProperlyInitialiseTimer() {
        this.time = new Time();
        assertTimeInInitialState();
    }

    private void assertTimeInInitialState() {
        assertEquals("Seconds should be 0", TIME_ZERO, time.getSeconds() );
        assertEquals("Minutes should be 0", TIME_ZERO, time.getMinutes());
        assertEquals("Hours should be 0", TIME_ZERO, time.getHours());
    }

    @Test
    public void shouldProperlyResetTimer() {
        this.time = new Time();
        Random random = new Random();
        for (int i=0; i< TEST_REPEATS; i++) {
            time.setSeconds(random.nextInt(HIGH - LOW) + LOW);
            time.setMinutes(random.nextInt(HIGH - LOW) + LOW);
            time.setHours(random.nextInt(HIGH - LOW) + LOW);
            time.reset();
            assertTimeInInitialState();
        }
    }

    @Test
    public void shouldProperlyFormatTimeStrings() {
        this.time = new Time();
        Random random = new Random();
        for (int i=0; i< TEST_REPEATS; i++) {
            time.setSeconds(random.nextInt(HIGH - LOW) + LOW);
            time.setMinutes(random.nextInt(HIGH - LOW) + LOW);
            time.setHours(random.nextInt(HIGH - LOW) + LOW);
            time.reset();
            assertStringsProperlyFormatted();
        }
    }

    private void assertStringsProperlyFormatted() {
        assertFormatCorrect(time.getSecondString());
        assertFormatCorrect(time.getMinuteString());
        assertFormatCorrect(time.getHourString());
    }

    private void assertFormatCorrect(String timeString) {
        assertEquals("Format must have length of 2.", timeString.length(), 2);
    }
}
