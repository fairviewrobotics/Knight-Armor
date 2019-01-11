package frc.team2036.robot

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.PWMTalonSRX
import frc.team2036.robot.knightarmor.KnightBot
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.drive.DifferentialDrive

class Robot : KnightBot() {

    lateinit var controller: XboxController
    lateinit var mechanismTalon1: PWMTalonSRX
    lateinit var mechanismTalon2: PWMTalonSRX
    lateinit var drivetrain: DifferentialDrive

    override fun robotInit() {
        this.controller = XboxController(0)
        this.mechanismTalon1 = PWMTalonSRX(2)
        this.mechanismTalon2 = PWMTalonSRX(3)
        this.drivetrain = DifferentialDrive(PWMTalonSRX(1), PWMTalonSRX(0))
    }

    override fun teleopPeriodic() {
        this.drivetrain.arcadeDrive(-this.controller.getY(GenericHID.Hand.kLeft), this.controller.getX(GenericHID.Hand.kLeft))
        when {
            this.controller.getBumper(GenericHID.Hand.kLeft) -> {
                this.mechanismTalon1.speed = -1.0
                this.mechanismTalon2.speed = 1.0
            }
            this.controller.getBumper(GenericHID.Hand.kRight) -> {
                this.mechanismTalon1.speed = 1.0
                this.mechanismTalon2.speed = -1.0
            }
            else -> {
                this.mechanismTalon1.speed = 0.0
                this.mechanismTalon2.speed = 0.0
            }
        }
    }

}
