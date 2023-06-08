package org.firstinspires.ftc.teamcode.vision;

/**
 * Class to store camera resolutions
 */
public class Resolution {

    private final int width;
    private final int height;

    /**
     * Constructor
     * @param width pixel width of the camera
     * @param height pixel height of the camera
     */
    public Resolution(int width, int height){
        this.width=width;
        this.height=height;
    }

    /**
     * @return stored width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return store height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return pixel area of camera view
     */
    public int getArea(){
        return width*height;
    }

}
