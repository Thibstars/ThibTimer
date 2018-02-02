package thibtimer.io;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import be.thibaulthelsmoortel.thibtimer.io.PreferenceReader;
import be.thibaulthelsmoortel.thibtimer.model.Preference;
import java.util.List;
import org.junit.Test;

/**
 * @author Thibault Helsmoortel
 * @since 02/02/2018
 */
public class PreferenceReaderTest {

    @Test
    public void shouldReadPreferences() {
        List<Preference> preferences = PreferenceReader.read();
        assertThat(
                "Preferences must be read.",
                preferences,
                notNullValue()
        );
        assertThat(
                "Preferences must not be empty.",
                preferences.size(),
                not(equalTo(0))
        );
        assertThat(
                "Preferences should be read out correctly.",
                preferences.get(0),
                notNullValue()
        );
        assertThat(
                "Preferences should be read out correctly.",
                preferences.get(0).getKey(),
                notNullValue()
        );
        assertThat(
                "Preferences should be read out correctly.",
                preferences.get(0).getValue(),
                notNullValue()
        );
    }
}
