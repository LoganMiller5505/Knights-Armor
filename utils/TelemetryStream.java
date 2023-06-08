package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

/**
 * This class is meant to be used as an alternative to the standard Telemetry usage.
 *
 * This is meant to solve issues of managing many different sources of Telemetry, each with a different title and description-- especially in removing telemetry once it is no longer desired.
 *
 * add() functions similarly to the default telemetry.add() and must be updated with a title and description whenever new data needs to be displayed
 *
 * remove() takes in the title of any telemetry field and removes it as well as the associated description
 *
 * update() functions similarly to the default telemetry.update() and is meant to be called at the very end of each loop to display everything
 *
 * This object also handles formatting, with a colon between each title and description as well as a new line between data points
 *
 * TODO: Add some form of FTC Dashboard synchronization (or even in a separate class) to further distinguish it from the generic telemetry object
 */
public class TelemetryStream {

    //Passed in Telemetry object, necessary to update info to the driver controller app
    private static Telemetry telemetry;

    //ArrayList holding all telemetry descriptions in alternating order (0 = title a, 1 = description a, 2 = title b, 3 = description b, etc.)
    private static ArrayList<String> telemToDisplay = new ArrayList<>();

    /**
     * MUST be called before the TelemetryStream can be used. Think of this as the contructor for the object (even though it is static)
     */
    public static void init(Telemetry telemetry){
        TelemetryStream.telemetry = telemetry;
    }

    /**
     * Adds a paired title and description to be displayed by the TelemetryStream
     * @param title first part of the telemetry (usually, the descriptor for the data)
     * @param desc second part of the telemetry (usually, the data itself)
     */
    public static void add(String title, String desc){
        for(int i = 0; i<telemToDisplay.size(); i++){
            if(telemToDisplay.get(i).equals(title)){
                telemToDisplay.set(i+1, desc);
                return;
            }
        }
        telemToDisplay.add(title);
        telemToDisplay.add(desc);
    }

    /**
     * Removes a paired title and description from the TelemetryStream
     * @param title title of the telemetry to be removed
     */
    public static void remove(String title){
        int idx = 0;
        for(int i = 0; i<telemToDisplay.size(); i++){
            if(telemToDisplay.get(i).equals(title)){
                telemToDisplay.remove(i);
                telemToDisplay.remove(i);
                return;
            }
        }
    }

    /**
     * Updates the telemetry to display on the robot controller
     */
    public static void update(){
        for(int i = 0; i<telemToDisplay.size(); i+=2){
            telemetry.addData(telemToDisplay.get(i) + ": ", telemToDisplay.get(i+1) + "/n");
        }
        telemetry.update();
    }

}