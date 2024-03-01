package observer

import kotlinx.coroutines.*
import utils.printInGreen
import utils.printInRed
import kotlin.properties.Delegates

/**
 * This is a custom implementation of an Observer pattern
 * with Observable class
 *
 * Observable generic class is a wrapper for the value of any type
 * so that it can be observed. If the value changes it will notify
 * everyone who observes it.
 *
 * Anyone can subscribe to value change and will be notified.
 * Subscriber must provide a function to be executed
 * after observable value changes. The subscribe method returns the id
 * so that the subscriber can unsubscribe with this id
 */
class Observable<Type>(initialValue: Type) {
    private var index = 0
    private val observers: MutableMap<Int, (Type) -> Unit> = mutableMapOf()

    // Value with custom setter
    var value: Type = initialValue
        set(newValue) {
            field = newValue // set field value to newValue

            // Notify all subscribers
            observers.values.forEach { callback ->
                callback.invoke(newValue)
            }
        }

    /**
     * Alternatively to the setter implementation above,
     * there is a way to achieve the same logic with Delegate.observable(...)
     *
     * Delegate is a class from Kotlin standard library that has two functions.
     * One of them is the observable(), that takes two parameters:
     * initial value for a property and a function
     * that takes property, old value and new value as parameters
     * and will be called if the property changes.
     */
//    var value: Type by Delegates.observable(initialValue) { _, _, newValue ->
//        observers.values.forEach { callback ->
//            callback.invoke(newValue)
//        }
//    }

    /**
     * Subscribe to observable value change.
     * Takes function that will be executed
     * after observable value changes
     */
    fun subscribe(callback: (Type) -> Unit): Int {
        observers[index] = callback
        return index++
    }

    fun unsubscribe(id: Int) {
        observers.remove(id)
    }
}

/**
 * Temperature sensor
 *
 * The sensor has an observable temperature value.
 * Sensor prints temperature to console every 2 seconds
 * At the same time every 2 seconds temperature decreases by one
 * If the temperature reaches a value below -5, it will change the temperature to 5
 *
 * To start sensor use start(). This method runs the sensor asynchronously
 * To stop the sensor change the working value to false.
 */

class ObservableSensor {
    val temperature: Observable<Int> = Observable(5)
    var working: Boolean = false

    // Coroutine scope is used to start Sensor asynchronously
    private var scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)


    /**
     *  When started, the Sensor prints its temperature every 2 seconds
     *  Every 2 seconds it cools off, decreasing temperature by 1
     *  When the temperature is below -5, it heats up, making the temperature 5
     *
     */
    fun start() {
        scope.launch {
            working = true

            while (working) {
                temperature.value -= 1  // make colder
                println("Sensor: current temperature: ${temperature.value}")

                if (temperature.value == -5) {
                    temperature.value = 5 // make hotter
                    println("Sensor: current temperature: ${temperature.value}")
                }

                delay(2000)
            }
        }
    }
}

/**
 * Monitor that observes temperature
 *
 * Monitor can subscribe to Observable temperature value and notify if
 * it becomes less than [threshold]. [threshold] can be passed
 * as a constructor parameter. Every time the observable value changes monitor
 * will print if the value is below or above the threshold
 */
class ObserverMonitor(private val threshold: Int) {

    /**
     * Subscribe to observable value and print in color
     * if it is above or below the threshold
     */
    fun subscribeTo(temperature: Observable<Int>) {
        temperature.subscribe { value ->
            if (value < threshold)
                printInRed("Monitor: WARNING! Temperature is below $threshold!!!")
            else {
                printInGreen("Monitor: Temperature is fine")
            }
        }
    }
}


fun main() {
    println("Demonstration of Observable<Type> implementation:\n")
    val sensor = ObservableSensor()
    val monitor = ObserverMonitor(0)
    monitor.subscribeTo(sensor.temperature)

//    val hotterMonitor = ObserverMonitor(2)
//    hotterMonitor.subscribeTo(sensor.temperature)

    sensor.start()
    readln()
}