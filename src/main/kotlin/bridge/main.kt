package bridge

fun main() {
    val theme = DarkTheme()

    val id = "1"
    val amount = 4.99
    val invoice = Invoice(theme, id, amount)

    println(invoice.render())
}