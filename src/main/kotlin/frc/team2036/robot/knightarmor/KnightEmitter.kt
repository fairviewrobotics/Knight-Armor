package frc.team2036.robot.knightarmor

/**
 * A class for message emitters to extend
 */

class KnightEmitter<T> {

    private val listeners = mutableListOf<(T) -> Unit>()

    /**
     * add a listener that recives all messages */

    fun addListener(listener: (T) -> Unit ){
        listeners.add(listener)
    }

    /**
     * send a message to all listeners */
    protected fun emit(message: T){
        listeners.forEach( {listener -> listener(message)} )
    }
}