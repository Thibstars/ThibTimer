/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package view;

import constants.StringConstants;
import constants.ViewConstants;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import model.LanguageManager;

/**
 * This class contains a screen with details about the application.
 *
 * @author Thibault Helsmoortel
 */
public class AboutFrame extends JDialog {

    private LanguageManager languageManager = LanguageManager.getInstance();
    private JTextArea taAboutText;
    private JButton btnClose;

    public AboutFrame() {
        setTitle(languageManager.getString(StringConstants.ABOUT) + StringConstants.ABOUT_SPACE + StringConstants.APP_TITLE);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        initialiseComponents();
        addLayout();
        addActionListeners();
        addComponents();
        setSize(ViewConstants.ABOUT_FRAME_DIMENSION);
        setVisible(true);
        setResizable(false);
    }

    private void addLayout() {
        taAboutText.setFont(ViewConstants.ABOUT_TEXT_FONT);
    }

    private void addComponents() {
        add(taAboutText);
        add(btnClose, BorderLayout.PAGE_END);
    }

    private void addActionListeners() {
        btnClose.addActionListener(e -> dispose());
    }

    private void initialiseComponents() {
        taAboutText = new JTextArea(languageManager.getString(StringConstants.ABOUT_TEXT));
        taAboutText.setLineWrap(true);
        taAboutText.setWrapStyleWord(true);
        //taAboutText.setRows(ViewConstants.ABOUT_TEXT_ROW_AMOUNT);
        taAboutText.setEditable(false);
        btnClose = new JButton(languageManager.getString(StringConstants.CLOSE));
    }
}
