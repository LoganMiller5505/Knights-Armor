package org.firstinspires.ftc.teamcode.Utils;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;

public class TelemetryStream {

    private static Telemetry telemetry;

    private static ArrayList<String> telemToDisplay = new ArrayList<>();

    public static void initTelemetryStream(Telemetry telemetry){
        TelemetryStream.telemetry = telemetry;
    }

    public static void updateTelemetry(String title, String desc){
        for(int i = 0; i<telemToDisplay.size(); i++){
            if(telemToDisplay.get(i).equals(title)){
                telemToDisplay.set(i+1, desc);
                return;
            }
        }

    }

    public static void removeTelemetry(String title){

    }



}
