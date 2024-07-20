package builder.kotlinstyle

class UserKtRepository {
    private val users = mutableListOf<UserKt>()
    private var id = 0L

    fun createUser(user: UserKt): UserKt {
        user.id = id++
        users.add(user)
        return user
    }

    fun getUser(id: Long): UserKt? {
        return users.find { it.id == id }
    }

    fun getUsers(): List<UserKt> {
        return users
    }
}