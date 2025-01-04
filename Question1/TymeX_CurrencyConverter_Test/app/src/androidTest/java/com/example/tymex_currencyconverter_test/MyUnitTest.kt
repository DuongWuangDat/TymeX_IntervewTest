package com.example.tymex_currencyconverter_test

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tymex_currencyconverter_test.ui.screens.MainScreens.MainScreen
import dagger.hilt.android.testing.CustomTestApplication
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MyUnitTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule();

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun TestingCompose(){
        composeRule.setContent {
            MainScreen()
        }

        //Drop down menu (from) is visible
        val fromComboBox = composeRule.onNodeWithText("Select currency to convert from")
        assert(fromComboBox.isDisplayed())

        //Drop down menu (to) is visible
        val toComboBox = composeRule.onNodeWithText("Select currency to convert to")
        assert(toComboBox.isDisplayed())

        //Amount textfield is visible
        val amountField = composeRule.onNodeWithText("Input your amount value")

        composeRule.onNodeWithText("Get converter result").performClick()

        val snackbar = composeRule.onNodeWithText("Something went wrong")
        assert(toComboBox.isDisplayed())
    }
}