package com.taetae98.diary.core.compose.diary.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mikepenz.markdown.m3.Markdown
import com.taetae98.diary.core.compose.dimension.DiaryDp
import com.taetae98.diary.core.compose.icon.CodeIcon
import com.taetae98.diary.core.compose.icon.PreviewIcon
import com.taetae98.diary.core.compose.text.ClearTextField
import kotlinx.coroutines.launch

@Composable
public fun DiaryInfo(
    modifier: Modifier = Modifier,
    state: DiaryInfoState,
) {
    Card(
        modifier = modifier,
    ) {
        ClearTextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.title,
            label = { Text(text = "제목") },
            singleLine = true,
            maxLines = 1,
        )

        TabLayout(
            modifier = Modifier.fillMaxWidth(),
            state = state.pagerState,
        )

        Pager(
            modifier = Modifier.fillMaxWidth()
                .height(300.dp),
            state = state,
        )
    }
}

@Composable
private fun TabLayout(
    modifier: Modifier = Modifier,
    state: PagerState,
) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        modifier = modifier,
        selectedTabIndex = state.currentPage,
    ) {
        Tab(
            selected = state.currentPage == 0,
            onClick = { coroutineScope.launch { state.animateScrollToPage(0) } },
            icon = { CodeIcon() },
        )

        Tab(
            selected = state.currentPage == 1,
            onClick = { coroutineScope.launch { state.animateScrollToPage(1) } },
            icon = { PreviewIcon() },
        )
    }
}

@Composable
private fun Pager(
    modifier: Modifier = Modifier,
    state: DiaryInfoState,
) {
    HorizontalPager(
        modifier = modifier,
        state = state.pagerState,
        key = { it },
    ) { page ->
        if (page == 0) {
            ClearTextField(
                modifier = Modifier.fillMaxSize(),
                state = state.description,
                label = { Text(text = "설명") },
            )
        } else if (page == 1) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Markdown(
                    modifier = Modifier.fillMaxWidth()
                        .padding(DiaryDp.diaryPadding),
                    content = state.description.value,
                )
            }
        }
    }
}
