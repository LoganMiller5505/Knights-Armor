package org.firstinspires.ftc.teamcode.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Write data to a CSV file so that it can be accessed and manipulated to produce
 * graphs and other helpful testing content.
 *
 * TODO: Untested, but based on a previous program we utilized
 * TODO: Integrate CSV with telemetry maybe?
 */
public class CSVWriter {

    private final String BASE_FOLDER_NAME = "FIRST";
    private Writer fileWriter;
    private String line;
    private long startTime;

    private boolean paused=false;

    /**
     * Must be called first to begin the file writing process
     * @param filename desired name of the csv file
     */
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

    /**
     * Must be called last to completely end the file writing process
     */
    public void stop(){
        try{
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * May be called to pause file writing, saving the time it was paused as well
     */
    public void pause(){
        paused=true;
        startTime=System.nanoTime()-startTime;
    }

    /**
     * Resumes the file writing from the last time signature it was paused
     */
    public void resume(){
        paused=false;
    }

    /**
     * Update the contents of the CSV file with one line. Must be called at the end of each "loop"
     */
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

    /**
     * Add a single datapoint, comma separated value
     * (Note, there are also many overloaded functions for different data types)
     * @param data data for the value
     */
    public void addData(String data) {
        if (paused){
            return;
        }
        if (!line.equals("")) {
            line += ",";
        }
        line += data;
    }
    /**
     * Add a single datapoint, comma separated value
     * (Note, there are also many overloaded functions for different data types)
     * @param data data for the value
     */
    public void addData(Object data) {
        addData(data.toString());
    }
    /**
     * Add a single datapoint, comma separated value
     * (Note, there are also many overloaded functions for different data types)
     * @param data data for the value
     */
    public void addData(boolean data) {
        addData(String.valueOf(data));
    }
    /**
     * Add a single datapoint, comma separated value
     * (Note, there are also many overloaded functions for different data types)
     * @param data data for the value
     */
    public void addData(byte data) {
        addData(String.valueOf(data));
    }
    /**
     * Add a single datapoint, comma separated value
     * (Note, there are also many overloaded functions for different data types)
     * @param data data for the value
     */
    public void addData(char data) {
        addData(String.valueOf(data));
    }
    /**
     * Add a single datapoint, comma separated value
     * (Note, there are also many overloaded functions for different data types)
     * @param data data for the value
     */
    public void addData(short data) {
        addData(String.valueOf(data));
    }
    /**
     * Add a single datapoint, comma separated value
     * (Note, there are also many overloaded functions for different data types)
     * @param data data for the value
     */
    public void addData(int data) {
        addData(String.valueOf(data));
    }
    /**
     * Add a single datapoint, comma separated value
     * (Note, there are also many overloaded functions for different data types)
     * @param data data for the value
     */
    public void addData(long data) {
        addData(String.valueOf(data));
    }
    /**
     * Add a single datapoint, comma separated value
     * (Note, there are also many overloaded functions for different data types)
     * @param data data for the value
     */
    public void addData(float data) {
        addData(String.valueOf(data));
    }
    /**
     * Add a single datapoint, comma separated value
     * (Note, there are also many overloaded functions for different data types)
     * @param data data for the value
     */
    public void addData(double data) {
        addData(String.valueOf(data));
    }

}
