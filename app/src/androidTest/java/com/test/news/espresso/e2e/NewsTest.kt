package com.test.news.espresso.e2e

import android.webkit.WebChromeClient
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.web.assertion.WebViewAssertions
import androidx.test.espresso.web.assertion.WebViewAssertions.webMatches
import androidx.test.espresso.web.sugar.Web
import androidx.test.espresso.web.sugar.Web.onWebView
import androidx.test.espresso.web.webdriver.DriverAtoms.findElement
import androidx.test.espresso.web.webdriver.Locator
import androidx.test.rule.ActivityTestRule
import com.test.news.espresso.pages.loginPage
import com.test.news.espresso.pages.newsPage
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.espresso.models.validLoginUser
import com.test.news.features.news.presentation.NewsActivity
import org.junit.*

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
            intended(hasComponent(NewsActivity::class.java.name))
            swipeNewsHorizontally(0)
            swipeNewsHorizontally(1)
            //todo check image?
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
//            Intents.init()
            clickNewsImageAtPosition(0)
            //todo web?
//            onWebView().withElement(findElement(Locator.CLASS_NAME, "android.widget.Image"))

            intended(hasComponent(Web::class.java.name))
//            Intents.release()
        }
    }
}
