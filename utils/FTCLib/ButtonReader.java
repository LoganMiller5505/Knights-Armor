package org.firstinspires.ftc.teamcode.utils.FTCLib;

import java.util.function.BooleanSupplier;

/*
 *************************************************************************************
 *   NOTE: THIS IS VERY HEAVILY COPIED FROM FTCLIB'S BUTTONREADER CLASS.             *
 *                                                                                   *
 *   ALL CREDIT for all functionality here goes to the original authors of FTCLib.   *
 *************************************************************************************
 */

/**
 * Class that reads the value of button states.
 */
public class ButtonReader implements KeyReader {

    /**
     * Last state of the button
     **/
    private boolean lastState;

    /**
     * Current state of the button
     **/
    private boolean currState;

    /**
     * the state of the button
     */
    private BooleanSupplier buttonState;

    /**
     * Initializes controller variables
     *
     * @param gamepad The controller joystick
     * @param button  The controller button
     **/
    public ButtonReader(GamepadEx gamepad, GamepadKeys.Button button) {
        buttonState = () -> gamepad.getButton(button);
        currState = buttonState.getAsBoolean();
        lastState = currState;
    }

    public ButtonReader(BooleanSupplier buttonValue) {
        buttonState = buttonValue;
        currState = buttonState.getAsBoolean();
        lastState = currState;
    }

    /**
     * Reads button value
     **/
    public void readValue() {
        lastState = currState;
        currState = buttonState.getAsBoolean();
    }

    /**
     * Checks if the button is down
     **/
    public boolean isDown() {
        return buttonState.getAsBoolean();
    }

    /**
     * Checks if the button was just pressed
     **/
    public boolean wasJustPressed() {
        return (!lastState && currState);
    }

    /**
     * Checks if the button was just released
     **/
    public boolean wasJustReleased() {
        return (lastState && !currState);
    }

    /**
     * Checks if the button state has changed
     **/
    public boolean stateJustChanged() {
        return (lastState != currState);
    }

}