package frc.team2036.robot.knightarmor

import edu.wpi.first.wpilibj.command.Subsystem

/**
 * An extended class of subsystem
 * Handles registering all subsystem info in other parts of KnightArmor
 */
open class KnightSubsystem : Subsystem() {

    /**
     * A small companion object that essentially stores static data for KnightSubsystem
     * All data in here is static, so it isn't owned by any instances
     */
    companion object {
        //A list of the names of all the subsystems; to actually publicly get, see registeredSubsystemNames
        private val allSubsystemNames = mutableListOf<String>()
        //A getter for all the names of the registeredSubsystems
        val registeredSubsystemNames get() = this.allSubsystemNames.toList()
    }

    /**
     * Registers the subsystem and ensures that its name is unique
     */
    init {
        if (allSubsystemNames.contains(this.name)) {
            throw Exception("A subsystem ${this.name} already exists! Don't make a new instance, use the existing instance!")
        }
        allSubsystemNames.add(this.name)
    }

    /**
     * Default constructor for WPILib
     * Is overriden to be empty by default, but can be further overriden
     */
    override fun initDefaultCommand() {}

    /**
     * Registers that the subystem will be using the specified port
     */
    fun claimPort(port: Int, portType: PortType): Int {
        KnightPortRegistry.register(port, portType, this.name)
        return port
    }

}
