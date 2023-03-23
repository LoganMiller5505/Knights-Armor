package org.firstinspires.ftc.teamcode.Drive;

public class MotionProfiling {

    public static double motion_profile(double max_acceleration, double max_velocity, double distance, double current_dt) {
        /*
        Return the current reference position based on the given motion profile times, maximum acceleration, velocity, and current time.
        */

        // calculate the time it takes to accelerate to max velocity
        double acceleration_dt = max_velocity / max_acceleration;

        // If we can't accelerate to max velocity in the given distance, we'll accelerate as much as possible
        double halfway_distance = distance / 2;
        double acceleration_distance = 0.5 * max_acceleration * acceleration_dt * 2;

            if (acceleration_distance > halfway_distance) {
                 acceleration_dt = Math.sqrt(halfway_distance / (0.5 * max_acceleration));
             }

        acceleration_distance = 0.5 * max_acceleration * acceleration_dt * 2;

        // recalculate max velocity based on the time we have to accelerate and decelerate
        max_velocity = max_acceleration * acceleration_dt;;

        // we decelerate at the same rate as we accelerate
        double deacceleration_dt = acceleration_dt;

        // calculate the time that we're at max velocity
        double cruise_distance = distance - 2 * acceleration_distance;
        double cruise_dt = cruise_distance / max_velocity;
        double deacceleration_time = acceleration_dt + cruise_dt;

        // check if we're still in the motion profile
        double entire_dt = acceleration_dt + cruise_dt + deacceleration_dt;
            if (current_dt > entire_dt) {
                return distance;
            }

            // if we're accelerating
            if (current_dt < acceleration_dt) {
                // use the kinematic equation for acceleration
                return 0.5 * max_acceleration * current_dt * 2;
            }

            // if we're cruising
            else if (current_dt < deacceleration_time) {
                acceleration_distance = 0.5 * max_acceleration * acceleration_dt * 2;
                double cruise_current_dt = current_dt - acceleration_dt;

                // use the kinematic equation for constant velocity
                return acceleration_distance + max_velocity * cruise_current_dt;
            }

            // if we're decelerating
            else {
                acceleration_distance = 0.5 * max_acceleration * acceleration_dt * 2;
                cruise_distance = max_velocity * cruise_dt;
                deacceleration_time = current_dt - deacceleration_time;

                // use the kinematic equations to calculate the instantaneous desired position
                return acceleration_distance + cruise_distance + max_velocity * deacceleration_time - 0.5 * max_acceleration * deacceleration_time * 2;
             }
    }

    /*
    while True:
   current_velocity = get_current_velocity()
   current_time = get_current_time()

   direction_multiplier = 1

   if position_error < 0:
       direction_multiplier = -1

   # if maximum speed has not been reached
   if MAXIMUM_SPEED > abs(current_velocity):
       output_velocity = current_velocity + direction_multiplier * MAX_ACCELERATION * (current_time - previous_time)
       output_acceleration = MAX_ACCELERATION

   #if maximum speed has been reached, stay there for now
   else:
       outputVelocity = MAXIMUM_SPEED
       outputAcceleration = 0

   #if we are close enough to the object to begin slowing down
   if position_error <= (output_velocity * output_velocity) / (2 * MAX_ACCELERATION)):
       output_velocity = current_velocity - direction_multiplier * MAX_ACCELERATION * (current_time - previous_time)
       output_acceleration = -MAX_ACCELERATION

   previous_time = current_time
     */

}
