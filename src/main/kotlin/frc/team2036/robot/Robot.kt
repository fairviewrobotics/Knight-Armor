package frc.team2036.robot

import edu.wpi.first.wpilibj.Joystick
// import edu.wpi.first.wpilibj.TalonSRX
import edu.wpi.first.wpilibj.Spark
import frc.team2036.robot.knightarmor.KnightBot
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.drive.MecanumDrive
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj.Preferences
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.CounterBase.EncodingType

import edu.wpi.first.wpilibj.Talon

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;

// OPENCV
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;


import org.opencv.core.*;
import org.opencv.core.Core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;

class Robot : KnightBot() {
    //left joystick
    lateinit var controller0: Joystick
    //right joystick
    lateinit var controller1: Joystick
    //drivetrain
    lateinit var drivetrain: MecanumDrive

    //back elevator
    lateinit var rearElevatorMotor: Spark
    //main elevator
    lateinit var elevatorMotor: WPI_TalonSRX
    //ball intak
    lateinit var grabMotor1: WPI_VictorSPX
    lateinit var grabMotor2: Talon
    //hatch intake
    lateinit var hatchIntake: Talon


    override fun robotInit() {

        controller0 = Joystick(0)
        controller1 = Joystick(1)
        drivetrain = MecanumDrive(WPI_TalonSRX(1), WPI_TalonSRX(2), WPI_TalonSRX(3), WPI_TalonSRX(4))

        rearElevatorMotor = Spark(8)
        elevatorMotor = WPI_TalonSRX(20)

        grabMotor1 = WPI_VictorSPX(11)
        grabMotor2 = Talon(7)
        hatchIntake = Talon(9)
    }

    /* run repeatedly during the teleop period */
    override fun teleopPeriodic(){
        drivetrain.driveCartesian(-controller0.getX(), -controller0.getY(), controller1.getX())
    }

    /* run repeatedly during auto period */
    override fun autonomousPeriodic(){

    }


}
