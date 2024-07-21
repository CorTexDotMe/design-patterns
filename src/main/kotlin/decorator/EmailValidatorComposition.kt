package decorator

/**
 * Decorator can be implemented with composition.
 * This approach requires inheritance and implementing all functions from the interface
 */
class EmailValidatorComposition(private val registerService: RegisterService) : RegisterService {
    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

    override fun register(user: User) {
        if (isValidEmail(user.email)) {
            registerService.register(user)
        } else {
            println("Error: Email is not valid")
        }
    }

    override fun getUsersList(): String {
        return registerService.getUsersList()
    }

    private fun isValidEmail(email: String): Boolean {
        return email.matches(emailRegex.toRegex())
    }
}