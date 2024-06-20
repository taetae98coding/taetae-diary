package com.taetae98.diary.core.compose.swipe

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.taetae98.diary.core.compose.icon.CircleIcon
import com.taetae98.diary.core.compose.icon.DeleteIcon
import com.taetae98.diary.core.compose.icon.FinishIcon

@Composable
public fun FinishAndDeleteSwipe(
    modifier: Modifier = Modifier,
    onFinish: () -> Boolean,
    onDelete: () -> Boolean,
    content: @Composable RowScope.() -> Unit,
) {
    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            when (it) {
                SwipeToDismissBoxValue.StartToEnd -> onFinish()
                SwipeToDismissBoxValue.EndToStart -> onDelete()
                else -> true
            }
        },
    )

    SwipeToDismissBox(
        modifier = modifier,
        state = state,
        backgroundContent = {
            Box(
                modifier = Modifier.fillMaxSize()
                    .drawBehind {
                        val color = when (state.dismissDirection) {
                            SwipeToDismissBoxValue.Settled -> null
                            SwipeToDismissBoxValue.StartToEnd -> Color.Green
                            SwipeToDismissBoxValue.EndToStart -> Color.Red
                        }?.copy(alpha = 0.5F)

                        color?.let(::drawRect)
                    }
                    .padding(horizontal = 8.dp),
            ) {
                when (state.dismissDirection) {
                    SwipeToDismissBoxValue.StartToEnd -> SwipeFinishButton(state = state)
                    SwipeToDismissBoxValue.EndToStart -> SwipeDeleteButton(state = state)
                    else -> Unit
                }
            }
        },
        content = content,
    )
}

@Composable
private fun BoxScope.SwipeDeleteButton(
    modifier: Modifier = Modifier,
    state: SwipeToDismissBoxState,
) {
    SwipeIcon(
        modifier = modifier,
        state = state,
        dismissValue = SwipeToDismissBoxValue.EndToStart,
    ) {
        DeleteIcon()
    }
}

@Composable
private fun BoxScope.SwipeFinishButton(
    modifier: Modifier = Modifier,
    state: SwipeToDismissBoxState,
) {
    SwipeIcon(
        modifier = modifier,
        state = state,
        dismissValue = SwipeToDismissBoxValue.StartToEnd,
    ) {
        FinishIcon()
    }
}

@Composable
private fun BoxScope.SwipeIcon(
    modifier: Modifier = Modifier,
    state: SwipeToDismissBoxState,
    dismissValue: SwipeToDismissBoxValue,
    content: @Composable () -> Unit,
) {
    val alignment = when (dismissValue) {
        SwipeToDismissBoxValue.StartToEnd -> Alignment.CenterStart
        SwipeToDismissBoxValue.EndToStart -> Alignment.CenterEnd
        else -> Alignment.Center
    }

    val scale by animateFloatAsState(
        when (state.targetValue) {
            dismissValue -> 1.33F
            else -> 0.66F
        },
    )

    when (state.targetValue) {
        dismissValue -> Box(
            modifier = modifier.align(alignment)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
        ) {
            content()
        }

        SwipeToDismissBoxValue.Settled -> CircleIcon(
            modifier = modifier.align(alignment)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                },
        )

        else -> Unit
    }
}