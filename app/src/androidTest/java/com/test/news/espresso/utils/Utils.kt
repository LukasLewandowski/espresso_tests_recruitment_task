package com.test.news.espresso.utils

import androidx.test.platform.app.InstrumentationRegistry

fun turnOffInternetConnection() {
    InstrumentationRegistry.getInstrumentation().uiAutomation
        .executeShellCommand("svc wifi disable")
    InstrumentationRegistry.getInstrumentation().uiAutomation
        .executeShellCommand("svc data disable")
}

fun turnOnInternetConnection() {
    InstrumentationRegistry.getInstrumentation().uiAutomation
        .executeShellCommand("svc wifi enable")
    InstrumentationRegistry.getInstrumentation().uiAutomation
        .executeShellCommand("svc data enable")
}
