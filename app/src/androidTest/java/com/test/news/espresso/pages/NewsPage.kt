package com.test.news.espresso.pages

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.test.news.R

fun newsPage(lackOfInternet: Boolean = false, func: NewsRobot.() -> Unit) =
    NewsRobot(lackOfInternet)
        .apply { func() }

class NewsRobot(lackOfInternet: Boolean) : BaseRobot() {
    init {
        if (!lackOfInternet) {
            try {
                waitUntilElementIsVisible(withId(R.id.progressBar))
            } catch (error: AssertionError) {
                Log.d("e2e", "progressBar is not visible")
            }
            waitUntilElementIsNotVisible(withId(R.id.progressBar))
            checkIfAppNameIsDisplayed()
        } else {
            checkIfAppNameIsDisplayed()
        }
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

//    fun retryBtnShouldBeDisplayed() {
//        onView(withId(R.id.retry)).check(matches(isDisplayed()))
//    }
}