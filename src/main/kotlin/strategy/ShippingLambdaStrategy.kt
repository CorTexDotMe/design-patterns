package strategy

/**
 *    No strategy classes, just lambdas
 */

typealias ShippingCostLambdaStrategy = (Order) -> Double

fun standardShippingCostStrategy(order: Order): Double {
    return order.weight * 1.5
}

fun expressShippingCostStrategy(order: Order): Double {
    return order.weight * 3
}

class ShippingLambdaController(private val shippingCostStrategy: ShippingCostLambdaStrategy) {
    fun shipOrderForm(order: Order): String {
        val cost = shippingCostStrategy(order)

        return "Order #${order.id}\n" +
                "destination: ${order.destination}\n" +
                "weight: ${order.weight}\n" +
                "cost: ${cost}$\n"
    }
}