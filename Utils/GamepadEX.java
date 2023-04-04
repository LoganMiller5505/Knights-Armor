package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

//TODO: Figure out how JavaDoc formatting works and add better comments! (Oops)
public class GamepadEX {

    //"Maps" for each value's place in the button array list
    public static final int a = 0;
    public static final int b = 1;

    //Array list of all pressable buttons
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

    //FTC's Gamepad Object
    private Gamepad rawGp;

    //Threshold for Float values of gamepad buttons to be considered "active"
    private double thresh;

    //Thread for updating Gamepad values
    //TODO: Have this thread stop when isStopRequested. Will probably need to pass/access "opMode" in this class (a bit of a class organizational pain)
    private Thread updateThread;

    //Default constructor with standard activityThreshold value
    public GamepadEX(Gamepad gp){
        this(gp,0.125);
    }

    //Big messy constructor with fun time thread :D much better optimized than before but (likely) may be able to be improved still
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

    public boolean isPress(String s){
        for(ButtonEX b: buttons){
            if(b.getName().equals(s)){
                return b.isPress();
            }
        }
        return false;
    }

    public boolean isHeld(String s){
        for(ButtonEX b: buttons){
            if(b.getName().equals(s)){
                return b.isHeld();
            }
        }
        return false;
    }

    public boolean isRelease(String s){
        for(ButtonEX b: buttons){
            if(b.getName().equals(s)){
                return b.isRelease();
            }
        }
        return false;
    }

    public void pauseInputListening() throws InterruptedException {
        updateThread.wait();
    }

    public void resumeInputListening(){
        updateThread.notifyAll();
    }





}
