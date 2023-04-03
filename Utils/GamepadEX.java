package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;
import java.util.Arrays;

public class GamepadEX {

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

    private Gamepad rawGp;

    private double thresh;

    private Thread updateThread;

    public GamepadEX(Gamepad gp){
        this(gp,0.125);
    }

    public GamepadEX(Gamepad gp, double activityThreshold){
        thresh = activityThreshold;
        updateThread = new Thread(() -> {

        });
        updateThread.start();
    }



}
