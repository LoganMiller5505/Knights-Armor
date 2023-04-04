package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;
import java.util.Arrays;

public class GamepadEX {

    //"Maps" for each value's place in the button array list
    public static final int a = 0;
    public static final int b = 1;

    //Array list of all pressable buttons
    public ArrayList<ButtonEX> buttons = new ArrayList<ButtonEX> (Arrays.asList(
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

    //FTC's Gamepad Object
    private Gamepad rawGp;

    //Threshold for Float values of gamepad buttons to be considered "active"
    private double thresh;

    //Thread for updating Gamepad values
    //TODO: Have this thread stop when isStopRequested. Will probably need to pass/access "opMode" in this class
    private Thread updateThread;

    //Default constructor with standard activityThreshold value
    public GamepadEX(Gamepad gp){
        this(gp,0.125);
    }

    //Big messy consturctor :D definitely needs a review and test to see A) If it works, and B) How it can be optimized (because this is NOT optimized right now.)
    public GamepadEX(Gamepad gp, double activityThreshold){
        thresh = activityThreshold;
        updateThread = new Thread(() -> {
            while(true){
                for(ButtonEX button: buttons){
                    if(button.isBool()){
                        try {
                            boolean b = false;
                            b = rawGp.getClass().getDeclaredField(button.getName()).getBoolean(b); //TODO: Does this actually work? I am using java in ways I never have before and I am lost.
                            button.setPress(b);

                            if(button.isPress()==button.getPrevState()){
                                button.setHeld(true);
                            }

                            button.setPrevState(b);

                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        try {
                            float f = 0.0f;
                            boolean b = rawGp.getClass().getDeclaredField(button.getName()).getFloat(f) > activityThreshold; //TODO: Does this actually work? I am using java in ways I never have before and I am lost.
                            button.setPress(b);

                            if(button.isPress()==button.getPrevState()){
                                button.setHeld(true);
                            }

                            button.setPrevState(b);

                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
        updateThread.start();
    }





}
