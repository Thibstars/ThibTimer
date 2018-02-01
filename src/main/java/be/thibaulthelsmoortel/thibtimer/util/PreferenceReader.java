package be.thibaulthelsmoortel.thibtimer.util;

import be.thibaulthelsmoortel.thibtimer.model.Preference;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thibault Helsmoortel
 * @since 01/02/2018
 */
public final class PreferenceReader {

    private PreferenceReader() {
        // Prevent instantiation
    }

    public static List<Preference> read() {
        List<Preference> preferences = new ArrayList<>();
        URL prefResources = PreferenceReader.class.getResource("/config/preferences.json");
        JsonElement preferencesElement = JsonFileReader.readFromResource(
                prefResources);
        JsonArray jsonPreferences = preferencesElement.getAsJsonArray();
        GsonBuilder gsonBuilder = new GsonBuilder();
        jsonPreferences.forEach(pref -> {
            JsonObject jsonPreference = pref.getAsJsonObject();
            Preference preference = gsonBuilder.create().fromJson(jsonPreference, Preference.class);
            preferences.add(preference);
        });
        return preferences;
    }
}
