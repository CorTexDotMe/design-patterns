package decorator

import utils.textInColor
import java.awt.Color

fun main() {
    val user = User("wrongEmail", "superpass")
    val registerService = RegisterServiceImpl()
    val emailValidatorComposition = EmailValidatorComposition(registerService)
    val emailValidatorDelegation = EmailValidatorDelegation(registerService)

    registerUser(emailValidatorComposition, user)
    println(
        "Wrong email user ${textInColor("not", Color.RED)} registered " +
                "with EmailValidator based on composition:" +
                " ${emailValidatorComposition.getUsersList()}"
    )

    registerUser(emailValidatorDelegation, user)
    println(
        "Wrong email user ${textInColor("not", Color.RED)} registered " +
                "with EmailValidator based on delegation:" +
                " ${emailValidatorDelegation.getUsersList()}"
    )

    registerUser(registerService, user)
    println("\nWrong email user registered with only RegisterService: ${registerService.getUsersList()}")
}

fun registerUser(registerService: RegisterService, user: User) {
    registerService.register(user)
}