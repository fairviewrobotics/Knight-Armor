package frc.team2036.robot.knightarmor

import java.util.*

/**
 * The different types of messages that KnightScribe can report
 */
enum class KnightScribeLogLevel {
    INFO, WARNING, ERROR, DEBUG
}

/**
 * An object that handles logging
 * Has different modes depending on what is required of logger
 * DOES NOT HANDLE DISPLAYING INFORMATION TO SHUFFLEBOARD, but this can be done by adding messageListeners to the KnightScribe
 */
object KnightScribe : KnightEmitter<KnightScribe.LogMessage>() {

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

    /**
     * Gets the most recent messages
     * Size of returned list is bounded by maxMessages
     */
    val recentMessages get() = this.messages.toList()

    /**
     * How many messages the scribe should keep track of
     * -1 maxMessages means the scribe will hold all the messages: THIS WILL RUN THE ROBORIO OUT OF MEMORY
     * Only use -1 maxMessages (all messages stored) when temporarily testing
     */
    val maxMessages: Int = 0

    /**
     * A function that records the given log message
     */
    fun log(message: String, level: KnightScribeLogLevel): String {
        return this.innerLog(LogMessage(message, level.name))
    }

    /**
     * A function that logs a message from Knight-Armor
     * Can only be used from within Knight-Armor
     */
    internal fun knightLog(message: String): String {
        return this.innerLog(LogMessage(message, "KNIGHT_ARMOR"))
    }

    /**
     * A function that can only be used by KnightScribe that actually handles the logging
     */
    private fun innerLog(logMessage: LogMessage): String {
        println(logMessage)
        this.messages.add(logMessage)
        while (this.messages.size > maxMessages && maxMessages != -1) {
            this.messages.removeAt(0)
        }
        this.emit(logMessage)
        return logMessage.toString()
    }

}
