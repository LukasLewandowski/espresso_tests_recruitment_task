package com.test.news.espresso

import com.test.news.espresso.e2e.LoginTest
import com.test.news.espresso.e2e.NewsTest
import com.test.news.espresso.e2e.NewsWithoutInternetTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/** execute all regression tests */

@RunWith(Suite::class)
@Suite.SuiteClasses(
    LoginTest::class,
    NewsTest::class,
    NewsWithoutInternetTest::class
)
class UIRegressionTestsSuite {
}