package builder

import builder.javastyle.UserJava
import builder.javastyle.UserJavaRepository
import builder.kotlinstyle.UserKt
import builder.kotlinstyle.UserKtRepository

fun main() {
    createUserKotlinStyle()
    createUserJavaBuilder()
}

fun createUserKotlinStyle() {
    val userKtRepository = UserKtRepository()

    val user = UserKt(
        name = "Amori",
        email = "amori@gmail.com",
        password = "amori-the-best"
    )

    userKtRepository.createUser(user)
    val userHasCorrectId = user.id >= 0
    val userIsStoredProperly =
        userKtRepository.getUser(user.id)?.equals(userKtRepository.getUsers().first()) ?: false

    println("Kotlin approach:")
    println("User has correct id: $userHasCorrectId")
    println("User is stored properly with UserKtRepository: $userIsStoredProperly\n")
}

fun createUserJavaBuilder() {
    val userJavaRepository = UserJavaRepository()

    val user = UserJava.Builder.newInstance()
        .name("Amori")
        .email("amori@gmail.com")
        .password("amori-the-best")
        .build()

    userJavaRepository.createUser(user)
    val userHasCorrectId = user.id >= 0
    val userIsStoredProperly =
        userJavaRepository.getUser(user.id).get() == userJavaRepository.users.first()

    println("Java approach:")
    println("User has correct id: $userHasCorrectId")
    println("User is stored properly with UserJavaRepository: $userIsStoredProperly\n")
}