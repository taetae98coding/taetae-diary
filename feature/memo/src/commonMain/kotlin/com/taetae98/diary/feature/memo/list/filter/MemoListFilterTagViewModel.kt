package com.taetae98.diary.feature.memo.list.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taetae98.diary.core.coroutines.DEFAULT_WHILE_SUBSCRIBED
import com.taetae98.diary.domain.tag.usecase.FindAllTagUseCase
import com.taetae98.diary.domain.tag.usecase.UpdateTagMemoFilterUseCase
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class MemoListFilterTagViewModel(
    findAllTagUseCase: FindAllTagUseCase,
    private val updateTagMemoFilterUseCase: UpdateTagMemoFilterUseCase,
) : ViewModel() {
    val tagList = findAllTagUseCase(Unit).mapLatest { it.getOrNull().orEmpty() }
        .mapLatest { it.toPersistentList() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.DEFAULT_WHILE_SUBSCRIBED,
            initialValue = persistentListOf(),
        )

    fun updateSelect(id: String, isSelect: Boolean) {
        viewModelScope.launch {
            updateTagMemoFilterUseCase(UpdateTagMemoFilterUseCase.Param(id, isSelect))
        }
    }
}
