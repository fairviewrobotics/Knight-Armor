package frc.team2036.robot.knightarmor

/**
 * An enumeration of all different port types
 * TODO: complete
 */
enum class PortType {
    DIGITAL_INPUT_OUTPUT, PULSE_WIDTH_MODULATION
}

/**
 * A class that handles registering robot components (eg. servos, motors, etc.)
 * Will keep track of used ports and will be able to give free ports
 */
object KnightPortRegistry {

    /**
     * A small inner class that represents a port that is in use on the RIO
     */
    data class RioPort(val portNumber: Int, val type: PortType, val owningSubsystemName: String)

    //A set of all ports in use; is only accessible internally; to get a copy, see portsInUse
    private val ports = mutableSetOf<RioPort>()

    //Gets the ports in use
    val portsInUse get() = this.ports.toSet()

    /**
     * Registers a port with a RoboRIO port number and the subsystem registering the port
     */
    internal fun register(portNumber: Int, portType: PortType, subsystemName: String) {
        val previouslyUsed = ports.firstOrNull { it.portNumber == portNumber && it.type == portType }
        if (previouslyUsed == null) {
            ports.add(RioPort(portNumber, portType, subsystemName))
        } else {
            throw Exception("$portType Port $portNumber was just registered for $subsystemName even though it is already claimed by ${previouslyUsed.owningSubsystemName}! Each port can only be claimed by one subsystem!")
        }
    }

    //TODO: make method to either return all unused ports or to just claim an unused port of portType

}