package decorator

interface RegisterService {
    fun register(user: User)

    fun getUsersList(): String
}

open class RegisterServiceImpl : RegisterService {
    private val registered = mutableSetOf<User>()

    override fun register(user: User) {
        registered.add(user)
    }

    override fun getUsersList(): String {
        return registered.toString()
    }
}