package org.firstinspires.ftc.teamcode.Sensors;

import com.qualcomm.hardware.bosch.BNO055IMU;

public class IMUEX {

    private BNO055IMU rawIMU;

    public IMUEX(BNO055IMU imu){
        rawIMU=imu;
    }

}
