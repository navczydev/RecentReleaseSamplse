package com.example.recentreleasesamplse

import androidx.compose.ui.test.junit4.accessibility.disableAccessibilityChecks
import androidx.compose.ui.test.junit4.accessibility.enableAccessibilityChecks
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.tryPerformAccessibilityChecks
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResult
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResultUtils.matchesViews
import com.google.android.apps.common.testing.accessibility.framework.integrations.espresso.AccessibilityValidator
import org.hamcrest.Matchers.anyOf
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BtnGrpSampleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {

        // Enable accessibility checks with default configuration:
        composeTestRule.enableAccessibilityChecks(accessibilityValidator = accessibilityValidator)

        // Accessibility checks are run automatically when performing an action:
       // composeTestRule.onNodeWithText("Submit").performClick()

        // You can also manually run accessibility checks:
        composeTestRule.onRoot().tryPerformAccessibilityChecks()

        // When disabling accessibility checks..
        composeTestRule.disableAccessibilityChecks()
    }

    @Test
    fun testButtonGroup() {
        composeTestRule.setContent {
            LoginScreen()
        }
        // Accessibility checks are run automatically when performing an action:
        composeTestRule.onNodeWithText("Submit").performClick()
    }

    @After
    fun tearDown() {
        //
    }

}

// custom validator
val accessibilityValidator = AccessibilityValidator().apply {
    setThrowExceptionFor(AccessibilityCheckResult.AccessibilityCheckResultType.WARNING)
}