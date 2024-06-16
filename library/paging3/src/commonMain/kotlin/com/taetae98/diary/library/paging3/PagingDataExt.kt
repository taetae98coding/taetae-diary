package com.taetae98.diary.library.paging3

import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData

public fun <T : Any> PagingData.Companion.emptyWithLoading(): PagingData<T> {
    val loadStates = LoadStates(
        refresh = LoadState.Loading,
        prepend = LoadState.Loading,
        append = LoadState.Loading,
    )

    return empty(sourceLoadStates = loadStates)
}

public fun <T : Any> PagingData.Companion.fromWithLoading(
    data: List<T>,
): PagingData<T> {
    val loadStates = LoadStates(
        refresh = LoadState.Loading,
        prepend = LoadState.Loading,
        append = LoadState.Loading,
    )

    return from(data = data, sourceLoadStates = loadStates)
}


public fun <T : Any> PagingData.Companion.fromWithNotLoading(
    data: List<T>,
): PagingData<T> {
    val loadStates = LoadStates(
        refresh = LoadState.NotLoading(true),
        prepend = LoadState.NotLoading(true),
        append = LoadState.NotLoading(true),
    )

    return from(data = data, sourceLoadStates = loadStates)
}
