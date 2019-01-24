package frc.team2036.robot

import frc.team2036.robot.vision.linesensing.*;

/* VisionRunner: run the line sensing algorithm in a different thread, and calculate needed x, y, and theta movement
    theta target assumed to be zero*/

class VisionRunner(val camera_index: Int, var x_target: Int, var y_target: Int, var x_cof: Double, var y_cof: Double, var theta_cof: Double): Thread() {

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
            return this.x_cof* (this.line_sensing.algorithm.centroid_x - this.x_target);
        }
    }

    public fun getDY(): Double {
        synchronized(this.line_sensing){
            return this.y_cof* (this.line_sensing.algorithm.centroid_y - this.y_target);
        }
    }

    public fun getDTheta(): Double {
        synchronized(this.line_sensing){
            var real_angle: Double = this.line_sensing.algorithm.angle;
            if(real_angle > 90.0){
                real_angle -= 180.0;
            }

            return real_angle * this.theta_cof;
        }
    }

}