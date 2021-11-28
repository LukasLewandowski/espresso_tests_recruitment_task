package com.test.news.espresso.pages

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.UriMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.test.news.R
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers

fun newsPage(lackOfInternet: Boolean = false, func: NewsRobot.() -> Unit) =
    NewsRobot(lackOfInternet)
        .apply { func() }

class NewsRobot(lackOfInternet: Boolean) : BaseRobot() {
    init {
        if (!lackOfInternet) {
            try {
                waitUntilElementIsVisible(withId(R.id.progressBar))
            } catch (error: AssertionError) {
                Log.d("e2e", "progressBar is not visible - AssertionError")
            } catch (error: NoMatchingViewException) {
                Log.d("e2e", "progressBar is not visible - NoMatchingViewException")
            }
            waitUntilElementIsNotVisible(withId(R.id.progressBar))
            waitUntilElementIsVisible(withId(R.id.recyclerViewNews))
        }
        checkIfAppNameIsDisplayed()
    }

    private fun checkIfAppNameIsDisplayed() {
        onView(withText(R.string.app_name)).check(matches(isDisplayed()))
    }

    fun swipeNewsHorizontally(position: Int) {
        onView(withId(R.id.recyclerViewNews)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                swipeLeft()
            )
        )
    }

    fun clickNewsImageAtPosition(position: Int) {
        Thread.sleep(2000) // TODO: remove hardcoded sleep
        onView(withId(R.id.recyclerViewNews)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )
    }

    fun getNewsLoadError(): String {
        return getText(withId(R.id.textViewError))
    }

    // TODO: uncomment after adding "Retry" button
//    fun retryBtnShouldBeDisplayed() {
//        onView(withId(R.id.retry)).check(matches(isDisplayed()))
//    }

    fun checkOpenedUrlInBrowser(url: String) {
        intended(
            Matchers.allOf(
                IntentMatchers.hasData(UriMatchers.hasHost(CoreMatchers.containsString(url))),
                hasAction(Intent.ACTION_VIEW),
                IntentMatchers.toPackage("com.android.chrome")
            )
        )
    }
}