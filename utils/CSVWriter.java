package org.firstinspires.ftc.teamcode.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

//TODO: Untested, but based on a previous program we utilized
public class CSVWriter {

    private final String BASE_FOLDER_NAME = "FIRST";
    private Writer fileWriter;
    private String line;
    private long startTime;

    private boolean paused=false;

    public void start(String filename){
        startTime = System.nanoTime();
        String directoryPath = Environment.getExternalStorageDirectory().getPath()+"/"+BASE_FOLDER_NAME;
        File dir = new File(directoryPath);
        dir.mkdir();
        try{
            fileWriter = new FileWriter(directoryPath+"/"+filename+".csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        try{
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause(){
        paused=true;
        startTime=System.nanoTime()-startTime;
    }

    public void resume(){
        paused=false;
    }


    public void update() {
        if (paused) return;
        try {
            long timeDifference = System.nanoTime()-startTime;
            line = timeDifference/1E9+","+line;
            fileWriter.write(line+"\n");
            line = "";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void addData(String data) {
        if (paused){
            return;
        }
        if (!line.equals("")) {
            line += ",";
        }
        line += data;
    }
    public void addData(Object data) {
        addData(data.toString());
    }
    public void addData(boolean data) {
        addData(String.valueOf(data));
    }
    public void addData(byte data) {
        addData(String.valueOf(data));
    }
    public void addData(char data) {
        addData(String.valueOf(data));
    }
    public void addData(short data) {
        addData(String.valueOf(data));
    }
    public void addData(int data) {
        addData(String.valueOf(data));
    }
    public void addData(long data) {
        addData(String.valueOf(data));
    }
    public void addData(float data) {
        addData(String.valueOf(data));
    }
    public void addData(double data) {
        addData(String.valueOf(data));
    }

}
