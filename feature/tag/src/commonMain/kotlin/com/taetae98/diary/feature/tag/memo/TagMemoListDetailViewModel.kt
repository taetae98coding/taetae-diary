package com.taetae98.diary.feature.tag.memo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taetae98.diary.core.coroutines.DEFAULT_WHILE_SUBSCRIBED
import com.taetae98.diary.core.navigation.tag.TagMemoList
import com.taetae98.diary.domain.tag.repository.TagRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class TagMemoListDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val tagRepository: TagRepository,
) : ViewModel() {
    private val id = savedStateHandle.getStateFlow(TagMemoList.TAG_ID, "")

    val title = id.flatMapLatest { tagRepository.findById(it) }
        .mapLatest { it?.title.orEmpty() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.DEFAULT_WHILE_SUBSCRIBED,
            initialValue = "",
        )
}
