package com.example.melichallenge.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.melichallenge.Adapters.MainAdapter
import com.example.melichallenge.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(
        MainActivity::class.java
    )

    @Test
    @Throws(Exception::class)
    fun searchCelular() {
        onView(withId(R.id.search_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.inputText)).perform(typeText("Celular"))
        onView(withId(R.id.inputText)).perform(pressImeActionButton())
    }

    @Test
    fun clickItem() {
        searchCelular()
        waitApiCall(5000)
        onView(withId(R.id.rv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MainAdapter.MainViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.activity_item))
            .check(matches(isDisplayed()))
    }

    fun waitApiCall(time: Int) {
        try {
            Thread.sleep(time.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}