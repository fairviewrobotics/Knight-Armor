package frc.team2036.robot

import edu.wpi.first.wpilibj.PWMTalonSRX
import frc.team2036.robot.knightarmor.KnightBot
import edu.wpi.first.wpilibj.XboxController

//The driverstation port where the controller is connected
const val CONTROLLER_PORT = 0

/**
 * The main and only class for the DDR bot
 * Is a timed robot which will have some of its methods periodically called
 */
class Robot : KnightBot() {

    //The DDR mat; is of type XboxController, but doesn't really need to be an XboxController
    lateinit var controller: XboxController

    lateinit var talon1: PWMTalonSRX
    lateinit var talon2: PWMTalonSRX

    /**
     * Initializer that sets up the controller and the drivetrain
     */
    override fun robotInit() {
        this.controller = XboxController(CONTROLLER_PORT)
        this.talon1 = PWMTalonSRX(0)
        this.talon2 = PWMTalonSRX(3)
    }

    /**
     * Polls the controller and then runs the robot
     * Gets called repeatedly during the teleoperated period
     */
    override fun teleopPeriodic() {
        this.talon1.speed = -this.controller.y
        this.talon2.speed = this.controller.y
    }

}
