package frc.team2036.robot.knightarmor

import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Scheduler

/**
 * Superclass for the entry class for entire robot program
 * Default period for TimedRobot periodic commands is 0.02 seconds, but this can (and shouldn't) be changed
 */
open class KnightBot : TimedRobot() {

    /**
     * What happens during robot initialization
     * Basically replaces constructor
     */
    override fun robotInit() {}

    /**
     * What happens when the robot begins its autonomous period
     */
    override fun autonomousInit() {
        //TODO: make an easy way of registering or making an autonomous command or command group
        KnightScribe.knightLog("Starting autonomous phase!")
        Scheduler.getInstance().removeAll()
    }

    /**
     * What happens every period of autonomous
     * Default interval for periodic calls is 0.02 seconds
     */
    override fun autonomousPeriodic() {
        Scheduler.getInstance().run()
    }

    /**
     * What happens when the robot begins its teleoperated period
     */
    override fun teleopInit() {
        KnightScribe.knightLog("Starting teleoperated phase!")
        Scheduler.getInstance().removeAll()
    }

    /**
     * What happens every period of teleoperated
     * Default interval for periodic calls is 0.02 seconds
     */
    override fun teleopPeriodic() {
        Scheduler.getInstance().run()
    }

    /**
     * What happens when the robot begins its test period
     */
    override fun testInit() {
        KnightScribe.knightLog("Starting test phase!")
        Scheduler.getInstance().removeAll()
    }

    /**
     * What happens every period of test
     * Default interval for test calls is 0.02 seconds
     */
    override fun testPeriodic() {
        Scheduler.getInstance().run()
    }

}
