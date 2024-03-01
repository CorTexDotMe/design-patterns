package observer

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import utils.printInGreen
import utils.printInRed

/**
 * Temperature sensor
 *
 * The sensor has an observable temperature value.
 * Sensor prints temperature to console every 2 seconds.
 * At the same time every 2 seconds temperature decreases by one.
 * If the temperature reaches a value below -5, it will change the temperature to 5.
 *
 * To start sensor use start(). This method runs the sensor asynchronously.
 * To stop the sensor change the working value to false.
 *
 * This implementation uses the MutableStateFlow generic class which is part of
 * kotlin standard library to achieve the same behavior as with
 * custom Observable class. Uses emit(...) function to notify that
 * observable value had changed.
 */
class StateFlowSensor {
    val temperature: MutableStateFlow<Int> = MutableStateFlow(5)
    var working = true

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
                temperature.emit(temperature.value - 1)  // make colder
                println("Sensor: current temperature: ${temperature.value}")

                if (temperature.value == -5)
                    temperature.emit(5) // make hotter

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
 * will print if the value is below or above the threshold.
 *
 * Observable temperature is a value of MutableStateFlow.
 * Uses collect(...) method to subscribe to value change
 */
class StateFlowMonitor(private val threshold: Int) {
    // Coroutine scope is used to start Monitor asynchronously
    private var scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    /**
     * Collect on MutableStateFlow value and print in color
     * if it is above or below the threshold.
     * This will be executed every time new value
     * is emitted to the Flow
     */
    fun collectFrom(temperature: MutableStateFlow<Int>) {
        scope.launch {
            temperature.collect { value ->
                if (value < threshold)
                    printInRed("Monitor: WARNING! Temperature is below zero!!!")
                else {
                    printInGreen("Monitor: Temperature is fine")
                }
            }
        }
    }
}


fun main() {
    println("Demonstration of StateFlow<Type> implementation:\n")
    val sensor = StateFlowSensor()
    val monitor = StateFlowMonitor(0)
    monitor.collectFrom(sensor.temperature)

//    val hotterMonitor = StateFlowMonitor(2)
//    hotterMonitor.collectFrom(sensor.temperature)

    sensor.start()
    readln()
}