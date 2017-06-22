/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package model;

import constants.StringConstants;

import java.util.*;

/**
 * This class manages the language throughout the entire application.
 *
 * @author Thibault Helsmoortel
 */

public class LanguageManager extends Observable {

    private static LanguageManager languageManager = new LanguageManager();
    private Locale locale;
    private HashMap<String, String> textMap;

    /**
     * This constructor initialises the language that will be used in the entire application.
     * Standard language is the system's language, if available.
     * The default language (if system's language is not available) is English.
     * All Strings will be loaded in a HashMap.
     */
    private LanguageManager() {
        String s = System.getProperty("user.language");
        switch (s) {
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
        setChanged();
        notifyObservers();
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
        this.locale = language;
        setTextAccordingToLanguage();
        setChanged();
        notifyObservers();
    }

    /**
     * This method gets the correct Resource Bundle for the set language, reads all Strings
     * in it and places it in a HashMap. After completion all the Strings will be
     * updated according the language.
     */
    private void setTextAccordingToLanguage() {
        //Get the bundle according the language and place it in the textMap
        ResourceBundle language = ResourceBundle.getBundle(StringConstants.LANGUAGE_BUNDLE_BASE_NAME, locale);
        Enumeration bundleKeys = language.getKeys();

        while (bundleKeys.hasMoreElements()) {
            String key = (String) bundleKeys.nextElement();
            String value = language.getString(key);
            textMap.put(key, value);
        }
        setChanged();
        notifyObservers();
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
