package org.firstinspires.ftc.teamcode.utils;

/**
 * Various methods to automatically handle calculating certain measures of statistical trends
 */
public class StatisticCalc {

    /**
     * @param nums list of integer datapoints
     * @return mean of those nums
     */
    public static double mean(int[] nums){
        int sum = 0;
        for(int i: nums){
            sum+=i;
        }
        return sum/nums.length;
    }
    /**
     * @param nums list of double datapoints
     * @return mean of those nums
     */
    public static double mean(double[] nums){
        double sum = 0;
        for(double i: nums){
            sum+=i;
        }
        return sum/nums.length;
    }

    /**
     * @param nums list of integer datapoints
     * @return median of those nums
     */
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
    /**
     * @param nums list of double datapoints
     * @return median of those nums
     */
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

    /**
     * @param nums list of integer datapoints
     * @return stand dev of those nums
     */
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
    /**
     * @param nums list of double datapoints
     * @return stand dev of those nums
     */
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
