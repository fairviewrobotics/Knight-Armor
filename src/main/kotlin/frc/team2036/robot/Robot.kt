package frc.team2036.robot

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.PWMTalonSRX
import frc.team2036.robot.knightarmor.KnightBot
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.drive.MecanumDrive

import frc.team2036.robot.vision.linesensing.*;

class Robot : KnightBot() {

    lateinit var controller: XboxController
    lateinit var drivetrain: MecanumDrive
    lateinit var elevatorMotor: PWMTalonSRX
    lateinit var grabMotor1: PWMTalonSRX
    lateinit var grabMotor2: PWMTalonSRX

    lateinit var line_runner: VisionRunner

    override fun robotInit() {
        this.controller = XboxController(1)
        this.drivetrain = MecanumDrive(PWMTalonSRX(0), PWMTalonSRX(1), PWMTalonSRX(2), PWMTalonSRX(3))
        this.elevatorMotor = PWMTalonSRX(4)
        this.grabMotor1 = PWMTalonSRX(5)
        this.grabMotor2 = PWMTalonSRX(6)

        line_runner = VisionRunner(0, 120, 150, 0.007, 0.007, 0.005, 0.2, 0.2, 0.1, 45, 45, 8)
        line_runner.line_sensing.algorithm.setDownscaleSize(240, 180)
        line_runner.start()

    }


    override fun teleopPeriodic() {
        //vision test
        synchronized(this.line_runner) {
            print("X: ${this.line_runner.getDX()}, Y: ${this.line_runner.getDY()}, DT: ${this.line_runner.getDTheta()}\n");
        }


        //run drive train
        if(this.controller.getBButton()){
            var dx: Double = 0.0
            var dy: Double = 0.0
            var dt: Double = 0.0

            synchronized(this.line_runner) {
                if(this.controller.getXButton()){
                    dx = -this.line_runner.getDX()
                }
                if(this.controller.getYButton()){
                    dy = this.line_runner.getDY()
                }
                if(this.controller.getAButton()){
                    dt = this.line_runner.getDTheta()
                }

                //run alignment
                this.drivetrain.driveCartesian(dx, dy, dt);
                    //this.drivetrain.driveCartesian(0.0, 0.0, this.line_runner.getDTheta());
                }
        } else {
            //run teleop
            this.drivetrain.driveCartesian(this.controller.getX(GenericHID.Hand.kLeft), -this.controller.getY(GenericHID.Hand.kLeft), this.controller.getX(GenericHID.Hand.kRight))
        }
        //run elevator
        this.elevatorMotor.speed = this.controller.getY(GenericHID.Hand.kRight) * 1.0;
        //this.drivetrain.driveCartesian(1.0, 0.0, 0.0);

        //run intake
        when {
            this.controller.getBumper(GenericHID.Hand.kLeft) -> {
                this.grabMotor1.speed = -0.2
                this.grabMotor2.speed = 0.2
            }
            this.controller.getBumper(GenericHID.Hand.kRight) -> {
                this.grabMotor1.speed = 0.2
                this.grabMotor2.speed = -0.2
            }
            else -> {
                this.grabMotor1.speed = 0.0
                this.grabMotor2.speed = 0.0
            }
    }


    }

}
