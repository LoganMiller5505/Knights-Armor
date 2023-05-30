package org.firstinspires.ftc.teamcode.vision;

public class Resolution {

    private final int width;
    private final int height;

    public Resolution(int width, int height){
        this.width=width;
        this.height=height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getArea(){
        return width*height;
    }

}
