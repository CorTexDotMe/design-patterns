package observer

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import utils.printInGreen
import utils.printInRed


class Sensor {
    // Coroutine scope is used to start Sensor asynchronously
    private var scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private var working = true

    var temperature: MutableStateFlow<Int> = MutableStateFlow(5)

    /**
     *  To demonstrate the idea, Sensor after starting will print its temperature every 2 seconds
     *  After printing it will cool off, decreasing temperature by 1
     *  When temperature is below -5, it will heat up, making temperature 5
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

    fun stop() {
        working = false
    }
}

class Monitor(private val threshold: Int) {
    // Coroutine scope is used to start Sensor asynchronously
    private var scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    fun collectOnTemperature(temperature: MutableStateFlow<Int>) {
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
    val sensor = Sensor()
    val monitor = Monitor(0)
    monitor.collectOnTemperature(sensor.temperature)

//    val hotterMonitor = Monitor(2)
//    hotterMonitor.collectOnTemperature(sensor.temperature)

    sensor.start()
    readln()
    sensor.stop()
}