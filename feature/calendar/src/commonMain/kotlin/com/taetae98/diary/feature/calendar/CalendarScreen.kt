package com.taetae98.diary.feature.calendar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.taetae98.diary.core.compose.icon.DropDownIcon
import com.taetae98.diary.core.compose.icon.DropUpIcon
import com.taetae98.diary.feature.calendar.internal.CalendarBody
import com.taetae98.diary.feature.calendar.internal.CalendarState
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

@Composable
internal fun CalendarScreen(
    modifier: Modifier = Modifier,
    state: CalendarState,
) {
    Scaffold(
        modifier = modifier,
        topBar = { TopBar(state = state) },
    ) {
        CalendarBody(
            modifier = Modifier.padding(it),
            state = state,
        )
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    state: CalendarState,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            var isDialogVisible by rememberSaveable { mutableStateOf(false) }

            Row(
                modifier = Modifier.minimumInteractiveComponentSize()
                    .clickable { isDialogVisible = true },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "${state.year} / ${state.month.number}")

                if (isDialogVisible) {
                    DropUpIcon()
                } else {
                    DropDownIcon()
                }
            }

            DatePickerDialog(
                state = state,
                isVisible = { isDialogVisible },
                onDismissRequest = { isDialogVisible = false },
            )
        },
        actions = {
            val coroutineScope = rememberCoroutineScope()
            val today = remember { Clock.System.todayIn(TimeZone.currentSystemDefault()) }

            IconButton(
                onClick = { coroutineScope.launch { state.scrollToToday() } },
            ) {
                Surface(
                    shape = RoundedCornerShape(25),
                    border = BorderStroke(1.dp, LocalContentColor.current),
                ) {
                    Box(
                        modifier = Modifier.size(24.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = today.dayOfMonth.toString(),
                            fontSize = with(LocalDensity.current) { 14.dp.toSp() },
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                }
            }
        },
    )
}

@Composable
private fun DatePickerDialog(
    modifier: Modifier = Modifier,
    state: CalendarState,
    isVisible: () -> Boolean,
    onDismissRequest: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    if (isVisible()) {
        val instant = remember { LocalDate(state.year, state.month, 1).atStartOfDayIn(TimeZone.UTC) }
        val datePickerState = rememberDatePickerState(instant.toEpochMilliseconds())

        DatePickerDialog(
            modifier = modifier,
            onDismissRequest = onDismissRequest,
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            coroutineScope.launch {
                                val date = Instant.fromEpochMilliseconds(millis).toLocalDateTime(TimeZone.UTC)

                                onDismissRequest()
                                state.scrollTo(date.year, date.month)
                            }
                        }
                    },
                ) {
                    Text(text = "선택")
                }
            },
            content = {
                DatePicker(state = datePickerState)
            },
        )
    }
}
