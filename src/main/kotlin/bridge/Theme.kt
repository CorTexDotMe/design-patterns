package bridge

interface Theme {
    fun renderHeader(title: String): String
    fun renderContent(content: String): String
    fun renderFooter(): String
}

class LightTheme : Theme {
    override fun renderHeader(title: String) = "Light Header: $title"
    override fun renderContent(content: String) = "Light Content: $content"
    override fun renderFooter() = "Light Footer"
}

class DarkTheme : Theme {
    override fun renderHeader(title: String) = "Dark Header: $title"
    override fun renderContent(content: String) = "Dark Content: $content"
    override fun renderFooter() = "Dark Footer"
}