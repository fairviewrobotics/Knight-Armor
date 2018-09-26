package frc.team2036.robot.knightarmor

/**
 * A class that handles registering robot components (eg. servos, motors, etc.)
 * Will keep track of used ports and will be able to give free ports
 */
object KnightPortRegistry {

    data class Port (val port_number: Int, val subsytem: String){}

    val ports = mutableListOf<Port>()
        get() = field

    //TODO: make

    // register a port with a io port number, a controller port, and the subsytem registering the port
    fun register(port_number: Int, subsytem: String){
        val prev_use = ports.firstOrNull( {port -> port.port_number == port_number})
        if(prev_use == null){
            ports.add(Port(port_number, subsytem));
        } else {
            throw Exception("Port Number in Use\nPort $port_number was attempted to be registered by $subsytem, already in use by ${prev_use.subsytem}")
        }
    }

}