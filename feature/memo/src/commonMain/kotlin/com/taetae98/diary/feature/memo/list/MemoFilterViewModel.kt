package com.taetae98.diary.feature.memo.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taetae98.diary.core.coroutines.DEFAULT_WHILE_SUBSCRIBED
import com.taetae98.diary.domain.memo.tag.usecase.HasMemoFilterUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class MemoFilterViewModel(
    private val hasMemoFilterUseCase: HasMemoFilterUseCase,
) : ViewModel() {
    val hasMemoFilter = hasMemoFilterUseCase(Unit)
        .mapLatest { it.getOrNull() ?: false }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.DEFAULT_WHILE_SUBSCRIBED,
            initialValue = false,
        )
}