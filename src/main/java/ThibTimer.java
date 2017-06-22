/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */


import view.MainFrame;

import javax.swing.*;

/**
 * Application entry point.
 *
 * @author Thibault Helsmoortel
 */

public class ThibTimer {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
