package com.taetae98.diary.feature.memo.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taetae98.diary.core.coroutines.DEFAULT_WHILE_SUBSCRIBED
import com.taetae98.diary.core.model.memo.tag.MemoTag
import com.taetae98.diary.core.navigation.memo.MemoDetailNav
import com.taetae98.diary.domain.memo.tag.usecase.FindMemoTagUseCase
import com.taetae98.diary.domain.memo.tag.usecase.UpdateMemoTagUseCase
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class MemoDetailTagViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val findMemoTagUseCase: FindMemoTagUseCase,
    private val updateMemoTagUseCase: UpdateMemoTagUseCase,
) : ViewModel() {
    private val memoId = savedStateHandle.getStateFlow(MemoDetailNav.MEMO_ID, "")

    val tagList = memoId.flatMapLatest { findMemoTagUseCase(it) }
        .mapLatest { it.getOrNull().orEmpty() }
        .mapLatest { it.toPersistentList() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.DEFAULT_WHILE_SUBSCRIBED,
            initialValue = persistentListOf(),
        )

    fun setMemoId(memoId: String) {
        savedStateHandle[MemoDetailNav.MEMO_ID] = memoId
    }

    fun updateMemoTag(id: String, isSelected: Boolean) {
        val memoTag = MemoTag(
            memoId = memoId.value,
            tagId = id,
        )

        viewModelScope.launch {
            updateMemoTagUseCase(UpdateMemoTagUseCase.Param(memoTag, isSelected))
        }
    }
}
