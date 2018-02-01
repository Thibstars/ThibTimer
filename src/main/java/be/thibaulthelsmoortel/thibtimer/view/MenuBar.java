package be.thibaulthelsmoortel.thibtimer.view;

import be.thibaulthelsmoortel.thibtimer.constants.StringConstants;
import be.thibaulthelsmoortel.thibtimer.model.LanguageManager;
import be.thibaulthelsmoortel.thibtimer.model.Theme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This is the class used for the MenuBar in the main view class.
 *
 * @author Thibault Helsmoortel
 */
public class MenuBar extends JMenuBar {

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
        languageManager.addPropertyChangeListener(event -> {
            mnPreferences.setText(languageManager.getString(StringConstants.PREFERENCES));
            mnLanguage.setText(languageManager.getString(StringConstants.LANGUAGE));
            miEnglish.setText(languageManager.getString(StringConstants.ENGLISH));
            miDutch.setText(languageManager.getString(StringConstants.DUTCH));
            mnTheme.setText(languageManager.getString(StringConstants.THEME));
            miDark.setText(Theme.DARK.toString());
            miLight.setText(Theme.LIGHT.toString());
            mnSound.setText(languageManager.getString(StringConstants.SOUND));

            miAbout.setText(languageManager.getString(StringConstants.ABOUT));
        });
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