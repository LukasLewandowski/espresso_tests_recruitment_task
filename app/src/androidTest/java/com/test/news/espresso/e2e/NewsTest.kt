package com.test.news.espresso.e2e

import androidx.test.espresso.intent.Intents
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.test.news.espresso.pages.loginPage
import com.test.news.espresso.pages.newsPage
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.espresso.models.validLoginUser
import org.junit.*
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class NewsTest {

    @Before
    fun setup() {
        Intents.init()
        loginPage {
            setUsername(validLoginUser.username)
            setPassword(validLoginUser.password)
            clickLogin()
        }
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @get:Rule
    var activityTestRule = ActivityTestRule<LoginActivity>(LoginActivity::class.java)

    /**
     * Scenario 1 - news images are loaded:
     * Given - the user successfully logged in to the app
     * When - there is internet connection
     * Then - images are displayed in the rows on the list (row can have one or more images scrollable horizontally)
     */
    @Test
    fun test_LoadNews() {
        newsPage() {
            swipeNewsHorizontally(0)
            swipeNewsHorizontally(1)
            // TODO: check if swipe was successful
        }
    }

    /**
     * Scenario 3 - news image is clicked:
     * Given - the news images are successfully loaded on the screen
     * When - the user clicks one of the image
     * Then - user is navigated to the external browser with clicked image loaded
     */
    @Test
    fun test_OpenNewsInExternalBrowser() {
        newsPage() {
            clickNewsImageAtPosition(0)
            checkOpenedUrlInBrowser("www.newsbtc.com")
        }
    }
}
