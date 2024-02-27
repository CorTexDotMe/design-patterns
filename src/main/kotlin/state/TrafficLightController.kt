package state

import RedColorState
import TrafficLightState
import kotlinx.coroutines.*
import utils.colorAsText

class TrafficLightController(initState: TrafficLightState) {
    // Coroutine scope is used to start TrafficLight asynchronously
    private var scope = CoroutineScope(Dispatchers.Default)
    private var working = false

    private var currentState: TrafficLightState = initState

    fun start() {
        // Start asynchronous coroutine with scope
        scope.launch {
            working = true

            while (working) {
                println("Current light is: ${colorAsText(currentState.color)}")
                currentState = currentState.changeLight()
                delay(2000L)
            }
        }
    }

    fun stop() {
        working = false
    }
}

fun main() {
    val controller = TrafficLightController(RedColorState())

    controller.start()

    // Wait for input to stop TrafficLight
    readLine()
    controller.stop()
}