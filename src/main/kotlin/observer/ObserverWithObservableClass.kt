package observer

import kotlinx.coroutines.*
import utils.printInGreen
import utils.printInRed

class Observable<Type>(initialValue: Type) {
    private var index = 0
    private val observers: MutableMap<Int, (Type) -> Unit> = mutableMapOf()

    // Value with custom setter
    var value: Type = initialValue
        set(newValue) {
            field = newValue // set field value to newValue
            observers.values.forEach { callback ->
                callback.invoke(newValue)
            }
        }

    fun subscribe(callback: (Type) -> Unit): Int {
        observers[index] = callback
        return index++
    }

    fun unsubscribe(id: Int) {
        observers.remove(id)
    }
}

class ObservableSensor {
    val temperature: Observable<Int> = Observable(5)
    var working: Boolean = false

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

class ObserverMonitor(private val threshold: Int) {

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

    val hotterMonitor = ObserverMonitor(2)
    hotterMonitor.subscribeTo(sensor.temperature)

    sensor.start()
    readln()
}