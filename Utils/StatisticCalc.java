package org.firstinspires.ftc.teamcode.Utils;

import java.util.ArrayList;

public class StatisticCalc {

    public static double mean(int[] nums){
        int sum = 0;
        for(int i: nums){
            sum+=i;
        }
        return sum/nums.length;
    }

    public static double mean(double[] nums){
        double sum = 0;
        for(double i: nums){
            sum+=i;
        }
        return sum/nums.length;
    }

    public static double median(int[] nums){
        double median = 0;
        if (nums.length % 2 == 0){
            median = ((double)nums[nums.length/2] + (double)nums[nums.length/2 - 1])/2;
        }
        else{
            median = (double) nums[nums.length/2];
        }
        return median;
    }

    public static double median(double[] nums){
        double median = 0;
        if (nums.length % 2 == 0){
            median = (nums[nums.length/2] + nums[nums.length/2 - 1])/2;
        }
        else{
            median = nums[nums.length/2];
        }
        return median;
    }

    public static double standDev(int[] nums){
        int sum = 0;
        for(int i: nums){
            sum+=i;
        }
        double mean = sum/nums.length;
        double standDev = 0;
        for(int i: nums){
            standDev+=Math.pow(i-mean,2);
        }
        return Math.sqrt(standDev/nums.length);
    }

    public static double standDev(double[] nums){
        double sum = 0;
        for(double i: nums){
            sum+=i;
        }
        double mean = sum/nums.length;
        double standDev = 0;
        for(double i: nums){
            standDev+=Math.pow(i-mean,2);
        }
        return Math.sqrt(standDev/nums.length);
    }

}
