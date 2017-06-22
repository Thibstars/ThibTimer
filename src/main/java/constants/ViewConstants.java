/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package constants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * This class describes constant, unchanging fields used in view classes.
 *
 * @author Thibault Helsmoortel
 */

public abstract class ViewConstants {
    //MAINFRAME
    public static final Font TIMER_FONT = new Font("Helvetica", Font.PLAIN, 60);
    public static final GridLayout TIME_PANEL_LAYOUT = new GridLayout(1, 0, 1, 2);
    public static final Color TIMER_BG_COLOR = Color.DARK_GRAY;
    public static final Color TIMER_FG_COLOR = Color.WHITE;
    public static final Border TIMER_HIDDEN_BORDER = BorderFactory.createEmptyBorder(0, 0, 0, 0);
    public static final GridLayout SET_TIME_PANEL_LAYOUT = new GridLayout(1, 0, 5, 2);
    public static final Color SET_SPINNERS_BG_COLOR = Color.LIGHT_GRAY;
    public static final Color DISPLAY_PANEL_BG_COLOR = Color.DARK_GRAY;
    public static final GridLayout RADIO_BUTTONS_PANEL_LAYOUT = new GridLayout(0, 1);
    public static final Color RADIO_BUTTONS_PANEL_BG_COLOR = Color.GRAY;
    public static final GridLayout BUTTONS_PANEL_LAYOUT = new GridLayout(1, 0, 3, 3);
    public static final Color BUTTONS_PANEL_BG_COLOR = Color.GRAY;
    public static final GridLayout BUTTONS_PANEL_LAYOUT_SET_MODE = new GridLayout(1, 1);
    public static final Color CONTOL_PANEL_BG_COLOR = Color.GRAY;

    public static final SpinnerNumberModel HOUR_MODEL = new SpinnerNumberModel(0, 0, 99, 1);
    public static final SpinnerNumberModel MINUTE_MODEL = new SpinnerNumberModel(0, 0, 60, 1);
    public static final SpinnerNumberModel SECOND_MODEL = new SpinnerNumberModel(0, 0, 60, 1);

    //ABOUTFRAME
    public static final Dimension ABOUT_FRAME_DIMENSION = new Dimension(250, 125);
    public static final Font ABOUT_TEXT_FONT = new Font("Helvetica", Font.PLAIN, 16);
    public static final int ABOUT_TEXT_ROW_AMOUNT = 2;
}
