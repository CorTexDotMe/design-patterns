package utils

import java.awt.Color

private val colorAsAnsiSymbol = mapOf(
    Color.RED to "\u001B[31m",
    Color.YELLOW to "\u001B[33m",
    Color.GREEN to "\u001B[32m"
)
private val endOfColorAnsiSymbol = "\u001B[0m"


fun textInColor(text: String, color: Color): String {
    return colorAsAnsiSymbol[color] + text + endOfColorAnsiSymbol
}

fun colorAsText(color: Color): String {
    return colorAsAnsiSymbol[color] + colorText(color) + endOfColorAnsiSymbol
}

private fun colorText(color: Color): String {
    return when (color) {
        Color.RED -> "Red"
        Color.YELLOW -> "Yellow"
        Color.GREEN -> "Green"
        else -> "Non-defined"
    }
}