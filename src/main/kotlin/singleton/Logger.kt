package singleton

import java.time.LocalDateTime

/**
 * Singleton can be implemented with companion object keywords
 * This approach requires a class that will exist as singleton
 * Companion object approach is usually used to add same behavior as static in java
 * To achieve thread-safety additional use of volatile and synchronized is required
 */
class Logger {
    companion object {
        // Use volatile annotation to ensure that changes are immediately visible to all threads
        @Volatile
        private var instance: Logger? = null

        //To prevent creating instance by multiple threads use synchronise
        fun getInstance(): Logger {
            return instance ?: synchronized(this) {
                instance ?: Logger().also { instance = it }
            }
        }
    }

    fun log(msg: String) {
        println(msg)
    }
}

fun main() {
    val time = LocalDateTime.now()

    val firstAccessInstance = Logger.getInstance()
    firstAccessInstance.log("Program started at $time")

    val loggerIsSingleton = firstAccessInstance == Logger.getInstance()
    println("Logger is singleton: $loggerIsSingleton")
}