/*
 * Copyright (c) 2017 by Thibault Helsmoortel.
 * This code has been entirely written and is entirely owned by Thibault Helsmoortel.
 * Do not (re)distribute or copy code without written permission of Thibault Helsmoortel.
 */

package model;

/**
 * Interface representing something that can be timed.
 *
 * @author Thibault Helsmoortel
 */
public interface Timeable {

    void changeTime(); //Increment or decrement time
}
