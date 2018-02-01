package be.thibaulthelsmoortel.thibtimer.view;

import be.thibaulthelsmoortel.thibtimer.constants.StringConstants;
import be.thibaulthelsmoortel.thibtimer.constants.ViewConstants;
import be.thibaulthelsmoortel.thibtimer.model.LanguageManager;
import be.thibaulthelsmoortel.thibtimer.model.Preference;
import be.thibaulthelsmoortel.thibtimer.model.Theme;
import be.thibaulthelsmoortel.thibtimer.model.Timer;
import be.thibaulthelsmoortel.thibtimer.model.TimerStateChrono;
import be.thibaulthelsmoortel.thibtimer.model.TimerStateTimer;
import be.thibaulthelsmoortel.thibtimer.util.JsonFileWriter;
import be.thibaulthelsmoortel.thibtimer.util.PreferenceReader;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * This is the main view class of the application.
 *
 * @author Thibault Helsmoortel
 */
public class MainFrame extends JFrame {

    private static final Timer timer = Timer.getInstance();

    private LanguageManager languageManager = LanguageManager.getInstance();

    private Theme theme;
    private JPanel pnlDisplay;
    private JPanel pnlControl;
    private JPanel pnlTime;
    private JPanel pnlSetTime;
    private JPanel pnlButtons;
    private JPanel pnlRadioButtons;
    private JTextField tfTimer;
    private JSpinner spHours, spMinutes, spSeconds;
    private JButton btnSet, btnStart, btnStop, btnReset;
    private JRadioButton rbTimer, rbChrono, rbWatch;
    private ButtonGroup btnGroup;

    public MainFrame() throws HeadlessException {
        super(StringConstants.APP_TITLE);
        createDisplayPanel();
        createControlPanel();
        readPreferences();
        showFrame();
        addListeners();
    }

    private void readPreferences() {
        List<Preference> preferences = PreferenceReader.read();
        preferences.forEach(preference -> {
            if ("theme".equals(preference.getKey())) {
                setTheme(Theme.valueOf(preference.getValue().toUpperCase()));
            }
        });
    }

    private void createDisplayPanel() {
        setJMenuBar(new MenuBar(this));
        pnlTime = new JPanel(ViewConstants.TIME_PANEL_LAYOUT);
        Font font = ViewConstants.TIMER_FONT;
        tfTimer = new JTextField(timer.toString());
        tfTimer.setFont(font);
        tfTimer.setEditable(false);
        tfTimer.setBorder(ViewConstants.TIMER_HIDDEN_BORDER);

        pnlSetTime = new JPanel(ViewConstants.SET_TIME_PANEL_LAYOUT);
        spHours = new JSpinner(ViewConstants.HOUR_MODEL);
        spHours.setValue(0);
        spHours.setFont(font);
        spMinutes = new JSpinner(ViewConstants.MINUTE_MODEL);
        spMinutes.setValue(0);
        spMinutes.setFont(font);
        spSeconds = new JSpinner(ViewConstants.SECOND_MODEL);
        spSeconds.setFont(font);
        spSeconds.setValue(0);
        pnlSetTime.setVisible(false);

        JLabel lblDelimiter = new JLabel(StringConstants.TIMER_DELIMITER, JLabel.CENTER);
        lblDelimiter.setFont(font);
        JLabel lblDelimiter2 = new JLabel(StringConstants.TIMER_DELIMITER, JLabel.CENTER);
        lblDelimiter2.setFont(font);


        pnlSetTime.add(spHours);
        pnlSetTime.add(lblDelimiter);
        pnlSetTime.add(spMinutes);
        pnlSetTime.add(lblDelimiter2);
        pnlSetTime.add(spSeconds);

        pnlTime.add(tfTimer);
        pnlTime.setVisible(true);

        pnlDisplay = new JPanel();
        pnlDisplay.add(pnlSetTime);
        pnlDisplay.add(pnlTime);

        add(pnlDisplay, BorderLayout.NORTH);
    }

    private void createControlPanel() {
        rbTimer = new JRadioButton(languageManager.getString(StringConstants.TIMER));
        rbChrono = new JRadioButton(languageManager.getString(StringConstants.CHRONO));
        rbWatch = new JRadioButton(languageManager.getString(StringConstants.WATCH));
        btnGroup = new ButtonGroup();
        btnGroup.add(rbTimer);
        btnGroup.add(rbChrono);
        btnGroup.add(rbWatch);
        rbTimer.setSelected(false);
        rbChrono.setSelected(false);
        rbWatch.setSelected(true);

        pnlRadioButtons = new JPanel(ViewConstants.RADIO_BUTTONS_PANEL_LAYOUT);
        pnlRadioButtons.add(rbTimer);
        pnlRadioButtons.add(rbChrono);
        pnlRadioButtons.add(rbWatch);

        pnlButtons = new JPanel(ViewConstants.BUTTONS_PANEL_LAYOUT);
        btnSet = new JButton(languageManager.getString(StringConstants.SET));
        btnStart = new JButton(languageManager.getString(StringConstants.START));
        btnStop = new JButton(languageManager.getString(StringConstants.STOP));
        btnReset = new JButton(languageManager.getString(StringConstants.RESET));
        btnSet.setEnabled(false);
        btnStop.setEnabled(false);
        btnStart.setEnabled(false);
        btnReset.setEnabled(false);

        pnlButtons.add(btnSet);
        pnlButtons.add(btnStart);
        pnlButtons.add(btnStop);
        pnlButtons.add(btnReset);
        pnlButtons.add(pnlRadioButtons);

        pnlControl = new JPanel();
        pnlControl.add(pnlButtons);

        setTheme(Theme.LIGHT);

        add(pnlControl, BorderLayout.SOUTH);
    }

    private void addListeners() {
        btnSet.addActionListener(e -> {
            timer.setHours((Integer) spHours.getValue());
            timer.setMinutes((Integer) spMinutes.getValue());
            timer.setSeconds((Integer) spSeconds.getValue());
            if (pnlSetTime.isVisible()) {
                pnlSetTime.setVisible(false);
                pnlTime.setVisible(true);
                pnlButtons.setLayout(ViewConstants.BUTTONS_PANEL_LAYOUT);
                btnStart.setVisible(true);
                btnStop.setVisible(true);
                btnReset.setVisible(true);
                btnStart.setEnabled(true);
                rbTimer.setVisible(true);
                rbChrono.setVisible(true);
                rbWatch.setVisible(true);
                if (rbTimer.isSelected() && (Integer) spHours.getValue() == 0 && (Integer) spMinutes.getValue() == 0 && (Integer) spSeconds.getValue() == 0)
                    btnStart.setEnabled(false);
            } else {
                timer.stop();
                pnlTime.setVisible(false);
                pnlSetTime.setVisible(true);
                pnlButtons.setLayout(ViewConstants.BUTTONS_PANEL_LAYOUT_SET_MODE);
                btnStart.setVisible(false);
                btnStop.setVisible(false);
                btnReset.setVisible(false);
                btnStart.setEnabled(false);
                btnStop.setEnabled(false);
                btnReset.setEnabled(false);
                rbTimer.setVisible(false);
                rbChrono.setVisible(false);
                rbWatch.setVisible(false);
            }
            pack();
        });
        btnStart.addActionListener(new TimerActionListener());
        btnStop.addActionListener(new TimerActionListener());
        btnReset.addActionListener(new TimerActionListener());
        rbTimer.addActionListener(e -> {
            timer.stop();
            timer.reset();
            timer.setTimeChanger(new TimerStateTimer());
            btnSet.setEnabled(true);
            btnStart.setEnabled(false);
        });
        rbChrono.addActionListener(e -> {
            timer.stop();
            timer.reset();
            timer.setTimeChanger(new TimerStateChrono());
            btnSet.setEnabled(false);
            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
            btnReset.setEnabled(false);
        });
        rbWatch.addActionListener(e -> {
            timer.stop();
            btnSet.setEnabled(false);
            btnStart.setEnabled(false);
            btnStop.setEnabled(false);
            btnReset.setEnabled(false);
            timer.setTimerToCurrentTime();
            timer.start();
        });
        timer.addPropertyChangeEventListener(event -> {
            if (event.getNewValue() instanceof javax.swing.Timer) {
                tfTimer.setText(timer.toString());
            }
        });
        languageManager.addPropertyChangeListener(event -> {
            if (event.getNewValue() instanceof LanguageManager) {
                // Language update
                LanguageManager languageMGR = (LanguageManager) event.getNewValue();
                btnSet.setText(languageMGR.getString(StringConstants.SET));
                btnStart.setText(languageMGR.getString(StringConstants.START));
                btnStop.setText(languageMGR.getString(StringConstants.STOP));
                btnReset.setText(languageMGR.getString(StringConstants.RESET));
                rbTimer.setText(languageMGR.getString(StringConstants.TIMER));
                rbChrono.setText(languageMGR.getString(StringConstants.CHRONO));
                rbWatch.setText(languageMGR.getString(StringConstants.WATCH));
            }
            pack();
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                URL prefResources = this.getClass().getResource("/config/preferences.json");
                List<Preference> preferences = new ArrayList<>();
                Preference themePref = new Preference();
                themePref.setKey(StringConstants.PREFERENCE_THEME);
                themePref.setValue(theme.toString());
                preferences.add(themePref);
                Preference langPref = new Preference();
                langPref.setKey(StringConstants.PREFERENCE_LANGUAGE);
                langPref.setValue(languageManager.getLocale().getLanguage());
                preferences.add(langPref);
                JsonFileWriter.writeJsonToFile(prefResources.getPath(), preferences);

                super.windowClosing(e);
            }
        });
    }

    private void showFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);

        ((Graphics2D) tfTimer.getGraphics()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        btnStart.requestFocus();
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
        switch (theme) {
            case DARK:
                pnlDisplay.setBackground(ViewConstants.DISPLAY_PANEL_BG_COLOR_DARK);
                pnlRadioButtons.setBackground(ViewConstants.RADIO_BUTTONS_PANEL_BG_COLOR_DARK);
                tfTimer.setBackground(ViewConstants.TIMER_BG_COLOR_THEME_DARK);
                tfTimer.setForeground(ViewConstants.TIMER_FG_COLOR_THEME_DARK);
                spHours.setBackground(ViewConstants.SET_SPINNERS_BG_COLOR_THEME_DARK);
                spMinutes.setBackground(ViewConstants.SET_SPINNERS_BG_COLOR_THEME_DARK);
                spSeconds.setBackground(ViewConstants.SET_SPINNERS_BG_COLOR_THEME_DARK);
                pnlControl.setBackground(ViewConstants.CONTROL_PANEL_BG_COLOR_THEME_DARK);
                pnlButtons.setBackground(ViewConstants.BUTTONS_PANEL_BG_COLOR_THEME_DARK);
                break;
            case LIGHT:
                pnlDisplay.setBackground(ViewConstants.DISPLAY_PANEL_BG_COLOR_LIGHT);
                pnlRadioButtons.setBackground(ViewConstants.RADIO_BUTTONS_PANEL_BG_COLOR_LIGHT);
                tfTimer.setBackground(ViewConstants.TIMER_BG_COLOR_THEME_LIGHT);
                tfTimer.setForeground(ViewConstants.TIMER_FG_COLOR_THEME_LIGHT);
                spHours.setBackground(ViewConstants.SET_SPINNERS_BG_COLOR_THEME_LIGHT);
                spMinutes.setBackground(ViewConstants.SET_SPINNERS_BG_COLOR_THEME_LIGHT);
                spSeconds.setBackground(ViewConstants.SET_SPINNERS_BG_COLOR_THEME_LIGHT);
                pnlControl.setBackground(ViewConstants.CONTROL_PANEL_BG_COLOR_THEME_LIGHT);
                pnlButtons.setBackground(ViewConstants.BUTTONS_PANEL_BG_COLOR_THEME_LIGHT);
                break;
        }
    }

    private class TimerActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnStart) {
                timer.start();
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
                btnReset.setEnabled(false);
                if (rbWatch.isSelected()) timer.setTimerToCurrentTime();
                if (rbChrono.isSelected()) btnReset.setEnabled(true);
            } else if (e.getSource() == btnStop) {
                timer.stop();
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                btnReset.setEnabled(true);
            } else if (e.getSource() == btnReset) {
                timer.stop();
                if (timer.getTimeChanger() instanceof TimerStateTimer)
                    timer.setTimerTime((Integer) spHours.getValue(), (Integer) spMinutes.getValue(), (Integer) spSeconds.getValue());
                else timer.reset();
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                btnReset.setEnabled(false);
            }
        }
    }
}
