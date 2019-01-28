package frc.team2036.robot

import frc.team2036.robot.vision.linesensing.*;

/* VisionRunner: run the line sensing algorithm in a different thread, and calculate needed x, y, and theta movement
    theta target assumed to be zero*/

class VisionRunner(val camera_index: Int, var x_target: Int, var y_target: Int, var x_cof: Double, var y_cof: Double, var theta_cof: Double, val x_max: Double, val y_max: Double, val theta_max: Double): Thread() {

    public var line_sensing: LineSense;

    init {
        line_sensing = LineSense()
        line_sensing.openCamera(camera_index)
    }

    public override fun run(){
        while(true){
            this.line_sensing.runAlgorithm();
        }
    }

    //get dx, dy, and dtheta

    public fun getDX(): Double {
        synchronized(this.line_sensing){
            return maxOf(minOf(this.x_cof* (this.x_target - this.line_sensing.algorithm.centroid_x), this.x_max), -this.x_max)

        }
    }

    public fun getDY(): Double {
        synchronized(this.line_sensing){
            return maxOf(minOf(this.y_cof* (this.y_target - this.line_sensing.algorithm.centroid_y), this.y_max), -this.y_max)
        }
    }

    public fun getDTheta(): Double {
        synchronized(this.line_sensing){
            var real_angle: Double = this.line_sensing.algorithm.angle;
            if(real_angle > 90.0){
                real_angle -= 180.0;
            }

            return maxOf(minOf(real_angle * this.theta_cof, this.theta_max), - this.theta_max)
        }
    }

}