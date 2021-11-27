package com.test.news.espresso.models

/** storage for user credentials */
data class User(
    val username: String,
    val password: String
)

val validLoginUser = User("user1", "password")
val invalidLoginUser = User("user0", "pass")
