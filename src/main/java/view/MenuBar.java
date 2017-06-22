/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package view;

import constants.StringConstants;
import model.LanguageManager;
import model.Theme;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

/**
 * This is the class used for the MenuBar in the main view class.
 *
 * @author Thibault Helsmoortel
 */

public class MenuBar extends JMenuBar implements Observer {

    private final MainFrame mainFrame;
    private LanguageManager languageManager = LanguageManager.getInstance();

    //TODO: add menu items for sounds
    private JMenu mnPreferences;
    private JMenu mnLanguage;
    private JMenuItem miAbout;
    private JMenuItem miEnglish;
    private JMenuItem miDutch;
    private JMenu mnTheme;
    private JMenuItem miDark;
    private JMenuItem miLight;
    private JMenu mnSound;

    public MenuBar(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        initialiseComponents();
        addActionListeners();
        addComponents();
    }

    private void addComponents() {
        add(miAbout);
        mnPreferences.add(mnLanguage);
        mnLanguage.add(miEnglish);
        mnLanguage.add(miDutch);
        mnPreferences.add(mnTheme);
        mnTheme.add(miDark);
        mnTheme.add(miLight);
        // mnPreferences.add(mnSound);
        add(mnPreferences);
    }

    private void addActionListeners() {
        miAbout.addActionListener(e -> new AboutFrame());
        miEnglish.addActionListener(new LanguageListener(StringConstants.EN));
        miDutch.addActionListener(new LanguageListener(StringConstants.NL));
        miDark.addActionListener(e -> mainFrame.setTheme(Theme.DARK));
        miLight.addActionListener(e -> mainFrame.setTheme(Theme.LIGHT));
        languageManager.addObserver(this);
    }

    private void initialiseComponents() {
        mnPreferences = new JMenu(languageManager.getString(StringConstants.PREFERENCES));
        mnLanguage = new JMenu(languageManager.getString(StringConstants.LANGUAGE));
        miEnglish = new JMenuItem(languageManager.getString(StringConstants.ENGLISH));
        miDutch = new JMenuItem(languageManager.getString(StringConstants.DUTCH));
        mnTheme = new JMenu(languageManager.getString(StringConstants.THEME));
        miDark = new JMenuItem(Theme.DARK.toString());
        miLight = new JMenuItem(Theme.LIGHT.toString());
        mnSound = new JMenu(languageManager.getString(StringConstants.SOUND));

        miAbout = new JMenuItem(languageManager.getString(StringConstants.ABOUT));
    }

    @Override
    public void update(Observable o, Object arg) {
        LanguageManager languageMGR = (LanguageManager) o;
        mnPreferences.setText(languageMGR.getString(StringConstants.PREFERENCES));
        mnLanguage.setText(languageMGR.getString(StringConstants.LANGUAGE));
        miEnglish.setText(languageMGR.getString(StringConstants.ENGLISH));
        miDutch.setText(languageMGR.getString(StringConstants.DUTCH));
        mnTheme.setText(languageManager.getString(StringConstants.THEME));
        miDark.setText(Theme.DARK.toString());
        miLight.setText(Theme.LIGHT.toString());
        mnSound.setText(languageManager.getString(StringConstants.SOUND));

        miAbout.setText(languageMGR.getString(StringConstants.ABOUT));
    }

    private class LanguageListener implements ActionListener {
        private String languageCode;

        public LanguageListener(String languageCode) {
            this.languageCode = languageCode;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            languageManager.setLanguage(new Locale(languageCode));
        }
    }
}