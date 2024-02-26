import java.awt.Color

interface TrafficLightState {
    val color: Color
    fun changeLight(): TrafficLightState
}

class RedColorState : TrafficLightState {
    override val color: Color = Color.RED

    override fun changeLight(): TrafficLightState {
        return YellowColorState(this)
    }
}

class YellowColorState(private val from: TrafficLightState) : TrafficLightState {
    override val color: Color = Color.YELLOW

    override fun changeLight(): TrafficLightState {
        if (from is GreenColorState)
            return RedColorState()
        return GreenColorState()
    }
}

class GreenColorState : TrafficLightState {
    override val color: Color = Color.GREEN

    override fun changeLight(): TrafficLightState {
        return YellowColorState(this)
    }
}