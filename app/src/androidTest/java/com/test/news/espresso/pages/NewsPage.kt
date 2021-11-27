package com.test.news.espresso.pages

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.test.news.R

fun newsPage(func: NewsRobot.() -> Unit) = NewsRobot()
    .apply { func() }

class NewsRobot : BaseRobot() {
    init {
        waitUntilElementIsVisible(withId(R.id.progressBar))
        waitUntilElementIsNotVisible(withId(R.id.progressBar))
//        try {
//            onView(withId(R.id.recyclerViewNews)).check(matches(isDisplayed()))
//        } catch (error: Error) {
//            waitUntilElementIsNotVisible(withId(R.id.progressBar))
//        }
//        onView(withId(R.id.recyclerViewNews)).check(matches(isDisplayed()))
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
}