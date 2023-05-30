package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

public class TelemetryStream {

    private static Telemetry telemetry;

    private static ArrayList<String> telemToDisplay = new ArrayList<>();

    public static void init(Telemetry telemetry){
        TelemetryStream.telemetry = telemetry;
    }

    public static void add(String title, String desc){
        for(int i = 0; i<telemToDisplay.size(); i++){
            if(telemToDisplay.get(i).equals(title)){
                telemToDisplay.set(i+1, desc);
                return;
            }
        }

    }

    public static void remove(String title){
        int idx = 0;
        for(int i = 0; i<telemToDisplay.size(); i++){
            if(telemToDisplay.get(i).equals(title)){
                telemToDisplay.remove(i);
                telemToDisplay.remove(i);
                break;
            }
        }
    }

    public static void update(){
        for(int i = 0; i<telemToDisplay.size(); i+=2){
            telemetry.addData(telemToDisplay.get(i) + ": ", telemToDisplay.get(i+1));
        }
        telemetry.update();
    }



}
