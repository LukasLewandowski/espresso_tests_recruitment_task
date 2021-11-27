package com.test.news.espresso.e2e

import androidx.test.rule.ActivityTestRule
import com.test.news.espresso.pages.loginPage
import com.test.news.espresso.pages.newsPage
import com.test.news.features.login.presentation.LoginActivity
import org.junit.Rule
import org.junit.Test
import android.content.Intent
import androidx.test.espresso.Espresso.pressBackUnconditionally
import com.test.news.espresso.models.invalidLoginUser
import com.test.news.espresso.models.validLoginUser
import org.junit.Assert


class LoginTest {

    @get:Rule
    var activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    /**
     * Scenario 1 - user opens the android app first time (when not logged in yet):
     * Given - the user opens app for the first time (when not logged in yet)
     * Then - login screen with user name and password entries and login button is displayed
     *
     * Scenario 3 - user login succeed:
     * Given - the user provided right user name and password
     * When - login button is clicked
     * Then - user is taken to the news screen
     *
     * Scenario 4 - user opens app next time (when previously logged in):
     * Given - the user opens app next time (when previously logged in)
     * Then - user is taken straight to the news screen
     */
    @Test
    fun test_LoginWithValidCredentials() {
        loginPage {
            setUsername(validLoginUser.username)
            setPassword(validLoginUser.password)
            clickLogin()
        }
        newsPage {}
        Assert.assertTrue(activityTestRule.activity.isFinishing)
        pressBackUnconditionally()
        activityTestRule.finishActivity()
        activityTestRule.launchActivity(Intent())
        // TODO: uncomment after fixing the bug with holding the app state after going to background
        // newsPage {}
    }

    /**
     * Scenario 2 - user login failed:
     * Given - the user provided wrong user name and/or password
     * When - login button is clicked
     * Then - error markers are displayed by user name and/or password entries
     */
    @Test
    fun test_IncorrectCredentials() {
        loginPage {
            setUsername(invalidLoginUser.username)
            setPassword(validLoginUser.password)
            clickLogin()
            checkUsernameValidationError("Wrong user name")
        }
        loginPage {
            setUsername(validLoginUser.username)
            setPassword(invalidLoginUser.password)
            clickLogin()
            checkPasswordValidationError("Wrong password")
        }
        loginPage {
            setUsername(invalidLoginUser.username)
            setPassword(invalidLoginUser.password)
            clickLogin()
            checkUsernameValidationError("Wrong user name")
            checkPasswordValidationError("Wrong password")
        }
    }
}
