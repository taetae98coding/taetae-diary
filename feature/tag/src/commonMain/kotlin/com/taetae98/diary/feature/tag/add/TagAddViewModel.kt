package com.taetae98.diary.feature.tag.add

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taetae98.diary.domain.tag.entity.TagDetail
import com.taetae98.diary.domain.tag.exception.TagTitleEmptyException
import com.taetae98.diary.domain.tag.usecase.InsertTagUseCase
import com.taetae98.diary.feature.tag.detail.TagDetailMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class TagAddViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val insertTagUseCase: InsertTagUseCase,
) : ViewModel() {
    val title = savedStateHandle.getStateFlow(TITLE, "")
    val description = savedStateHandle.getStateFlow(DESCRIPTION, "")

    private val _message = MutableStateFlow<TagDetailMessage?>(null)
    val message = _message.asStateFlow()

    fun setTitle(title: String) {
        savedStateHandle[TITLE] = title
    }

    fun setDescription(description: String) {
        savedStateHandle[DESCRIPTION] = description
    }

    fun add() {
        val tagDetail = TagDetail(
            title = title.value,
            description = description.value,
        )

        viewModelScope.launch {
            insertTagUseCase(tagDetail).onSuccess {
                clear()
                _message.emit(TagDetailMessage.Add(::dismissMessage))
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
            is TagTitleEmptyException -> {
                _message.emit(TagDetailMessage.TitleEmpty(::dismissMessage))
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
