package org.firstinspires.ftc.teamcode.utils.FTCLib;


import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.utils.FTCLib.GamepadKeys.Button;

import java.util.HashMap;

/**
 * NOTE: THIS IS VERY HEAVILY COPIED FROM FTCLIB'S GAMEPADEX CLASS. The primary addition is an exponential ramp feature for the , and the removal of command-based systems within the class.   *
 *                                                                                                                                                                                               *
 * ALL CREDIT for all functionality here unless otherwise stated goes to the original authors of FTCLib.                                                                                       *
 */
public class GamepadEx {

    /**
     * The retrievable gamepad object
     */
    public Gamepad gamepad;
    private double rampStart;
    private double rampBVal;


    private HashMap<Button, ButtonReader> buttonReaders;

    private final Button[] buttons = {
            Button.Y, Button.X, Button.A, Button.B, Button.LEFT_BUMPER, Button.RIGHT_BUMPER, Button.BACK,
            Button.START, Button.DPAD_UP, Button.DPAD_DOWN, Button.DPAD_LEFT, Button.DPAD_RIGHT,
            Button.LEFT_STICK_BUTTON, Button.RIGHT_STICK_BUTTON
    };

    /**
     * The constructor, that contains the gamepad object from the
     * opmode.
     *
     * @param gamepad the gamepad object from the opmode
     */
    public GamepadEx(Gamepad gamepad) {
        this(gamepad, 0.125);
    }

    /**
     * The constructor, that contains the gamepad object from the
     * opmode. Features a custom start point for the linear ramp
     * of the Gamepad joysticks to become an exponential ramp.
     *
     * @param gamepad the gamepad object from the opmode
     * @param rampStart value at which the gamepads no longer returns linearly and instead uses an exponential increase
     */
    public GamepadEx(Gamepad gamepad, double rampStart) {

        this.rampStart=rampStart;
        this.rampBVal=Math.log(2-rampStart)/(1-rampStart);

        this.gamepad = gamepad;
        buttonReaders = new HashMap<>();
        for (Button button : buttons) {
            buttonReaders.put(button, new ButtonReader(this, button));
        }
    }


    /**
     * @param button the button object
     * @return the boolean value as to whether the button is active or not
     */
    public boolean getButton(Button button) {
        boolean buttonValue = false;
        switch (button) {
            case A:
                buttonValue = gamepad.a;
                break;
            case B:
                buttonValue = gamepad.b;
                break;
            case X:
                buttonValue = gamepad.x;
                break;
            case Y:
                buttonValue = gamepad.y;
                break;
            case LEFT_BUMPER:
                buttonValue = gamepad.left_bumper;
                break;
            case RIGHT_BUMPER:
                buttonValue = gamepad.right_bumper;
                break;
            case DPAD_UP:
                buttonValue = gamepad.dpad_up;
                break;
            case DPAD_DOWN:
                buttonValue = gamepad.dpad_down;
                break;
            case DPAD_LEFT:
                buttonValue = gamepad.dpad_left;
                break;
            case DPAD_RIGHT:
                buttonValue = gamepad.dpad_right;
                break;
            case BACK:
                buttonValue = gamepad.back;
                break;
            case START:
                buttonValue = gamepad.start;
                break;
            case LEFT_STICK_BUTTON:
                buttonValue = gamepad.left_stick_button;
                break;
            case RIGHT_STICK_BUTTON:
                buttonValue = gamepad.right_stick_button;
                break;
            default:
                buttonValue = false;
                break;
        }
        return buttonValue;
    }

    /**
     * @param trigger the trigger object
     * @return the value returned by the trigger in question
     */
    public double getTrigger(GamepadKeys.Trigger trigger) {
        double triggerValue = 0;
        switch (trigger) {
            case LEFT_TRIGGER:
                triggerValue = gamepad.left_trigger;
                break;
            case RIGHT_TRIGGER:
                triggerValue = gamepad.right_trigger;
                break;
            default:
                break;
        }
        return triggerValue;
    }

    /**
     * @return the y-value on the left analog stick
     */
    public double getLeftY() {
        return -gamepad.left_stick_y;
    }

    /**
     * @return the y-value on the right analog stick
     */
    public double getRightY() {
        return gamepad.right_stick_y;
    }

    /**
     * @return the x-value on the left analog stick
     */
    public double getLeftX() {
        return gamepad.left_stick_x;
    }

    /**
     * @return the x-value on the right analog stick
     */
    public double getRightX() {
        return gamepad.right_stick_x;
    }


    /**
     * @return the y-value on the left analog stick using the defined exponential ramps (CUSTOM, MADE BY ME)
     */
    public double getLeftYExp() {
        double rawVal = -gamepad.left_stick_y;
        if(rawVal<rampStart){
            return rawVal;
        }
        return Math.exp(rampBVal*(rawVal-rampStart))-(1-rampStart);
    }

    /**
     * @return the y-value on the right analog stick using the defined exponential ramps (CUSTOM, MADE BY ME)
     */
    public double getRightYExp() {
        double rawVal = gamepad.right_stick_y;
        if(rawVal<rampStart){
            return rawVal;
        }
        return Math.exp(rampBVal*(rawVal-rampStart))-(1-rampStart);
    }

    /**
     * @return the x-value on the left analog stick using the defined exponential ramps (CUSTOM, MADE BY ME)
     */
    public double getLeftXExp() {
        double rawVal = gamepad.left_stick_x;
        if(rawVal<rampStart){
            return rawVal;
        }
        return Math.exp(rampBVal*(rawVal-rampStart))-(1-rampStart);
    }

    /**
     * @return the x-value on the right analog stick using the defined exponential ramps (CUSTOM, MADE BY ME)
     */
    public double getRightXExp() {
        double rawVal = gamepad.left_stick_x;
        if(rawVal<rampStart){
            return rawVal;
        }
        return Math.exp(rampBVal*(rawVal-rampStart))-(1-rampStart);
    }


    /**
     * Returns if the button was just pressed
     *
     * @param button the desired button to read from
     * @return if the button was just pressed
     */
    public boolean wasJustPressed(Button button) {
        return buttonReaders.get(button).wasJustPressed();
    }

    /**
     * Returns if the button was just released
     *
     * @param button the desired button to read from
     * @return if the button was just released
     */
    public boolean wasJustReleased(Button button) {
        return buttonReaders.get(button).wasJustReleased();
    }

    /**
     * Updates the value for each {@link ButtonReader}.
     * Call this once in your loop.
     */
    public void readButtons() {
        for (Button button : buttons) {
            buttonReaders.get(button).readValue();
        }
    }

    /**
     * Returns if the button is down
     *
     * @param button the desired button to read from
     * @return if the button is down
     */
    public boolean isDown(Button button) {
        return buttonReaders.get(button).isDown();
    }

    /**
     * Returns if the button's state has just changed
     *
     * @param button the desired button to read from
     * @return if the button's state has just changed
     */
    public boolean stateJustChanged(Button button) {
        return buttonReaders.get(button).stateJustChanged();
    }

}
