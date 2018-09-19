package frc.team2036.robot.knightarmor

/**
 * The different types of messages that KnightScribe can report
 */
enum class KnightScribeLogLevel {
    INFO, WARNING, ERROR, DEBUG
}

/**
 * An object that handles logging
 * Has different modes depending on what is required of logger
 * Also makes displaying information to shuffleboard and displaying information to a terminal easy
 */
object KnightScribe {

    /**
     * The format in which messages are stored
     */
    data class LogMessage(val message: String, val logLevel: String) {

        //When the log message is created, the time of creation is recorded
        val time = Date()

        //Returns a string representation of the log message
        override fun toString(): String {
            return "[${this.time} ; ${this.logLevel}] -> ${this.message}"
        }

    }

    //The messages that the scribe is keeping track of
    private val messages = mutableListOf<LogMessage>()
    //How many messages the scribe should keep track of
    val maxMessages: Int = 0

    /**
     * A function that records the given log message
     */
    fun log(message: String, level: KnightScribeLogLevel) {
        this.innerLog(LogMessage(message, level.name))
    }

    /**
     * A function that logs a message from Knight-Armor
     * Can only be used from within Knight-Armor
     */
    internal fun knightLog(message: String) {
        this.innerLog(LogMessage(message, "KNIGHT_ARMOR"))
    }

    /**
     * A function that can only be used by Knight-Armor that actually handles the logging
     */
    internal fun innerLog(logMessage: LogMessage) {
        //TODO:
    }

}