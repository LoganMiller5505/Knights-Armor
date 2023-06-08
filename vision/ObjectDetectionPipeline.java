package org.firstinspires.ftc.teamcode.vision;

import org.openftc.easyopencv.OpenCvPipeline;

/**
 * Abstract class that features a return center x function. Must be used within the DualWebcam pipeline to ensure it has access to necessary camera information.
 */
public abstract class ObjectDetectionPipeline extends OpenCvPipeline {

    /**
     * NOTE: This function works best if it returns -1 for inconclusive/outlying results
     * @return the center x coordinate of the detected object
     */
    public abstract double returnObjectCenterX();

}
