package com.fernandopretell.retokonfio

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.fernandopretell.retokonfio.presentation.doglist.DogItem
import com.fernandopretell.retokonfio.presentation.model.DogUi
import com.fernandopretell.retokonfio.presentation.theme.DogsAppTheme
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalMaterial3Api::class)
class DogItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val sampleDog = DogUi(
        id = "1",
        name = "Chief",
        breed = "Guard Dog",
        ageFormatted = "Almost 5 years",
        imageUrl = "https://via.placeholder.com/150"
    )

    @Test
    fun dogItem_displaysCorrectText() {
        composeTestRule.setContent {
            DogsAppTheme {
                DogItem(dog = sampleDog)
            }
        }

        composeTestRule.onNodeWithText("Chief").assertIsDisplayed()
        composeTestRule.onNodeWithText("Guard Dog").assertIsDisplayed()
        composeTestRule.onNodeWithText("Almost 5 years").assertIsDisplayed()
    }
}
