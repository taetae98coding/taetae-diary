package com.taetae98.diary.feature.memo.add

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taetae98.diary.domain.memo.entity.MemoDetail
import com.taetae98.diary.domain.memo.exception.MemoTitleEmptyException
import com.taetae98.diary.domain.memo.usecase.InsertMemoUseCase
import com.taetae98.diary.feature.memo.detail.MemoDetailMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class MemoAddViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val insertMemoUseCase: InsertMemoUseCase,
) : ViewModel() {
    val title = savedStateHandle.getStateFlow(TITLE, "")
    val description = savedStateHandle.getStateFlow(DESCRIPTION, "")

    private val _message = MutableStateFlow<MemoDetailMessage?>(null)
    val message = _message.asStateFlow()

    fun setTitle(title: String) {
        savedStateHandle[TITLE] = title
    }

    fun setDescription(description: String) {
        savedStateHandle[DESCRIPTION] = description
    }

    fun add() {
        val memoDetail = MemoDetail(
            title = title.value,
            description = description.value,
        )

        viewModelScope.launch {
            insertMemoUseCase(memoDetail).onSuccess {
                clear()
                _message.emit(MemoDetailMessage.Add(::dismissMessage))
            }.onFailure {
                handleError(it)
            }
        }
    }

    fun clear() {
        setTitle("")
        setDescription("")
    }

    private suspend fun handleError(throwable: Throwable) {
        when (throwable) {
            is MemoTitleEmptyException -> {
                _message.emit(MemoDetailMessage.TitleEmpty(::dismissMessage))
            }
        }
    }

    private fun dismissMessage() {
        viewModelScope.launch {
            _message.emit(null)
        }
    }

    companion object {
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
    }
}