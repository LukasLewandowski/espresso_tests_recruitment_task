package com.test.news.espresso.e2e

import androidx.test.espresso.IdlingRegistry
import androidx.test.rule.ActivityTestRule
import com.test.news.espresso.pages.loginPage
import com.test.news.espresso.pages.newsPage
import com.test.news.features.login.presentation.LoginActivity
import org.junit.Rule
import org.junit.Test
import com.test.news.espresso.models.validLoginUser
import com.test.news.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before


class NewsTest {

    @Before
    fun setup() {
        loginPage {
            setUsername(validLoginUser.username)
            setPassword(validLoginUser.password)
            clickLogin()
        }
    }

//    @Before
//    fun registerIdlingResource() {
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
//    }
//
//
//    @After
//    fun unregisterIdlingResource() {
//        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
//    }

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
        newsPage {
            swipeNewsHorizontally(0)
            swipeNewsHorizontally(1)
        }
    }

    /**
     * Scenario 2 - failed to load images:
     * Given - the user successfully logged in to the app
     * When - there is no internet connection
     * Then - “failed to load news” error message is displayed and Retry button
     */
    @Test
    fun test_FailedToLoadNews() {
    }

    /**
     * Scenario 3 - news image is clicked:
     * Given - the news images are successfully loaded on the screen
     * When - the user clicks one of the image
     * Then - user is navigated to the external browser with clicked image loaded
     */
    @Test
    fun test_OpenNewsInExternalBrowser() {
    }
}
