package be.thibaulthelsmoortel.thibtimer.model;

import be.thibaulthelsmoortel.thibtimer.constants.StringConstants;
import be.thibaulthelsmoortel.thibtimer.util.PreferenceReader;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.commons.lang3.StringUtils;

/**
 * This class manages the language throughout the entire application.
 *
 * @author Thibault Helsmoortel
 */
public class LanguageManager {

    private static LanguageManager languageManager = new LanguageManager();
    private Locale locale;
    private HashMap<String, String> textMap;

    private final List<PropertyChangeListener> propertyChangeListeners;

    /**
     * This constructor initialises the language that will be used in the entire application.
     * Standard language is the system's language, if available.
     * The default language (if system's language is not available) is English.
     * All Strings will be loaded in a HashMap.
     */
    private LanguageManager() {
        this.propertyChangeListeners = new ArrayList<>();
        Preference langPref = PreferenceReader.read().stream()
                .filter(preference -> StringConstants.PREFERENCE_LANGUAGE.equals(preference.getKey()))
                .findFirst().orElseGet(() -> {
                    Preference defaultLang = new Preference();
                    defaultLang.setKey(StringConstants.PREFERENCE_LANGUAGE);
                    defaultLang.setValue(System.getProperty("user.language"));
                    return defaultLang;
                });
        String lang = StringUtils.isNotBlank(langPref.getValue()) ? langPref.getValue() : System.getProperty("user.language");
        switch (lang) {
            case StringConstants.NL:
                locale = new Locale(StringConstants.NL);

                break;
            case StringConstants.EN:
                locale = new Locale(StringConstants.EN);

                locale = new Locale(StringConstants.EN);
                break;
            default:
                locale = new Locale(StringConstants.EN);
                break;
        }
        textMap = new HashMap<>();
        setTextAccordingToLanguage();
        firePropertyChangeEvent(new HashMap<>(), textMap);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeListeners.add(listener);
    }

    private void firePropertyChangeEvent(HashMap<String, String> oldMap, HashMap<String, String> newMap) {
        PropertyChangeEvent event = new PropertyChangeEvent(this, "textMap", oldMap, newMap);
        propertyChangeListeners.forEach(listener -> listener.propertyChange(event));
    }

    public static synchronized LanguageManager getInstance() {
        return languageManager;
    }

    /**
     * This method changes the application's current language. After completion all Strings
     * will be updated thanks to the setTextAccordingToLanguage() method.
     *
     * @param language the Locale of the language
     */
    public void setLanguage(Locale language) {
        HashMap<String, String> oldMap = textMap;
        this.locale = language;
        setTextAccordingToLanguage();
        firePropertyChangeEvent(oldMap, textMap);
    }

    /**
     * This method gets the correct Resource Bundle for the set language, reads all Strings
     * in it and places it in a HashMap. After completion all the Strings will be
     * updated according the language.
     */
    private void setTextAccordingToLanguage() {
        HashMap<String, String> oldMap = textMap;

        //Get the bundle according the language and place it in the textMap
        ResourceBundle language = ResourceBundle.getBundle(StringConstants.LANGUAGE_BUNDLE_BASE_NAME, locale);
        Enumeration bundleKeys = language.getKeys();

        while (bundleKeys.hasMoreElements()) {
            String key = (String) bundleKeys.nextElement();
            String value = language.getString(key);
            textMap.put(key, value);
        }
        firePropertyChangeEvent(oldMap, textMap);
    }

    /**
     * This method fetches the requested String from the HashMap with all the Strings.
     *
     * @param stringToGet The requested String (key to find the String in textMap)
     * @return the requested String value, or the key if no value was found
     */
    public String getString(String stringToGet) {
        return textMap.get(stringToGet) != null ? textMap.get(stringToGet) : stringToGet;
    }

    public Locale getLocale() {
        return locale;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
