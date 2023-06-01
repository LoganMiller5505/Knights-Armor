package org.firstinspires.ftc.teamcode.utils.FTCLib;

/*
 *************************************************************************************
 *   NOTE: THIS IS VERY HEAVILY COPIED FROM FTCLIB'S KEYREADER CLASS.                *
 *                                                                                   *
 *   ALL CREDIT for all functionality here goes to the original authors of FTCLib.   *
 *************************************************************************************
 */

public interface KeyReader {
    /**
     * Reads button value
     **/
    void readValue();

    /**
     * Checks if the button is down
     **/
    boolean isDown();

    /**
     * Checks if the button was just pressed
     **/
    boolean wasJustPressed();

    /**
     * Checks if the button was just released
     **/
    boolean wasJustReleased();

    /**
     * Checks if the button state has changed
     **/
    boolean stateJustChanged();
}