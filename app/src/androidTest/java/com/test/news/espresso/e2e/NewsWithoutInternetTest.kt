package com.test.news.espresso.e2e

import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.test.news.espresso.pages.loginPage
import com.test.news.espresso.pages.newsPage
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.espresso.models.validLoginUser
import com.test.news.espresso.utils.turnOffInternetConnection
import com.test.news.espresso.utils.turnOnInternetConnection
import org.junit.*
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class NewsWithoutInternetTest {

    @Before
    fun setup() {
        turnOffInternetConnection()
        loginPage {
            setUsername(validLoginUser.username)
            setPassword(validLoginUser.password)
            clickLogin()
        }
    }

    @After
    fun tearDown() {
        turnOnInternetConnection()
    }

    @get:Rule
    var activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    /**
     * Scenario 2 - failed to load images:
     * Given - the user successfully logged in to the app
     * When - there is no internet connection
     * Then - “failed to load news” error message is displayed and Retry button
     */
    @Test
    fun test_FailedToLoadNews() {
        newsPage(true) {
            Assert.assertEquals(
                "e2e test - error text on News page is not correct",
                "Failed to load news", getNewsLoadError()
            )
            // TODO: uncomment after adding retry button
            // retryBtnShouldBeDisplayed()
        }
    }
}
