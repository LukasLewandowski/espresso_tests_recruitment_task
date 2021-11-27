package com.test.news.espresso.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.test.news.R
import org.hamcrest.Matchers.allOf
import org.junit.Assert

fun loginPage(func: LoginRobot.() -> Unit) = LoginRobot()
    .apply { func() }

class LoginRobot : BaseRobot() {

    fun setUsername(email: String) = typeText(R.id.editTextUserName, email);

    fun setPassword(pass: String) = typeText(R.id.editTextPassword, pass)

    fun clickLogin() = clickButton(R.id.buttonLogin)

    fun matchErrorText(err: String) = matchText(textView(android.R.id.message), err)

    fun checkUsernameValidationError(errorText: String): ViewInteraction =
        onView(withId(R.id.editTextUserName)).check(matches(hasErrorText(errorText)))

    fun checkPasswordValidationError(errorText: String): ViewInteraction =
        onView(withId(R.id.editTextPassword)).check(matches(hasErrorText(errorText)))
}