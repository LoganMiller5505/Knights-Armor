package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Enhanced GamePad functionality.
 * Utilizes multi-threading to update the press, held, and release boolean states of all
 * TODO: Have the thread stop when isStopRequested
 * TODO: Add functionality for retrieving (and modifying with some reference function) floating point number returns
 */
public class GamepadEX {

    /**
     * Array list of all pressable buttons
     */
    private final ArrayList<ButtonEX> buttons = new ArrayList<> (Arrays.asList(
            new ButtonEX("a", true),
            new ButtonEX("b", true),
            new ButtonEX("dpad_down", false),
            new ButtonEX("dpad_left", false),
            new ButtonEX("dpad_right", false),
            new ButtonEX("dpad_up", false),
            new ButtonEX("left_bumper", true),
            new ButtonEX("left_stick_button", true),
            new ButtonEX("left_stick_x", false),
            new ButtonEX("left_stick_y", false),
            new ButtonEX("left_trigger", false),
            new ButtonEX("right_bumper", true),
            new ButtonEX("right_stick_button", true),
            new ButtonEX("right_stick_x", false),
            new ButtonEX("right_stick_y", false),
            new ButtonEX("right_trigger", false),
            new ButtonEX("x", true),
            new ButtonEX("y", true)
    ));

    /**
     * FTC's default gamepad object
     */
    private Gamepad rawGp;

    /**
     * Threshold for Float values of gamepad buttons to be considered "active"
     */
    private double thresh;

    /**
     * Thread for updating Gamepad values
     */
    private Thread updateThread;

    /**
     * Default case constructor, with standard activityThreshold value being 0.125
     * @param gp FTC Gamepad Object
     */
    public GamepadEX(Gamepad gp){
        this(gp,0.125);
    }

    /**
     * Loaded constructor, with overridable activityThreshold value
     * @param gp FTC Gamepad Object
     * @param activityThreshold Custom threshold to consider a float input "active"
     */
    public GamepadEX(Gamepad gp, double activityThreshold){

        this.rawGp=gp;
        this.thresh = activityThreshold;

        updateThread = new Thread(() -> {
            while(true){
                for(ButtonEX b: buttons){

                    Field state;
                    boolean engaged;

                    try {
                        state = gp.getClass().getDeclaredField(b.getName());
                    } catch (NoSuchFieldException e) { //NOTE: THIS SHOULD NEVER HAPPEN since we are working with the limited, final ArrayList "buttons" here unless you manually come in and mess it up
                        throw new RuntimeException(e);
                    }

                    if(b.isBool()){
                        engaged = state.equals(true);
                    }
                    else{
                        engaged = Float.parseFloat(state.toString()) > thresh;
                    }

                    b.setPress(engaged);

                    if(b.isPress()==b.getPrevState()){
                        b.setHeld(true);
                    }
                    else if(b.isPress()==false){
                        b.setRelease(true);
                    }

                    b.setPrevState(engaged);

                }

            }
        });
        updateThread.start();
    }

    /**
     * Check whether a specific button ID is pressed or not
     * @param ID ID of the button
     * @return Boolean of if the button has been pressed (and is NOT being held)
     */
    public boolean isPress(String ID){
        for(ButtonEX b: buttons){
            if(b.getName().equals(ID)){
                return b.isPress();
            }
        }
        return false;
    }

    /**
     * Check whether a specific button ID is held or not
     * @param ID ID of the button
     * @return Boolean of if the button is being held (irrespective of active/inactive state)
     */
    public boolean isHeld(String ID){
        for(ButtonEX b: buttons){
            if(b.getName().equals(ID)){
                return b.isHeld();
            }
        }
        return false;
    }

    /**
     * Check whether a specific button ID is released or not
     * @param ID ID of the button
     * @return Boolean of if the button has been released (and is NOT being held)
     */
    public boolean isRelease(String ID){
        for(ButtonEX b: buttons){
            if(b.getName().equals(ID)){
                return b.isRelease();
            }
        }
        return false;
    }

    /**
     * Pause the Gamepad input listening thread
     * @throws InterruptedException This should never occur unless there is a serious problem with the thread that can only come about by manually screwing with it's initialization, but Java wants it here because Java
     */
    public void pauseInputListening() throws InterruptedException {
        updateThread.wait();
    }

    /**
     * Resume the Gamepad input listening thread
     */
    public void resumeInputListening(){
        updateThread.notifyAll();
    }





}
