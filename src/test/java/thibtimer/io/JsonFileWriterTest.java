package thibtimer.io;

import static org.junit.Assert.assertTrue;

import be.thibaulthelsmoortel.thibtimer.constants.StringConstants;
import be.thibaulthelsmoortel.thibtimer.io.JsonFileWriter;
import be.thibaulthelsmoortel.thibtimer.model.LanguageManager;
import be.thibaulthelsmoortel.thibtimer.model.Preference;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * @author Thibault Helsmoortel
 * @since 6/02/2018
 */
public class JsonFileWriterTest {

    private static final LanguageManager languageManager = LanguageManager.getInstance();

    @Test
    public void shouldWriteJsonToFile() throws Exception {
        String newFilePath = "/test/testFile.json";
        URL resource = this.getClass().getResource(newFilePath);
        List<Preference> preferences = mockPreferences();

        JsonFileWriter.writeJsonToFile(resource.getPath(), preferences);

        File createdFile = new File(resource.getFile());
        assertTrue("File should exist.", createdFile.exists());
        assertTrue("File should not be a directory.", !createdFile.isDirectory());
        assertTrue("File should not be empty.", createdFile.length() > 0);
    }

    private List<Preference> mockPreferences() {
        List<Preference> preferences = new ArrayList<>();
        Preference themePref = new Preference();
        themePref.setKey(StringConstants.PREFERENCE_THEME);
        themePref.setValue("dark");
        preferences.add(themePref);
        Preference langPref = new Preference();
        langPref.setKey(StringConstants.PREFERENCE_LANGUAGE);
        langPref.setValue(languageManager.getLocale().getLanguage());
        preferences.add(langPref);
        return preferences;
    }
}
