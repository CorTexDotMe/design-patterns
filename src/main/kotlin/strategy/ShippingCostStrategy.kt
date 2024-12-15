package strategy

/**
 *    We use shipping strategy for orders to extend order functionality.
 *    We could have added calculateCost method to Order.
 *    However, if added logic is long it is much better to have a separate class for it.
 *    More objects with small responsibility, instead of fewer objects with more responsibility.
 */

interface ShippingCostStrategy {
    fun calculateCost(order: Order): Double
}

class StandardShippingCostStrategy : ShippingCostStrategy {
    override fun calculateCost(order: Order): Double {
        return order.weight * 1.5
    }
}

class ExpressShippingCost : ShippingCostStrategy {
    override fun calculateCost(order: Order): Double {
        return order.weight * 3
    }
}

class ShippingController(private val shippingCostStrategy: ShippingCostStrategy) {
    fun shipOrderForm(order: Order): String {
        val cost = shippingCostStrategy.calculateCost(order)

        return "Order #${order.id}\n" +
                "destination: ${order.destination}\n" +
                "weight: ${order.weight}\n" +
                "cost: ${cost}$\n"
    }
}