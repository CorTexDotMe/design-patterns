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

// Return string of color name that is colored when printed to console
fun colorAsText(color: Color): String {
    return colorAsAnsiSymbol[color] + colorText(color) + endOfColorAnsiSymbol
}

fun printInRed(text: String) {
    println( colorAsAnsiSymbol[Color.RED] + text + endOfColorAnsiSymbol)
}

fun printInGreen(text: String) {
    println( colorAsAnsiSymbol[Color.GREEN] + text + endOfColorAnsiSymbol)
}

private fun colorText(color: Color): String {
    return when (color) {
        Color.RED -> "Red"
        Color.YELLOW -> "Yellow"
        Color.GREEN -> "Green"
        else -> "Non-defined"
    }
}