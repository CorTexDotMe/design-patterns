package singleton

/**
 * Singleton can be implemented with object keyword.
 * This approach doesn't need separate class.
 *
 * This approach is thread-safe and lazy initialized,
 * when singleton is accessed for the first time
 */
object Settings {
    private val settings = HashMap<String, String>()

    fun put(key: String, value: String) {
        settings[key] = value
    }

    fun getValue(key: String): String? {
        return settings[key]
    }
}

fun main() {
    val secretKey = "secretKey"
    val secretValue = "secretValue"
    Settings.put(secretKey, secretValue)
    val keyValuesStoredCorrectly = secretValue == Settings.getValue(secretKey)
    println("Key-value pairs are stored correctly: $keyValuesStoredCorrectly")
}