package state

import RedColorState
import TrafficLightState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import utils.colorAsText

/**
 * Controller for Traffic light. Uses states. Change color every 2 seconds.
 * Prints current color of traffic light to console.
 * Text printed in console should be colored with traffic light color.
 *
 * To start traffic light use start() method. This method run traffic light asynchronously
 * Use stop method to stop traffic light
 *
 */
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
    readln()
    controller.stop()
}