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

        line_runner = VisionRunner(0, 210, 200, 0.003, 0.003, 0.005)
        line_runner.start()

    }


    override fun teleopPeriodic() {
        //vision test
        synchronized(this.line_runner) {
            print("X: ${this.line_runner.getDX()}, Y: ${this.line_runner.getDY()}, DT: ${this.line_runner.getDTheta()}\n");
        }


        //run drive train
        this.drivetrain.driveCartesian(-this.controller.getY(GenericHID.Hand.kLeft) * 1.0, -this.controller.getX(GenericHID.Hand.kLeft) *1.0, this.controller.getX(GenericHID.Hand.kRight) * 1.0)
        //run elevator
        this.elevatorMotor.speed = this.controller.getY(GenericHID.Hand.kRight) * 1.0;
        //this.drivetrain.driveCartesian(1.0, 0.0, 0.0);

        //run intake
        when {
            this.controller.getBumper(GenericHID.Hand.kLeft) -> {
                this.grabMotor1.speed = -1.0
                this.grabMotor2.speed = 1.0
            }
            this.controller.getBumper(GenericHID.Hand.kRight) -> {
                this.grabMotor1.speed = 1.0
                this.grabMotor2.speed = -1.0
            }
            else -> {
                this.grabMotor1.speed = 0.0
                this.grabMotor2.speed = 0.0
            }
    }


    }

}
