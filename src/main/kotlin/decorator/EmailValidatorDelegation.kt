package decorator

/**
 * Decorator can be implemented with delegation.
 * Delegation in Kotlin is an alternative to inheritance that works for interfaces.
 * Instead of implementing all function from the interface we can delegate them to another object.
 * This object has to implement the interface.
 *
 * With this approach we can override only one method, which we need to decorate, and achieve desired behavior
 */
class EmailValidatorDelegation(private val registerService: RegisterService) : RegisterService by registerService {
    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"

    override fun register(user: User) {
        if (isValidEmail(user.email)) {
            registerService.register(user)
        } else {
            println("Error: Email is not valid")
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.matches(emailRegex.toRegex())
    }
}