package strategy


data class Order(val id: String, val destination: String, val weight: Double)


fun main() {
    val orderKyiv = Order("1", "Ukraine, Kyiv", 15.0)
    val stdShippingCost = StandardShippingCostStrategy()
//    val expressShippingCost = StandardShippingCostStrategy()
    val shippingController = ShippingController(stdShippingCost)
    println(shippingController.shipOrderForm(orderKyiv))

    val orderLviv = Order("1", "Ukraine, Lviv", 15.0)
//    val shippingLambdaController = ShippingLambdaController(::standardShippingCostStrategy)
    val shippingLambdaController = ShippingLambdaController(::expressShippingCostStrategy)
    println(shippingLambdaController.shipOrderForm(orderLviv))
}