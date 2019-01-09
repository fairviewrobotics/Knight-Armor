package frc.team2036.robot.knightarmor

/**
 * A class for message emitters to extend
 */
open class KnightEmitter<T> {

    /**
     * A list of listeners mapped by their arbitrary names
     */
    private val listeners = mutableMapOf<String, (T) -> Unit>()

    /**
     * Adds a listener with the given name
     */
    fun addListener(name: String, listener: (T) -> Unit) {
        this.listeners[name] = listener
    }

    /**
     * Removes the listener with the given name
     */
    fun removeListener(name: String) {
        this.listeners.remove(name)
    }

    /**
     * Send a message to all listeners
     */
    protected fun emit(message: T) {
        this.listeners.values.forEach { listener -> listener(message) }
    }

}
