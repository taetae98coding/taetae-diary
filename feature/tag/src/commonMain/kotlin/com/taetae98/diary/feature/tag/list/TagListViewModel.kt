package com.taetae98.diary.feature.tag.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.taetae98.diary.domain.tag.usecase.PageTagUseCase
import com.taetae98.diary.library.paging3.emptyWithLoading
import kotlinx.coroutines.flow.mapLatest
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class TagListViewModel(
    pageTagUseCase: PageTagUseCase,
) : ViewModel() {
    val pagingData = pageTagUseCase(Unit).mapLatest { it.getOrNull() ?: PagingData.emptyWithLoading() }
        .cachedIn(viewModelScope)
}
