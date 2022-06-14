package com.nihaskalam.automobilenewsapp.ui

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Main coroutine rule to replacing the Dispatchers with a TestCoroutineDispatcher,
 * which allows us to control the execution of the coroutines. We extend TestWatcher and implement
 * starting and finished methods by moving the code in setup() and tearDown() to them
 *
 * @property testDispatcher allows us to control the execution of the coroutines when we are doing tests.
 */
@ExperimentalCoroutinesApi
class MainCoroutineRule(val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :
    TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}

/**
 * Extension function to allow us to write MainCoroutineRule.runBlockingTest directly
 *
 * @param block code block to be executed
 */
@ExperimentalCoroutinesApi
fun MainCoroutineRule.runBlockingTest(block: suspend () -> Unit) =
    this.testDispatcher.runBlockingTest {
        block()
    }