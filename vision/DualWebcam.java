package org.firstinspires.ftc.teamcode.vision;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

/**
 * Class for easy implementation of a stereo-processing, dual webcam system
 */
public class DualWebcam {

    private int height;
    private int width;

    private double FOV;

    private double leftCamTilt;
    private double rightCamTilt;

    private double a;
    private double b;

    private OpenCvCamera leftCam;
    private OpenCvCamera rightCam;

    private ObjectDetectionPipeline leftPipeline;
    private ObjectDetectionPipeline rightPipeline;

    private OpenCvCameraRotation leftRotation = OpenCvCameraRotation.UPRIGHT;
    private OpenCvCameraRotation rightRotation = OpenCvCameraRotation.UPRIGHT;

    //TODO: CONSTRUCTOR CHAIN THINGS THAT COULD BE CONSTRUCTOR CHAINED
    /**
     * A constructor containing necessary constants for a dual webcam system
     * @param res The resolution of both cameras
     * @param camFOV FOV of both cameras
     * @param camTilt Tilt from the normal line of the front panel (in degrees)
     * @param camHorizontalDistance Distance between the two webcams parallel to the front panel
     * @param camVerticalDistance Distance of both webcams from the center of the robot (behind the center point is positive)
     * @param leftCam OpenCvCamera for the left-side camera
     * @param rightCam OpenCvCamera for the right-side camera
     * @param leftPipeline ObjectDetectionPipeline for the left-side camera
     * @param rightPipeline ObjectDetectionPipeline for the right-side camera (MUST be a different pipeline from leftPipeline)
     * @param leftCamUpsideDown Whether the left camera is upside down
     * @param rightCamUpsideDown Whether the right camera is upside down
     */
    public DualWebcam(Resolution res, double camFOV, double camTilt, double camHorizontalDistance, double camVerticalDistance, OpenCvCamera leftCam, OpenCvCamera rightCam, ObjectDetectionPipeline leftPipeline, ObjectDetectionPipeline rightPipeline, boolean leftCamUpsideDown, boolean rightCamUpsideDown){
        this.height=res.getHeight();
        this.width=res.getWidth();

        this.FOV=camFOV;

        this.leftCamTilt=-camTilt;
        this.rightCamTilt=camTilt;

        this.a = camHorizontalDistance/2;
        this.b = camVerticalDistance;

        this.leftCam=leftCam;
        this.rightCam=rightCam;

        this.leftPipeline=leftPipeline;
        this.rightPipeline=rightPipeline;

        if(leftCamUpsideDown){
            this.leftRotation=OpenCvCameraRotation.UPSIDE_DOWN;
        }

        if(rightCamUpsideDown){
            this.rightRotation=OpenCvCameraRotation.UPSIDE_DOWN;
        }
    }

    /**
     * Method to initialize the cameras running the passed Pipelines
     */
    public void initCameras(){
        leftCam.setPipeline(leftPipeline);
        leftCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                leftCam.startStreaming(width,height, leftRotation);

            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        rightCam.setPipeline(rightPipeline);
        rightCam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                rightCam.startStreaming(width,height, rightRotation);

            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

    }


    /**
     * @return The change in angle necessary for the center of the robot to be aligned to the detected object in both cameras (in radians)
     */
    public double findDTheta(){

        if(leftPipeline.returnObjectCenterX() == -1 || rightPipeline.returnObjectCenterX() == -1){
            return -1;
        }

        double thetaLeft = Math.toRadians(90+(FOV/2)+leftCamTilt-((leftPipeline.returnObjectCenterX()*FOV)/width));
        double thetaRight = Math.toRadians(90+(FOV/2)+rightCamTilt-((rightPipeline.returnObjectCenterX()*FOV)/width));

        double u = Math.tan(thetaRight) - Math.tan(thetaLeft);
        double dx = a * (Math.tan(thetaRight) + Math.tan(thetaLeft))/ u;
        double dy = (2 * a) * (Math.tan(thetaRight) * Math.tan(thetaLeft))/ u;

        double rawTheta = Math.atan((dy-b)/dx);

        double dTheta = rawTheta-Math.PI/2;

        if(dTheta<-Math.PI/2){
            dTheta = Math.PI + dTheta;
        }

        return dTheta;

    }

    /**
     * @return The distance from the detected object in both cameras to the center of the robot
     */
    public double findDist(){

        if(leftPipeline.returnObjectCenterX() == -1 || rightPipeline.returnObjectCenterX() == -1){
            return -1;
        }

        double thetaLeft = Math.toRadians(90+(FOV/2)+leftCamTilt-((leftPipeline.returnObjectCenterX()*FOV)/width));
        double thetaRight = Math.toRadians(90+(FOV/2)+rightCamTilt-((rightPipeline.returnObjectCenterX()*FOV)/width));

        double u = Math.tan(thetaRight) - Math.tan(thetaLeft);
        double dx = a * (Math.tan(thetaRight) + Math.tan(thetaLeft))/ u;
        double dy = (2 * a) * (Math.tan(thetaRight) * Math.tan(thetaLeft))/ u;

        double dist = Math.sqrt(Math.pow(dy-b, 2) + Math.pow(dx, 2));

        return dist;

    }


}
