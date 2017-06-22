/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * This class describes the constants used in the model of the application.
 *
 * @author Thibault Helsmoortel
 */

public abstract class ModelConstants {

    public static final DateFormat HOUR_FORMAT = new SimpleDateFormat("HH");
    public static final DateFormat MINUTE_FORMAT = new SimpleDateFormat("mm");
    public static final DateFormat SECOND_FORMAT = new SimpleDateFormat("ss");
    public static final int SECONDS_IN_MILLIS = 1000;
    public static final int TIME_ZERO = 0;
    public static final int TIME_MAX = 59;
    public static final int TIME_NEXT_VALUE_AMOUNT = 60;
    public static final int TIME_DOUBLE_DIGIT_CHECK = 10;
}
