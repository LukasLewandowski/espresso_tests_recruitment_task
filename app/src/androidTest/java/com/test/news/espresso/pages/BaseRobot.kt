package com.test.news.espresso.pages

import android.view.View
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.hamcrest.Matchers.anything
import java.lang.AssertionError
import android.widget.TextView
import androidx.test.espresso.Espresso

import androidx.test.espresso.ViewAction

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.matcher.ViewMatchers.*


open class BaseRobot {

    fun fillEditText(resId: Int, text: String): ViewInteraction =
        onView(withId(resId)).perform(
            ViewActions.replaceText(text),
            ViewActions.closeSoftKeyboard()
        )

    fun typeText(resId: Int, text: String): ViewInteraction =
        onView(withId(resId)).perform(
            ViewActions.clearText(),
            ViewActions.typeText(text),
            ViewActions.closeSoftKeyboard()
        )

    fun clickButton(resId: Int): ViewInteraction =
        onView((withId(resId))).perform(ViewActions.click())

    fun textView(resId: Int): ViewInteraction = onView(withId(resId))

    fun matchText(viewInteraction: ViewInteraction, text: String): ViewInteraction = viewInteraction
        .check(ViewAssertions.matches(ViewMatchers.withText(text)))

    fun matchText(resId: Int, text: String): ViewInteraction = matchText(textView(resId), text)

    fun clickListItem(listRes: Int, position: Int) {
        onData(anything())
            .inAdapterView(allOf(withId(listRes)))
            .atPosition(position).perform(ViewActions.click())
    }

    private fun checkInvisibility(matcher: Matcher<View>): Boolean {
        return try {
            onView(matcher).check(matches(withEffectiveVisibility(Visibility.GONE)))
            true
        } catch (error: Error) {
            false
        }
    }

    fun waitUntilElementIsNotVisible(
        matcher: Matcher<View>,
        timeout: Long = 5000L
    ) {
        val assertionError =
            AssertionError("UI element with matcher: $matcher is visible after $timeout")
        val conditionCheckIntervalTime = 300L
        val startTime = System.currentTimeMillis()
        while (!checkInvisibility(matcher)) {
            Thread.sleep(conditionCheckIntervalTime)
            if (System.currentTimeMillis() - startTime >= timeout) {
                throw assertionError
            }
        }
    }

    fun waitUntilElementIsVisible(
        matcher: Matcher<View>,
        timeout: Long = 5000L
    ) {
        val assertionError =
            AssertionError("UI element with matcher: $matcher is not visible after $timeout")
        val conditionCheckIntervalTime = 300L
        val startTime = System.currentTimeMillis()
        while (checkInvisibility(matcher)) {
            Thread.sleep(conditionCheckIntervalTime)
            if (System.currentTimeMillis() - startTime >= timeout) {
                throw assertionError
            }
        }
    }

    open fun getText(matcher: Matcher<View?>?): String {
        var text = String()
        onView(matcher).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "getting text from a TextView"
            }

            override fun perform(uiController: UiController?, view: View) {
                val textView = view as TextView //Save, because of check in getConstraints()
                text = textView.text.toString()
            }
        })
        return text
    }
}