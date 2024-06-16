package com.taetae98.diary.feature.memo.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.taetae98.diary.domain.memo.usecase.PageMemoUseCase
import com.taetae98.diary.library.paging3.emptyWithLoading
import kotlinx.coroutines.flow.mapLatest
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class MemoListViewModel(
    pageMemoUseCase: PageMemoUseCase,
) : ViewModel() {
    val pagingData = pageMemoUseCase(Unit).mapLatest { it.getOrNull() ?: PagingData.emptyWithLoading() }
        .cachedIn(viewModelScope)
}