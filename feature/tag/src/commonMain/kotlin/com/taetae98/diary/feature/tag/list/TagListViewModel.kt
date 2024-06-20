package com.taetae98.diary.feature.tag.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taetae98.diary.core.coroutines.DEFAULT_WHILE_SUBSCRIBED
import com.taetae98.diary.domain.tag.usecase.FindAllTagUseCase
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class TagListViewModel(
    findAllTagUseCase: FindAllTagUseCase,
) : ViewModel() {
    val tagList = findAllTagUseCase(Unit).mapLatest { it.getOrNull() }
        .mapLatest { it?.toImmutableList() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.DEFAULT_WHILE_SUBSCRIBED,
            initialValue = null
        )
}
