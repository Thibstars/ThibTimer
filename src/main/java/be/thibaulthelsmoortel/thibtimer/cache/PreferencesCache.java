package be.thibaulthelsmoortel.thibtimer.cache;

import be.thibaulthelsmoortel.thibtimer.io.PreferenceReader;
import be.thibaulthelsmoortel.thibtimer.model.Preference;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Thibault Helsmoortel
 * @since 02/02/2018
 */
public class PreferencesCache {

    private static PreferencesCache ourInstance = new PreferencesCache();

    private ImmutableMap<String, Preference> preferences;

    private PreferencesCache() {
    }

    public Map<String, Preference> getPreferences() {
        if (preferences == null) {
            Builder<String, Preference> builder = ImmutableMap.builder();
            builder.putAll(PreferenceReader.read().stream()
                    .collect(Collectors.toMap(Preference::getKey, p -> p)));
            preferences = builder.build();
        }
        return preferences;
    }

    public static PreferencesCache getInstance() {
        return ourInstance;
    }
}
