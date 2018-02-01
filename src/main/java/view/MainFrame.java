/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package view;

import constants.StringConstants;
import constants.ViewConstants;
import model.*;
import model.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * This is the main view class of the application.
 *
 * @author Thibault Helsmoortel
 */
public class MainFrame extends JFrame implements Observer {
    private static final Timer timer = Timer.getInstance();

    private LanguageManager languageManager = LanguageManager.getInstance();

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
        addListeners();
        showFrame();
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
            timer.setTimeable(new TimerStateTimer());
            btnSet.setEnabled(true);
            btnStart.setEnabled(false);
        });
        rbChrono.addActionListener(e -> {
            timer.stop();
            timer.reset();
            timer.setTimeable(new TimerStateChrono());
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
        timer.addObserver(this);
        languageManager.addObserver(this);
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

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Timer) {
            Timer thibTimer = (Timer) o;
            tfTimer.setText(thibTimer.toString());
        }

        if (o instanceof LanguageManager) {
            //Language update
            LanguageManager languageMGR = (LanguageManager) o;
            btnSet.setText(languageMGR.getString(StringConstants.SET));
            btnStart.setText(languageMGR.getString(StringConstants.START));
            btnStop.setText(languageMGR.getString(StringConstants.STOP));
            btnReset.setText(languageMGR.getString(StringConstants.RESET));
            rbTimer.setText(languageMGR.getString(StringConstants.TIMER));
            rbChrono.setText(languageMGR.getString(StringConstants.CHRONO));
            rbWatch.setText(languageMGR.getString(StringConstants.WATCH));
        }
        pack();
    }

    public void setTheme(Theme theme) {
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
                if (timer.getTimeable() instanceof TimerStateTimer)
                    timer.setTimerTime((Integer) spHours.getValue(), (Integer) spMinutes.getValue(), (Integer) spSeconds.getValue());
                else timer.reset();
                btnStart.setEnabled(true);
                btnStop.setEnabled(false);
                btnReset.setEnabled(false);
            }
        }
    }
}
