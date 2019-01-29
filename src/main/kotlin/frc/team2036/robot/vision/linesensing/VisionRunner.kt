package frc.team2036.robot.vision.linesensing;


/* VisionRunner: run the line sensing algorithm in a different thread, and calculate needed x, y, and theta movement
    theta target assumed to be zero*/

class VisionRunner(val camera_index: Int, var x_target: Int, var y_target: Int, var x_cof: Double, var y_cof: Double, var theta_cof: Double, val x_max: Double, val y_max: Double, val theta_max: Double, val x_dead: Int, val y_dead: Int, val theta_dead: Int): Thread() {

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

            var dx: Double;

            dx = (this.x_target - this.line_sensing.algorithm.centroid_x)

            if(Math.abs(dx) > this.x_dead){
                return Math.signum(dx) * this.x_max;
            }

            return 0.0;

        }
    }

    public fun getDY(): Double {
        synchronized(this.line_sensing){
            var dy: Double;

            dy = (this.y_target - this.line_sensing.algorithm.centroid_y)

            if(Math.abs(dy) > this.y_dead){
                return Math.signum(dy) * this.y_max;
            }

            return 0.0;
        }
    }

    public fun getDTheta(): Double {
        synchronized(this.line_sensing){
            var real_angle: Double = this.line_sensing.algorithm.angle;
            if(real_angle > 90.0){
                real_angle -= 180.0;
            }

            if(Math.abs(real_angle) > this.theta_dead){
                return Math.signum(real_angle) * this.theta_max;
            }

            return 0.0
        }
    }

}