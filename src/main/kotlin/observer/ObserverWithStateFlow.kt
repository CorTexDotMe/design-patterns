package observer

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import utils.printInGreen
import utils.printInRed


class StateFlowSensor {
    val temperature: MutableStateFlow<Int> = MutableStateFlow(5)
    var working = true

    // Coroutine scope is used to start Sensor asynchronously
    private var scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    /**
     *  To demonstrate the idea, Sensor prints its temperature every 2 seconds
     *  Every 2 seconds it cools off, decreasing temperature by 1
     *  When temperature is below -5, it heats up, making temperature 5
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

class StateFlowMonitor(private val threshold: Int) {
    // Coroutine scope is used to start Monitor asynchronously
    private var scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

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

    val hotterMonitor = StateFlowMonitor(2)
    hotterMonitor.collectFrom(sensor.temperature)

    sensor.start()
    readln()
}