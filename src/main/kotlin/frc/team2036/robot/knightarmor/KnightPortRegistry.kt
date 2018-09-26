package frc.team2036.robot.knightarmor

/**
 * A class that handles registering robot components (eg. servos, motors, etc.)
 * Will keep track of used ports and will be able to give free ports
 */
object KnightPortRegistry {

    /**
     * A small inner class that represents a port that is in use on the RIO
     */
    data class Port(val portNumber: Int, val subsytem: String)

    //A set of all ports in use; getter returns a copy of the list
    private val ports = mutableSetOf<Port>()

    //Gets the ports in use
    val portsInUse get() = this.ports

    // register a port with a io port number, a controller port, and the subsytem registering the port
    fun register(portNumber: Int, subsytem: String) {
        val previouslyUsed = ports.firstOrNull { port -> port.portNumber == portNumber }
        if (previouslyUsed == null) {
            ports.add(Port(portNumber, subsytem))
        } else {
            throw Exception("Port Number in Use\nPort $portNumber was attempted to be registered by $subsytem, already in use by ${previouslyUsed.subsytem}")
        }
    }

}