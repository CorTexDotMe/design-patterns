package bridge

class Invoice(private val theme: Theme, private val id: String, private val amount: Double) {
    fun render(): String {
        val res = StringBuilder()
        res.appendLine(theme.renderHeader("Invoice #$id"))
        res.appendLine(theme.renderContent("amount: $amount"))
        res.appendLine(theme.renderFooter())
        return res.toString()
    }
}