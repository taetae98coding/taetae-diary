package com.taetae98.diary.core.compose.text

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@Ignore("Compose + Robolectric 버그로 CI 테스트 시 Exception 발생")
@RunWith(RobolectricTestRunner::class)
class ClearTextFieldTest {
    @Test
    fun `텍스트가 없으면 ClearButton이 보이지 않는다`() {
        val stringState = mutableStateOf("")

        internalTest(
            state = TextFieldState(
                stringState = stringState,
                onValueChange = { stringState.value = it },
            ),
        ) {
            onNode(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))
                .assertDoesNotExist()
        }
    }

    @Test
    fun `텍스트가 있다면 ClearButton이 보인다`() {
        val stringState = mutableStateOf("텍스트")

        internalTest(
            state = TextFieldState(
                stringState = stringState,
                onValueChange = { stringState.value = it },
            ),
        ) {
            onNode(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))
                .assertExists()
        }
    }

    @Test
    fun `ClearButton 클릭 시 사라진다`() {
        val stringState = mutableStateOf("텍스트")

        internalTest(
            state = TextFieldState(
                stringState = stringState,
                onValueChange = { stringState.value = it },
            ),
        ) {
            onNode(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))
                .performClick()
                .assertDoesNotExist()
        }
    }

    @Test
    fun `ClearButton 클릭 시 텍스트 지운다`() {
        val stringState = mutableStateOf("텍스트")

        internalTest(
            state = TextFieldState(
                stringState = stringState,
                onValueChange = { stringState.value = it },
            ),
        ) {
            onNodeWithTag("TextField")
                .assertTextEquals("텍스트")

            onNode(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))
                .performClick()

            onNodeWithTag("TextField")
                .assertTextEquals("")

            assertEquals(stringState.value, "")
        }
    }

    @Test
    fun `ClearButton 클릭 시 텍스트 포커스를 가져간다`() {
        val stringState = mutableStateOf("텍스트")

        internalTest(
            state = TextFieldState(
                stringState = stringState,
                onValueChange = {},
            ),
        ) {
            onNode(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button))
                .performClick()

            onNodeWithTag("TextField")
                .assertIsFocused()
        }
    }

    private fun internalTest(
        state: TextFieldState,
        test: ComposeUiTest.() -> Unit,
    ) {
        runComposeUiTest {
            setContent {
                ClearTextField(
                    state = state,
                )
            }

            test()
        }
    }
}