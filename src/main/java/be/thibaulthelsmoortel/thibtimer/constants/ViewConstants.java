package be.thibaulthelsmoortel.thibtimer.constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

/**
 * This class describes constant, unchanging fields used in be.thibaulthelsmoortel.thibtimer.view classes.
 *
 * @author Thibault Helsmoortel
 */
public final class ViewConstants {

    //MAINFRAME
    public static final Font TIMER_FONT = new Font("Helvetica", Font.PLAIN, 60);
    public static final GridLayout TIME_PANEL_LAYOUT = new GridLayout(1, 0, 1, 2);
    public static final Color TIMER_BG_COLOR_THEME_DARK = Color.DARK_GRAY;
    public static final Color TIMER_BG_COLOR_THEME_LIGHT = Color.LIGHT_GRAY;
    public static final Color TIMER_FG_COLOR_THEME_DARK = Color.WHITE;
    public static final Color TIMER_FG_COLOR_THEME_LIGHT = Color.BLACK;
    public static final Border TIMER_HIDDEN_BORDER = BorderFactory.createEmptyBorder(0, 0, 0, 0);
    public static final GridLayout SET_TIME_PANEL_LAYOUT = new GridLayout(1, 0, 5, 2);
    public static final Color SET_SPINNERS_BG_COLOR_THEME_DARK = Color.LIGHT_GRAY;
    public static final Color SET_SPINNERS_BG_COLOR_THEME_LIGHT = Color.LIGHT_GRAY;
    public static final Color DISPLAY_PANEL_BG_COLOR_DARK = Color.DARK_GRAY;
    public static final Color DISPLAY_PANEL_BG_COLOR_LIGHT = Color.LIGHT_GRAY;
    public static final GridLayout RADIO_BUTTONS_PANEL_LAYOUT = new GridLayout(0, 1);
    public static final Color RADIO_BUTTONS_PANEL_BG_COLOR_DARK = Color.GRAY;
    public static final Color RADIO_BUTTONS_PANEL_BG_COLOR_LIGHT = Color.LIGHT_GRAY;
    public static final GridLayout BUTTONS_PANEL_LAYOUT = new GridLayout(1, 0, 3, 3);
    public static final Color BUTTONS_PANEL_BG_COLOR_THEME_DARK = Color.GRAY;
    public static final Color BUTTONS_PANEL_BG_COLOR_THEME_LIGHT = Color.WHITE;
    public static final GridLayout BUTTONS_PANEL_LAYOUT_SET_MODE = new GridLayout(1, 1);
    public static final Color CONTROL_PANEL_BG_COLOR_THEME_DARK = Color.GRAY;
    public static final Color CONTROL_PANEL_BG_COLOR_THEME_LIGHT = Color.WHITE;

    public static final SpinnerNumberModel HOUR_MODEL = new SpinnerNumberModel(0, 0, 99, 1);
    public static final SpinnerNumberModel MINUTE_MODEL = new SpinnerNumberModel(0, 0, 60, 1);
    public static final SpinnerNumberModel SECOND_MODEL = new SpinnerNumberModel(0, 0, 60, 1);

    //ABOUTFRAME
    public static final Dimension ABOUT_FRAME_DIMENSION = new Dimension(250, 125);
    public static final Font ABOUT_TEXT_FONT = new Font("Helvetica", Font.PLAIN, 16);
    public static final int ABOUT_TEXT_ROW_AMOUNT = 2;

    private ViewConstants() {
        // Prevent instantiation
    }
}
