package be.thibaulthelsmoortel.thibtimer;

import be.thibaulthelsmoortel.thibtimer.view.MainFrame;
import javax.swing.SwingUtilities;

/**
 * Application entry point.
 *
 * @author Thibault Helsmoortel
 */
public class TimerApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
